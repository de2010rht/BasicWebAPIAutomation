package com.learn.auto.testsForStatusCodes;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.testng.annotations.Test;

import com.learn.auto.parent.ValidationOfGitHubAPIs;

public class GitHubAPIStatus401 extends ValidationOfGitHubAPIs {
  @Test
  public void testUnauthenticatedUser() throws ClientProtocolException, IOException {
	  HttpGet get = new HttpGet(BASE_URL + "/user");
	  response = client.execute(get);
	  int statusCode = response.getStatusLine().getStatusCode();
	  assertEquals(statusCode, 401);
  }
  @Test
  public void testFollowersForUnauthenticatedUser() throws ClientProtocolException, IOException {
	  HttpGet get = new HttpGet(BASE_URL + "/user/followers");
	  response = client.execute(get);
	  int statusCode = response.getStatusLine().getStatusCode();
	  assertEquals(statusCode, 401);
  }
  @Test
  public void testNotificationsForUnauthenticatedUser() throws ClientProtocolException, IOException {
	  HttpGet get = new HttpGet(BASE_URL + "/notifications");
	  response = client.execute(get);
	  int statusCode = response.getStatusLine().getStatusCode();
	  assertEquals(statusCode, 401);
  }
}
