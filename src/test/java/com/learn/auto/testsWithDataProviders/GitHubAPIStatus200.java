package com.learn.auto.testsWithDataProviders;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.learn.auto.parent.ValidationOfGitHubAPIs;

public class GitHubAPIStatus200 extends ValidationOfGitHubAPIs {
  @DataProvider
  private Object[][] endPoints() {
	  return new String[][] {
		  { "/rate_limit" },
		  { "/search/repositories?q=C#" }
	  };
  }
  @Test(dataProvider = "endPoints")
  public void testSuccess(String endPoint) throws ClientProtocolException, IOException {
	  HttpGet get = new HttpGet(BASE_URL + endPoint);
	  response = client.execute(get);
	  int statusCode = response.getStatusLine().getStatusCode();
	  assertEquals(statusCode, 200);
  }
}
