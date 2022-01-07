package com.api.utility;


import org.testng.Reporter;
import org.testng.log4testng.Logger;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class RestAssuredRequestFilter implements Filter {
	
	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
			
		Response response = ctx.next(requestSpec, responseSpec);
		
		
		if(Framework.LOGALL.equals("true")) {
			requestSpec.given().log().all();
			requestSpec.when().log().all();
			Reporter.log(requestSpec.getMethod() + " " + requestSpec.getURI() + " => " +
                    response.getStatusCode() + " " + response.getStatusLine());
		}
		
		
		if(Framework.LOGRESPONSE.equals("true")) {
				response.then().log().all();
		}
		
		
		
		
		
		// TODO Auto-generated method stub
		return response;
	}

}
