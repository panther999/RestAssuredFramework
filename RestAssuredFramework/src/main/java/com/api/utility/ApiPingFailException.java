package com.api.utility;

import org.testng.Assert;

public class ApiPingFailException extends Exception {

	public ApiPingFailException(String message) {
		super(message);
		Assert.fail(message + " Ping fail : Failed the test");
	}
}
