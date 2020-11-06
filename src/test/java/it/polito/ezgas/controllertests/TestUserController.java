package it.polito.ezgas.controllertests;

import static org.junit.jupiter.api.Assertions.*;
import com.google.gson.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;
import net.minidev.json.JSONObject;

class TestUserController {

	@Test
	void testGetUserById() throws ClientProtocolException, IOException{
		HttpUriRequest request = new HttpGet("http://localhost:8080/user/getUser/1");
		
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		System.out.println(response.getStatusLine().getStatusCode());
		
		String jsonFromResponse = EntityUtils.toString(response.getEntity());
		
//		assert(response.getStatusLine().getStatusCode()==200);
		
		System.out.println(jsonFromResponse);
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		User u1 = mapper.readValue(jsonFromResponse, User.class);
		
		assert(u1.getUserId()==1);
				
	}
	
	@Test
	void testGetAllUsers() throws ClientProtocolException, IOException{
		HttpUriRequest request = new HttpGet("http://localhost:8080/user/getAllUsers");
	
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
	
		String jsonFromResponse = EntityUtils.toString(response.getEntity());
		
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		User[] user = mapper.readValue(jsonFromResponse,User[].class);
		
		System.out.println(user[0].getUserId() + " " + user[0].getUserName()+ " " +user.length);
		System.out.println(response.getStatusLine().getStatusCode());
		System.out.println(user.length);
		assert(response.getStatusLine().getStatusCode() == 200);
		
		
	}
	
	@Test
	void testDeleteUser() throws ClientProtocolException, IOException{
		HttpUriRequest request = new HttpDelete("http://localhost:8080/user/deleteUser/6");
	
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
	
		//String jsonFromResponse = EntityUtils.toString(response.getEntity());
		
		//ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//User[] user = mapper.readValue(jsonFromResponse,User[].class);
		
		//System.out.println(user[0].getUserId() + " " + user[0].getUserName()+ " " +user.length);
		System.out.println(response.getStatusLine().getStatusCode());
		assert(response.getStatusLine().getStatusCode() == 200);		
		
	}
	
	@Test
	void testIncreaseUserReputation() throws ClientProtocolException, IOException{
		HttpUriRequest request = new HttpPost("http://localhost:8080/user/increaseUserReputation/4");
	
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
	
		System.out.println(response);
		assert(response.getStatusLine().getStatusCode() == 200);		
		
	}
	
	@Test
	void testDecreaseUserReputation() throws ClientProtocolException, IOException{
		HttpUriRequest request = new HttpPost("http://localhost:8080/user/decreaseUserReputation/4");
	
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
	
		System.out.println(response);
		assert(response.getStatusLine().getStatusCode() == 200);		
		
	}

	@Test
	void testSaveUser() throws ClientProtocolException, IOException{
//		CloseableHttpClient client = HttpClients.createDefault();
	    HttpPost httpPost = new HttpPost("http://localhost:8080/user/saveUser");
	    Gson gson = new Gson();
	    UserDto u = new UserDto(null,"abcdefg", "password", "mail@try2.com", 2);
	    String json = gson.toJson(u);
	    
	    StringEntity entity = new StringEntity(json);
	    httpPost.setEntity(entity);
	    httpPost.setHeader("Accept", "application/json");
	    httpPost.setHeader("Content-type", "application/json");
	    
	    HttpResponse response = HttpClientBuilder.create().build().execute(httpPost);
//	    CloseableHttpResponse response = client.execute(httpPost);
	    System.out.println(response);
	    System.out.println(response.getStatusLine().getStatusCode());
	    assert(response.getStatusLine().getStatusCode()==200);
//	    client.close();		
	}
	@Test
	void testLogin() throws ClientProtocolException, IOException{
//		CloseableHttpClient client = HttpClients.createDefault();
	    HttpPost httpPost = new HttpPost("http://localhost:8080/user/login");
	    Gson gson = new Gson();
//	    UserDto u = new UserDto(9,"abcdefg", "password", "mail@try.com", 2);
	    IdPw id = new IdPw("admin@ezgas.com","admin");
	    String json = gson.toJson(id);
	    
	    StringEntity entity = new StringEntity(json);
	    
	    httpPost.setEntity(entity);
	    httpPost.setHeader("Accept", "application/json");
	    httpPost.setHeader("Content-type", "application/json");
	    
	    HttpResponse response = HttpClientBuilder.create().build().execute(httpPost);
//	    CloseableHttpResponse response = client.execute(httpPost);
	    System.out.println(response);
	    System.out.println(response.getStatusLine().getStatusCode());
	    assert(response.getStatusLine().getStatusCode()==200);
//	    client.close();		
	}
	
}