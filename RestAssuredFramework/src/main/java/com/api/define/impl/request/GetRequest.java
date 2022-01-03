package com.api.define.impl.request;

import com.api.define.ApiRequest;
import com.api.utility.ApiPingFailException;

public class GetRequest extends ApiRequest {

	
	@Override
	public boolean ping() {
		boolean returnval = false;
		try {
		
		int status=this
				.when()
				.get("/" + this.pingpath)
				.andReturn()
				.getStatusCode();
		
	
		returnval = ((status == 200)) ? true: false;

		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return returnval;
	}
	
	public GetRequest() throws ApiPingFailException {
	
			if (ping() == false) {
				throw new ApiPingFailException(this.baseURI + this.basePath + this.pingpath + " Failed to ping");
			}
	
		
	}

}
