package com.api.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Properties;
import java.util.stream.Collectors;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class Utility {
	
	public static Properties frameworkproperties;
	public static Properties apiproperties;
	public static JsonElement mapDataJsonElement;
	
	static {
		loadFramework();
		loadapijsonmap();
		
	}
	private static void loadFramework() {
	//read framework.properties
		InputStream frameworkStream = Utility.class.getClass().getResourceAsStream("/framework.properties");
		frameworkproperties = new Properties();
		try {
			frameworkproperties.load(frameworkStream);
			frameworkStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	private static void loadapijsonmap() {
		InputStream mapStream = Utility.class.getClass().getResourceAsStream("/api-map.json");
		BufferedReader br = new BufferedReader(new InputStreamReader(mapStream));
		String mapData = br.lines().collect(Collectors.joining("\n"));
		try {
			
			mapDataJsonElement = new JsonParser().parse(mapData);

			br.close();
			mapStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void loadApiProperties(String apiname, String env) {
		apiproperties = new Properties();
		JsonElement propertiesElement = mapDataJsonElement.getAsJsonObject().get(apiname).getAsJsonObject().get(env);
		Iterator<String> itProperties = propertiesElement.getAsJsonObject().keySet().iterator();
		while(itProperties.hasNext()) {
			String key = itProperties.next();
			String value = propertiesElement.getAsJsonObject().get(key).getAsString();
			apiproperties.put(key, value);
		}
	}

}
