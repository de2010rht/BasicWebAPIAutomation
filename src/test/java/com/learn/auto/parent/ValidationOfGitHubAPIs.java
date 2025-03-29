package com.learn.auto.parent;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/*
 * Number of concurrent connections is a point to remember while using Apache HTTP Client.
 * The default in Apache HTTP Client is 2 connections.
 * A test creates one connection to the API and will never release it unless closed; it will be kept open.
 * So, the connection is opened before every test and closed after every test.
 */
public abstract class ValidationOfGitHubAPIs {
  protected static final String BASE_URL = "https://api.github.com";
  protected CloseableHttpClient client;
  protected CloseableHttpResponse response;

  @BeforeMethod
  protected void beforeMethod() {
	  client = HttpClientBuilder.create().build();
  }

  @AfterMethod
  protected void afterMethod() throws IOException {
	  client.close();
	  response.close();
  }
}
