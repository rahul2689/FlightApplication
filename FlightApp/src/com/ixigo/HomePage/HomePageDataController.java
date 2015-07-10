package com.ixigo.HomePage;

import com.ixigo.common.IxigoApplication;
import com.ixigo.entities.FlightResponseData;

public class HomePageDataController {

	private static final String NETWORK_ERROR = "Network Error";
	private HomePageDataFeature mHomePageDataFeature;

	public HomePageDataController() {
		mHomePageDataFeature = IxigoApplication.getInstance()
				.getFeatureFactory().getHomePageDataFeature();
	}

	public void getFlightResponseData(
			final ICallback<FlightResponseData, String> callback) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				FlightResponseData flightResponseData = mHomePageDataFeature
						.getFlightResponseData();
				if (flightResponseData != null) {
					callback.success(flightResponseData);
				}else{
					callback.failure(NETWORK_ERROR);
				}
			}
		};
		thread.start();
	}

}
