package com.learn.auto.testsForStatusCodes;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.testng.annotations.Test;

import com.learn.auto.parent.ValidationOfGitHubAPIs;

public class GitHubAPIStatus200 extends ValidationOfGitHubAPIs {
  
  @Test
  public void testBasic() throws ClientProtocolException, IOException {
	  HttpGet get = new HttpGet(BASE_URL);
	  response = client.execute(get);
	  int statusCode = response.getStatusLine().getStatusCode();
	  assertEquals(statusCode, 200);
  }
  @Test
  public void testRateLimit() throws ClientProtocolException, IOException {
	  HttpGet get = new HttpGet(BASE_URL + "/rate_limit");
	  response = client.execute(get);
	  int statusCode = response.getStatusLine().getStatusCode();
	  assertEquals(statusCode, 200);
  }
  @Test
  public void testSearch() throws ClientProtocolException, IOException {
	  HttpGet get = new HttpGet(BASE_URL + "/search/repositories?q=C#");
	  response = client.execute(get);
	  int statusCode = response.getStatusLine().getStatusCode();
	  assertEquals(statusCode, 200);
  }
}
