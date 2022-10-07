package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostAPITest extends TestBase{

	TestBase testbase;
	String apiUrl;
	String serviceUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse closeablehttpresponse;
	
	@BeforeMethod
	public void setUp() throws  IOException {
		testbase = new TestBase();
		apiUrl = prop.getProperty("URL");
		serviceUrl = prop.getProperty("serviceURL");
		
		url = apiUrl + serviceUrl;
		
	}
	
	@Test
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException {
		restClient = new RestClient();
		//pass the headers
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		
		//jackson API for converting java object to JSON object
		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("Timberlee", "Manager"); // Expected users object
		
		//Java object to JSON file:
		mapper.writeValue(new File("E:\\eclipseWorkspace\\RestAPITest\\src\\main\\java\\com\\qa\\data\\users.json"), users);
		
		//Java Object to JSON in String:
		String usersJsonString = mapper.writeValueAsString(users);
		System.out.println(usersJsonString);
		
		//call post request
		closeablehttpresponse = restClient.post(url, usersJsonString, headerMap);
		
		//Validate response from POST API
		//1. get Status code
		int statusCode = closeablehttpresponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, TestBase.RESPONSE_STATUS_CODE_201);
		
		//2. JsonString:
		String responseString = EntityUtils.toString(closeablehttpresponse.getEntity(), "UTF-8");
		// convert string to json
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from POST API is "+responseJson);
		
		//json to java object
		Users usersResObj = mapper.readValue(responseString, Users.class); // Actual users object
		System.out.println(usersResObj); 
		
		Assert.assertTrue(users.getName().equals(usersResObj.getName()));
		Assert.assertTrue(users.getJob().equals(usersResObj.getJob()));
		
		System.out.println(usersResObj.getId());
		System.out.println(usersResObj.getCreatedAt());
	}
}
