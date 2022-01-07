package com.api.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Framework {
	
	public static String ENV = "prod";
	
	public static String LOGALL =  "true";
	
	public static String LOGRESPONSE = "true";
	
	public static Properties frameworkproperties;
	
	public static void loadFramework() {
		//read framework.properties
			InputStream frameworkStream = Framework.class.getClass().getResourceAsStream("/framework.properties");
			frameworkproperties = new Properties();
			try {
				frameworkproperties.load(frameworkStream);
				frameworkStream.close();
				
				
				
				ENV = frameworkproperties.getProperty("env") == null ? "prod" : frameworkproperties.getProperty("env");
				LOGALL = frameworkproperties.getProperty("log") == null ? "true" : frameworkproperties.getProperty("log");
				LOGRESPONSE = frameworkproperties.getProperty("logResponse") == null ? "prod" : frameworkproperties.getProperty("logResponse");
				
				
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
		
		}
	

}
