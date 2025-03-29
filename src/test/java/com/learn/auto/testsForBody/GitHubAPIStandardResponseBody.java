package com.learn.auto.testsForBody;

import org.testng.annotations.Test;

import com.learn.auto.parent.ValidationOfGitHubAPIs;
import com.learn.auto.pojo.NonExistentURL;
import com.learn.auto.pojo.User;
import com.learn.auto.util.ResponseUtilities;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;

/*
 * This class contains the tests that all validate data from simple JSON (i.e. without nesting of contents).
 * In other words, all the APIs return simple JSON based response bodies that are parsed and validated accordingly.
 * The very first test uses regular JSONObject to extract the value of a given key and performs the assertion afterwards.
 * The other tests are developed on Jackson libraries to un-marshal (or de-serialize) the JSON response to POJOs.
 */
public class GitHubAPIStandardResponseBody extends ValidationOfGitHubAPIs {
  private ResponseUtilities responseUtilities;
  @Test
  public void testUserResponse() throws ClientProtocolException, IOException {
	  HttpGet get = new HttpGet(BASE_URL + "/users/de2010rht");
	  response = client.execute(get);
	  String body = EntityUtils.toString(response.getEntity());
	  JSONObject jsonObject = new JSONObject(body);
	  assertEquals(jsonObject.get("name"), "Rohitaswa De");
  }
  @Test
  public void testLoginUsingJackson() throws ClientProtocolException, IOException {
	  HttpGet get = new HttpGet(BASE_URL + "/users/de2010rht");
	  response = client.execute(get);
	  User user = responseUtilities.unmarshal(response, User.class);
	  assertEquals(user.getLogin(), "de2010rht");
  }
  @Test
  public void testUserIDUsingJackson() throws ClientProtocolException, IOException {
	  HttpGet get = new HttpGet(BASE_URL + "/users/de2010rht");
	  response = client.execute(get);
	  User user = responseUtilities.unmarshal(response, User.class);
	  assertEquals(user.getId(), 70890264);
  }
  @Test
  public void testNonExistentURLMessageUsingJackson() throws ClientProtocolException, IOException {
	  HttpGet get = new HttpGet(BASE_URL + "/nonexistingurl");
	  response = client.execute(get);
	  NonExistentURL nonExistentURL = responseUtilities.unmarshalGeneric(response, NonExistentURL.class);
	  assertEquals(nonExistentURL.getMessage(), "Not Found");
  }
  @BeforeTest
  public void beforeTest() {
	  responseUtilities = new ResponseUtilities();
  }
}
