package com.learn.auto.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.auto.pojo.User;

public class ResponseUtilities {
	/*
	 * Take a response and the header whose value needs to be extracted from that response.
	 * Retrieve all headers from the response and iterate through them to get the exact match with the header specified in argument
	 * If the match is found, extract the value and return to the caller, otherwise throw a runtime exception
	 */
	public String getHeader(HttpResponse response, String header) {
		Header[] headers = response.getAllHeaders();
		for (Header h : headers) {
			if (h.getName().equalsIgnoreCase(header)) {
				return h.getValue();
			}
		}
		throw new RuntimeException("Header " + header + " not found in response!");
	}
	
	/*
	 * Here is the alternative way of doing the same as above, but using lambda and streams.
	 * The method generates a list of headers from the response, and then it streams them.
	 * In other words, it makes the headers flow in a stream one by one.
	 * As they flow past, it filters for a header specified in its argument and after that, it extracts the first which gives the match.
	 */
	public String getHeaderUsingLambdaAndStream(HttpResponse response, String header) {
		List<Header> headers = Arrays.asList(response.getAllHeaders());
		
		Header matchedHeader = headers.stream().filter(h -> header.equalsIgnoreCase(h.getName())).findFirst()
				.orElseThrow(() -> new RuntimeException("Header " + header + " not found in response!"));
		return matchedHeader.getValue();
	}
	
	/*
	 * Check whether a particular header is present in response
	 * This is needed when we can't validate any fixed value of certain headers such as ETag since they are likely to change over time
	 * ETag helps Web APIs with caching, which means that it determines if the client still has a fresh copy of the content.
	 * If no, then it sends the content with status 200, otherwise it sends 304 saying 'Use your own local copy'.
	 * So, it is never confirmed what the value of ETag is going to be at a particular moment of time.
	 * But it's necessary to check whether ETag header is present in the response.
	 */
	public boolean isHeaderPresent(HttpResponse response, String header) {
		List<Header> headers = Arrays.asList(response.getAllHeaders());
		
		return headers.stream().anyMatch(h -> h.getName().equalsIgnoreCase(header));
	}
	
	/*
	 * Un-marshal (or de-serialize) a response to custom User object.
	 * The whole response body may contain lot of fields. But the custom POJO class has only two fields.
	 * This may lead to failure such as UnrecognizedPropertyException.
	 * So the ignored fields from response body should be handled in such a way that only extracts the fields which are present in the POJO.
	 */
	public User unmarshal(HttpResponse response, Class<User> user) throws ParseException, IOException {
		String body = EntityUtils.toString(response.getEntity());
		return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(body, user);
	}
	
	/*
	 * Un-marshal (or de-serialize) a response to a generic object.
	 * This is a generic method with uses Java generic.
	 * So, this method is not bound to any specific type such as User.
	 */
	public <T> T unmarshalGeneric(HttpResponse response, Class<T> type) throws ParseException, IOException {
		String body = EntityUtils.toString(response.getEntity());
		return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(body, type);
	}
}
