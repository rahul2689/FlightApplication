package com.ixigo.HomePage;

import com.ixigo.entities.FlightResponseData;

public class HomePageDataFeature {

	private HomePageDataService mHomePageService;

	public HomePageDataFeature(HomePageDataService homePageService) {
		mHomePageService = homePageService;
	}

	public FlightResponseData getFlightResponseData(){
		return mHomePageService.getFlightResponseDataFromNw();
	}
}
