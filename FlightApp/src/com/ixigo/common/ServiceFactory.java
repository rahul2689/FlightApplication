package com.ixigo.common;

import com.ixigo.HomePage.HomePageDataService;

public class ServiceFactory {

	private Device mDevice;
	private HttpService mHttpService;
	private HomePageDataService mHomePageService;
	private ParserService mParserService;

	public ServiceFactory(){
		 mDevice = IxigoApplication.getInstance().getDevice();
		 mHttpService = new HttpService(mDevice);
		 mParserService = new ParserService();
		 mHomePageService = new HomePageDataService(mHttpService, mParserService);
	}

	public HomePageDataService getHomePageService() {
		return mHomePageService;
	}
}
