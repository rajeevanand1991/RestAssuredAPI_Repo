package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;


public class GetAPITest extends TestBase {
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
	
	@Test(priority=1)
	public void getAPITestWithoutHeaders() throws  IOException {
		restClient = new RestClient();
		closeablehttpresponse = restClient.get(url);
		// get status code
				int  statusCode= closeablehttpresponse.getStatusLine().getStatusCode();
				System.out.println("Status Code ===> " + statusCode);
				Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"Status code is not 200");
				
				// get json response string message
				String responseString = EntityUtils.toString(closeablehttpresponse.getEntity(), "UTF-8");

				// convert string to json
				JSONObject responseJson = new JSONObject(responseString);
				System.out.println("JSON Response from API ==> " +responseJson);
			
				//Single value assertion
				//validating per_page field
				String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
				System.out.println("value of per page is =====> "+perPageValue);
				Assert.assertEquals(Integer.parseInt(perPageValue), 6);
				
				
				//validating per_page field
				String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
				System.out.println("value of total is =====> "+totalValue);
				Assert.assertEquals(Integer.parseInt(totalValue), 12);
				
				//Get the value from JSON Array
				String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
				String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
				String firstName = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
				String email = TestUtil.getValueByJPath(responseJson, "/data[0]/email");
				System.out.println("get lastName ============> "+lastName);
				System.out.println("get Avatar ===============> "+avatar);
				System.out.println("get firstName ============> "+firstName);
				System.out.println("get email ================> "+email);
				Assert.assertEquals(lastName, "Bluth");
				
				//get all Header details
				Header[] HeadersArray = closeablehttpresponse.getAllHeaders();
				
				//To store all headers we use hashmap to fetch by K and V
				HashMap<String,String> allHeaders = new HashMap<String, String>();
				
				for(Header header : HeadersArray) {
					allHeaders.put(header.getName(), header.getValue());
				}
				
				System.out.println("Headers Array ----> " +allHeaders);
				//String headerTransfer_Encoding = TestUtil.getValueByJPath(allHeaders.get(0), "Transfer-Encoding=chunked");
				System.out.println(allHeaders.get("Transfer-Encoding"));
				Assert.assertEquals(allHeaders.get("Transfer-Encoding"), "chunked");
				//System.out.println("Header for Transfer-Encoding ========> "+headerTransfer_Encoding);
	}
	
	@Test(priority=2)
	public void getAPITestWithHeaders() throws  IOException {
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		
		closeablehttpresponse = restClient.get(url,headerMap);
		// get status code
				int  statusCode= closeablehttpresponse.getStatusLine().getStatusCode();
				System.out.println("Status Code ===> " + statusCode);
				Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"Status code is not 200");
				
				// get json response string message
				String responseString = EntityUtils.toString(closeablehttpresponse.getEntity(), "UTF-8");

				// convert string to json
				JSONObject responseJson = new JSONObject(responseString);
				System.out.println("JSON Response from API ==> " +responseJson);
			
				//Single value assertion
				//validating per_page field
				String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
				System.out.println("value of per page is =====> "+perPageValue);
				Assert.assertEquals(Integer.parseInt(perPageValue), 6);
				
				
				//validating per_page field
				String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
				System.out.println("value of total is =====> "+totalValue);
				Assert.assertEquals(Integer.parseInt(totalValue), 12);
				
				//Get the value from JSON Array
				String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
				String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
				String firstName = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
				String email = TestUtil.getValueByJPath(responseJson, "/data[0]/email");
				System.out.println("get lastName ============> "+lastName);
				System.out.println("get Avatar ===============> "+avatar);
				System.out.println("get firstName ============> "+firstName);
				System.out.println("get email ================> "+email);
				Assert.assertEquals(lastName, "Bluth");
				
				//get all Header details
				Header[] HeadersArray = closeablehttpresponse.getAllHeaders();
				
				//To store all headers we use hashmap to fetch by K and V
				HashMap<String,String> allHeaders = new HashMap<String, String>();
				
				for(Header header : HeadersArray) {
					allHeaders.put(header.getName(), header.getValue());
				}
				
				System.out.println("Headers Array ----> " +allHeaders);
	}

}
