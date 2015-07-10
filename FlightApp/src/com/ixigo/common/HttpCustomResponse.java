package com.ixigo.common;

import com.ixigo.HomePage.EntityStatusCodes;

public class HttpCustomResponse {
	private final EntityStatusCodes mErrorCode;
	private String mResponseBody;

	public HttpCustomResponse(String responseBody, EntityStatusCodes errorCode) {
		mResponseBody = responseBody;
		mErrorCode = errorCode;
	}

	public String getResponseBody() {
		return mResponseBody;
	}

	public EntityStatusCodes getErrorCode() {
		return mErrorCode;
	}
}
