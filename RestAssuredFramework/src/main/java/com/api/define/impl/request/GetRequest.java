package com.api.define.impl.request;

import com.api.define.ApiRequest;
import com.api.utility.ApiPingFailException;

import io.restassured.RestAssured;

public class GetRequest extends ApiRequest {

	
	@Override
	public boolean ping() {
		boolean returnval = false;
		try {
		
		int status=RestAssured.given().spec(this.requestSpecification)
				.when()
				.get(this.pingpath)
				.andReturn()
				.getStatusCode();
		
	
		returnval = ((status == 200)) ? true: false;
		System.out.println("Ping status received : " + status);
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return returnval;
	}
	

	public GetRequest(String apiUnderTestname,String env) throws ApiPingFailException {
		super(apiUnderTestname,env);
		if (ping() == false) {
			throw new ApiPingFailException(RestAssured.baseURI + ":" + RestAssured.port + RestAssured.basePath + this.pingpath);
		}
	}

}
