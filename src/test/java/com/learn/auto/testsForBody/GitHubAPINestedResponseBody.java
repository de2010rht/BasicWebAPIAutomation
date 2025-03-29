package com.learn.auto.testsForBody;

import org.testng.annotations.Test;

import com.learn.auto.parent.ValidationOfGitHubAPIs;
import com.learn.auto.pojo.RateLimit;
import com.learn.auto.util.ResponseUtilities;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.testng.annotations.BeforeClass;

/*
 * This class contains the tests that validate data from a nested JSON.
 * Here, the API returns a response whose body contains several nested JSON structures.
 * The tests only validate a few fields that are present in the nested components.
 */
public class GitHubAPINestedResponseBody extends ValidationOfGitHubAPIs {
  private ResponseUtilities responseUtilities;
  @Test
  public void testRateLimitUsingJackson() throws ClientProtocolException, IOException {
	  HttpGet get = new HttpGet(BASE_URL + "/rate_limit");
	  response = client.execute(get);
	  RateLimit rateLimit = responseUtilities.unmarshalGeneric(response, RateLimit.class);
	  assertEquals(rateLimit.getCoreLimit(), 60);
  }
  @Test
  public void testSearchLimitUsingJackson() throws ClientProtocolException, IOException {
	  HttpGet get = new HttpGet(BASE_URL + "/rate_limit");
	  response = client.execute(get);
	  RateLimit rateLimit = responseUtilities.unmarshalGeneric(response, RateLimit.class);
	  assertEquals(rateLimit.getSearchLimit(), 10);
  }
  @BeforeClass
  public void beforeClass() {
	  responseUtilities = new ResponseUtilities();
  }
}
