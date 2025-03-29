package com.learn.auto.testsForHeaders;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.learn.auto.parent.ValidationOfGitHubAPIs;
import com.learn.auto.util.ResponseUtilities;

public class GitHubAPIOtherResponseHeaders extends ValidationOfGitHubAPIs {
  private ResponseUtilities responseUtilities;
  
  @Test
  public void testServerHeader() throws ClientProtocolException, IOException {
	  HttpGet get = new HttpGet(BASE_URL);
	  response = client.execute(get);
	  String val = responseUtilities.getHeader(response, "server");
	  assertEquals(val, "github.com");
  }
  @Test
  public void testCustomHeader() throws ClientProtocolException, IOException {
	  HttpGet get = new HttpGet(BASE_URL);
	  response = client.execute(get);
	  
	  /*
	   * Let's check the value for the header X-RateLimit.
	   * Notice the 'X' at the beginning; this is supposed to tell that it is not a standard header.
	   * It is rather a custom header which is developed by the corresponding developer(s).
	   * This shows that we can't possibly have an Apache or any other HTTP library that supplies all possible methods for all possible headers.
	   */
	  String val = responseUtilities.getHeaderUsingLambdaAndStream(response, "X-RateLimit-Limit");
	  assertEquals(Integer.parseInt(val), 60);
  }
  @Test
  public void testOtherHeader() throws ClientProtocolException, IOException {
	  HttpGet get = new HttpGet(BASE_URL);
	  response = client.execute(get);
	  assertTrue(responseUtilities.isHeaderPresent(response, "ETag"), "ETag is not present in the response!");
  }
  @BeforeClass
  public void beforeClass() {
	  responseUtilities = new ResponseUtilities();
  }
}
