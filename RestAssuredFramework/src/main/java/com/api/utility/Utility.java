package com.api.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Properties;
import java.util.stream.Collectors;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Utility {
	

	public static JsonElement mapDataJsonElement;
	public static JsonElement mapApiUnderTestDataJsonElement;
	public static JsonObject apiJsonProperties;
	
	
	
	
	public static void loadapijsonmap() {
		InputStream mapApiUnderTestStream = Utility.class.getClass().getResourceAsStream("/api-under-test-map.json");
		BufferedReader brApiUnderTest = new BufferedReader(new InputStreamReader(mapApiUnderTestStream));
		String mapApiUnderTestData = brApiUnderTest.lines().collect(Collectors.joining("\n"));

		InputStream mapStream = Utility.class.getClass().getResourceAsStream("/api-map.json");
		BufferedReader br = new BufferedReader(new InputStreamReader(mapStream));
		String mapData = br.lines().collect(Collectors.joining("\n"));
		try {
			
			mapDataJsonElement = new JsonParser().parse(mapData);
			mapApiUnderTestDataJsonElement = new JsonParser().parse(mapApiUnderTestData);

			br.close();
			mapStream.close();
			
			brApiUnderTest.close();
			mapApiUnderTestStream.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void loadApiProperties(String apiUnderTestname, String env) {
		apiJsonProperties = new JsonObject();
		JsonElement apiUnderTestPropertiesElement = mapApiUnderTestDataJsonElement.getAsJsonObject().get(apiUnderTestname);
		
		String apiname = apiUnderTestPropertiesElement.getAsJsonObject().get("apiname").getAsString();
		
		
		JsonElement propertiesElement = mapDataJsonElement.getAsJsonObject().get(apiname).getAsJsonObject().get(env);
		
		Iterator<String> itProperties = propertiesElement.getAsJsonObject().keySet().iterator();
		while(itProperties.hasNext()) {
			String key = itProperties.next();
			
			if(!propertiesElement.getAsJsonObject().get(key).isJsonNull()) {
				apiJsonProperties.add(key, propertiesElement.getAsJsonObject().get(key));

			}

		}
		
		
		Iterator<String> itapiUnderTestProperties = apiUnderTestPropertiesElement.getAsJsonObject().keySet().iterator();
		while(itapiUnderTestProperties.hasNext()) {
			String key = itapiUnderTestProperties.next();
			if(!apiUnderTestPropertiesElement.getAsJsonObject().get(key).isJsonNull()) {
				apiJsonProperties.add(key, apiUnderTestPropertiesElement.getAsJsonObject().get(key));
			}

		}
	}

}
