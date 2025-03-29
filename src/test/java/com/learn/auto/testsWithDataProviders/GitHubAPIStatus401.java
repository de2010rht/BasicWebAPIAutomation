package com.learn.auto.testsWithDataProviders;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.learn.auto.parent.ValidationOfGitHubAPIs;

public class GitHubAPIStatus401 extends ValidationOfGitHubAPIs {
  @DataProvider
  private Object[][] endPoints() {
	  return new String[][] {
		  { "/user" },
		  { "/user/followers" },
		  { "/notifications" }
	  };
  }
  @Test(dataProvider = "endPoints")
  public void testUnauthenticated(String endPoint) throws ClientProtocolException, IOException {
	  HttpGet get = new HttpGet(BASE_URL + endPoint);
	  response = client.execute(get);
	  int statusCode = response.getStatusLine().getStatusCode();
	  assertEquals(statusCode, 401);
  }
}
