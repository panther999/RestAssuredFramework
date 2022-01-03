package com.api.base;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.stream.Collectors;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.api.utility.Utility;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

import io.restassured.RestAssured;

public class BaseTest {
	@BeforeClass
	public void process() {
		try {
		
			
			
		
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@BeforeMethod
	public void startTest(Method method) {
		System.out.println("STARTING THE TEST OF " + method.getName());
		
	}
	
	@AfterMethod
	public void EndTest(Method method) {
		System.out.println("ENDING THE TEST OF " + method.getName());
		
	}
	
	@AfterClass
	public void restAssuredInstanceTear() {
		RestAssured.reset();
	}

}
