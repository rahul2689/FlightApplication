package com.ixigo.common;

import com.ixigo.HomePage.IConfiguration;

public class CommonConfig implements IConfiguration{

	@Override
	public String getFlightDataUrl() {
		return ApiConstants.FLIGHT_DATA_URL;
	}
}
