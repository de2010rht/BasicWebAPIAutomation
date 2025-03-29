package com.learn.auto.testsForHeaders;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.testng.annotations.Test;

import com.learn.auto.parent.ValidationOfGitHubAPIs;

/*
 * There are 3 different terms; MIME Type, Media Type & Content-Type.
 * MIME Type or Media Type is what we get in response, such as plain text, an image, a file, XML, JSON etc.
 * In the HTTP world, the header is called Content-Type, which comprises of Media Type and the encoding scheme.
 */
public class GitHubAPIStandardResponseHeaders extends ValidationOfGitHubAPIs {
  @Test
  public void testContentType() throws ClientProtocolException, IOException {
	  HttpGet get = new HttpGet(BASE_URL);
	  response = client.execute(get);
	  Header contentType = response.getEntity().getContentType();
	  assertEquals(contentType.getValue(), "application/json; charset=utf-8");
  }
  @Test
  public void testMediaType() throws ClientProtocolException, IOException
  {
	  HttpGet get = new HttpGet(BASE_URL);
	  response = client.execute(get);
	  ContentType contentType = ContentType.getOrDefault(response.getEntity());
	  assertEquals(contentType.getMimeType(), "application/json");
  }
  @Test
  public void testCharset() throws ClientProtocolException, IOException {
	  HttpGet get = new HttpGet(BASE_URL);
	  response = client.execute(get);
	  ContentType contentType = ContentType.getOrDefault(response.getEntity());
	  assertEquals(contentType.getCharset().toString(), "UTF-8");
  }
}
