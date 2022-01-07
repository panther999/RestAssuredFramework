package com.api.base;
import java.lang.reflect.Method;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.api.utility.Framework;
import com.api.utility.Utility;
import io.restassured.RestAssured;

public class BaseTest {
	
	static {
		Framework.loadFramework();
		Utility.loadapijsonmap();
	}
	
	
	@BeforeClass
	public void process() {
		try {
		System.out.println("This is Beforeclass");
			
			
		
		
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
