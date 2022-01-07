package com.api.define;

import java.util.HashMap;
import java.util.Map;

import com.api.utility.RestAssuredRequestFilter;
import com.api.utility.Utility;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.specification.RequestSender;
import io.restassured.specification.RequestSpecification;

public abstract class ApiRequest  {
	
	public String pingpath = "";
		
	public Utility utilities = new Utility();

	public RequestSpecification requestSpecification;
	
	
	

	public boolean ping() {
		return false;
		
	}
	
	public void setBaseParams(JsonElement propertiesData) {
		
		RequestSpecBuilder builder = new RequestSpecBuilder();
		
		
		this.pingpath = propertiesData.getAsJsonObject().get("pingpath").getAsString();
		
		JsonElement headerElement = propertiesData.getAsJsonObject().get("headers");
		Map<String,String> headers = new HashMap<String,String>();
		if(headerElement != null) {
			JsonObject headerObject = headerElement.getAsJsonObject();
			headerObject.keySet().stream().forEach( h->{
				headers.put(h, headerObject.get(h).getAsString());
			});
			builder = builder.addHeaders(headers);
		}
		
		
		JsonElement baseUrlElement = propertiesData.getAsJsonObject().get("baseurl");
		if(baseUrlElement != null) {
			builder = builder.setBaseUri(baseUrlElement.getAsString());
		}
		
		
		JsonElement basePathElement = propertiesData.getAsJsonObject().get("path");
		if(basePathElement != null) {
			builder = builder.setBasePath(basePathElement.getAsString());
		}
		
		JsonElement basePortElement = propertiesData.getAsJsonObject().get("port");
		if(basePortElement != null) {
			builder = builder.setPort(basePortElement.getAsInt());
		}
		
		
		
		
		
		this.requestSpecification =builder.build();
		System.out.println("ReqSpecification is set");

	}
	
	public ApiRequest(String apiUnderTestname, String env){
		String classname = apiUnderTestname;
		utilities.loadApiProperties(classname, env);
		this.setBaseParams(Utility.apiJsonProperties);
		
	}
	
	public RequestSpecification api() {
		RestAssured.reset();
		return RestAssured.given().filter(new RestAssuredRequestFilter()).spec(this.requestSpecification);
	}
	
	
	
	
	
	
}
