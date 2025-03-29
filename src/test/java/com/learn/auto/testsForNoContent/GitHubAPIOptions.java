package com.learn.auto.testsForNoContent;

import org.testng.annotations.Test;

import com.learn.auto.parent.ValidationOfGitHubAPIs;
import com.learn.auto.util.ResponseUtilities;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpOptions;
import org.testng.annotations.BeforeClass;

public class GitHubAPIOptions extends ValidationOfGitHubAPIs {
  private ResponseUtilities responseUtilities;
  
  @Test
  public void testNoContent() throws ClientProtocolException, IOException {
	  String header = "Access-Control-Allow-Methods";
	  String expectedMethods = "GET, POST, PATCH, PUT, DELETE";
	  
	  HttpOptions options = new HttpOptions(BASE_URL);
	  response = client.execute(options);
	  String actualMethods = responseUtilities.getHeader(response, header);
	  assertEquals(actualMethods, expectedMethods);
  }
  @BeforeClass
  public void beforeClass() {
	  responseUtilities = new ResponseUtilities();
  }
}
