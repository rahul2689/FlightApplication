package com.ixigo.HomePage;

import com.ixigo.common.ApiConstants;
import com.ixigo.common.HttpCustomResponse;
import com.ixigo.common.HttpService;
import com.ixigo.common.ParserService;
import com.ixigo.entities.FlightResponseData;

public class HomePageDataService {

	private HttpService mHttpService;
	private ParserService mParserService;

	public HomePageDataService(HttpService httpService,
			ParserService parserService) {
		mHttpService = httpService;
		mParserService = parserService;
	}
	
	public FlightResponseData getFlightResponseDataFromNw(){
		HttpCustomResponse customResponse = mHttpService.getHttpResponse(getFlightDataUrl());
		FlightResponseData flightResponseData = mParserService.getFlightDataResponse(customResponse);
		return flightResponseData;
		
	}
	
	public String getFlightDataUrl() {
		return ApiConstants.FLIGHT_DATA_URL;
	}
}
