package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import org.json.JSONObject;

public class RestClient {
	// GET method without Headers
	public CloseableHttpResponse get(String url) throws IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// http get request
		HttpGet httpget = new HttpGet(url); 
		
		// Hit the GET URL
		CloseableHttpResponse closeablehttpresponse = httpclient.execute(httpget); 
		
		return closeablehttpresponse;
	}
		// GET method with Headers
		public CloseableHttpResponse get(String url, HashMap<String, String> headerMap) throws IOException {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			// http get request
			HttpGet httpget = new HttpGet(url); 
			
			for(Map.Entry<String, String> entry: headerMap.entrySet()) {
				httpget.addHeader(entry.getKey(),entry.getValue());
			}
			
			// Hit the GET URL
			CloseableHttpResponse closeablehttpresponse = httpclient.execute(httpget); 
			
			return closeablehttpresponse;				
}
		//POST method
		public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap ) throws ClientProtocolException, IOException {
			CloseableHttpClient httpclient = HttpClients.createDefault();
			// http POST request
			HttpPost httpPost = new HttpPost(url);
			
			//define payload entity/request body
			httpPost.setEntity(new StringEntity(entityString));
			
			//for Headers
			for(Map.Entry<String, String> entry: headerMap.entrySet()) {
				httpPost.addHeader(entry.getKey(),entry.getValue());
			}
			
			//Hit request
			CloseableHttpResponse closeablehttpresponse = httpclient.execute(httpPost);
			return closeablehttpresponse;
			
		} 	


}
