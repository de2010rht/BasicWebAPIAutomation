package com.learn.auto.pojo;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RateLimit {
	private int coreLimit;
	private int searchLimit;
	
	@SuppressWarnings("unchecked")
	@JsonProperty("resources")
	private void unmarshalAndInitialize(Map<String, Object> map) {
		Map<String, Object> core = (Map<String, Object>) map.get("core");
		coreLimit = Integer.parseInt(core.get("limit").toString());
		
		Map<String, Object> search = (Map<String, Object>) map.get("search");
		searchLimit = Integer.parseInt(search.get("limit").toString());
	}
	
	/**
	 * @return the coreLimit
	 */
	public int getCoreLimit() {
		return coreLimit;
	}
	/**
	 * @return the searchLimit
	 */
	public int getSearchLimit() {
		return searchLimit;
	}
	
	/*
	 * The API : https://api.github.com/rate_limit
	 * Original response body of the API
		{
		  "resources": {
		    "core": {
		      "limit": 60,
		      "remaining": 39,
		      "reset": 1742744954,
		      "used": 21,
		      "resource": "core"
		    },
		    "graphql": {
		      "limit": 0,
		      "remaining": 0,
		      "reset": 1742745769,
		      "used": 0,
		      "resource": "graphql"
		    },
		    "integration_manifest": {
		      "limit": 5000,
		      "remaining": 5000,
		      "reset": 1742745769,
		      "used": 0,
		      "resource": "integration_manifest"
		    },
		    "search": {
		      "limit": 10,
		      "remaining": 10,
		      "reset": 1742742229,
		      "used": 0,
		      "resource": "search"
		    }
		  },
		  "rate": {
		    "limit": 60,
		    "remaining": 39,
		    "reset": 1742744954,
		    "used": 21,
		    "resource": "core"
		  }
		}
	 */
}
