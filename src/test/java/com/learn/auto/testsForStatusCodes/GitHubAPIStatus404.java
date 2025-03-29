package com.learn.auto.testsForStatusCodes;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.testng.annotations.Test;

import com.learn.auto.parent.ValidationOfGitHubAPIs;

public class GitHubAPIStatus404 extends ValidationOfGitHubAPIs {
  @Test
  public void testNonExistentURL() throws ClientProtocolException, IOException {
	  HttpGet get = new HttpGet(BASE_URL + "/nonexistingurl");
	  response = client.execute(get);
	  int statusCode = response.getStatusLine().getStatusCode();
	  assertEquals(statusCode, 404);
  }
  @Test
  public void testNotificationsForUnauthenticatedUser() throws ClientProtocolException, IOException {
	  HttpGet get = new HttpGet(BASE_URL + "/user/notifications");
	  response = client.execute(get);
	  int statusCode = response.getStatusLine().getStatusCode();
	  assertEquals(statusCode, 404);
  }
}
