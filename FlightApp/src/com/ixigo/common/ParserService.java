package com.ixigo.common;

import com.ixigo.entities.FlightResponseData;

public class ParserService {
	private ParserHelper mParserHelper;

	public ParserService() {
		mParserHelper = new ParserHelper();
	}

	public FlightResponseData getFlightDataResponse(HttpCustomResponse customResponse) {
		return mParserHelper.parseResponse(customResponse, FlightResponseData.class);
	}
	
	
}
