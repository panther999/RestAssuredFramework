package com.api.define;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.api.utility.Utility;

import io.restassured.RestAssured;

public abstract class ApiRequest extends RestAssured {
	
	public String base_uri ="";
	
	public String path = "";
	
	public String pingpath = "";
	
	//public static RestAssured restassured;
	
	public Utility utilities = new Utility();

	
	public void setBaseParams(String base_uri, String path, String pingpath) {

		this.base_uri = base_uri;
		this.path = path;
		this.pingpath = pingpath;
		
		this.baseURI = this.base_uri;
		this.basePath = this.path;
		
	}
	
	public boolean ping() {
		return false;
		
	}
	
	public ApiRequest(){
		String classname = this.getClass().getName().split("\\.")[1];
		utilities.loadApiProperties(classname, Utility.frameworkproperties.getProperty("env"));
		this.setBaseParams(utilities.apiproperties.getProperty("baseurl"), utilities.apiproperties.getProperty("path"), utilities.apiproperties.getProperty("pingpath"));
	}
	
	public void get(Map<String,?> allParam) {
		
	}
}
