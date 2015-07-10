package com.ixigo.common;

import com.ixigo.HomePage.HomePageDataFeature;

public class FeatureFactory {

	private static FeatureFactory sFeatureFactory;
	private ServiceFactory mServiceFactory;
	private HomePageDataFeature mHomePageDataFeature;

	public FeatureFactory(){
	        mServiceFactory = IxigoApplication.getInstance().getServiceFactory();
	        mHomePageDataFeature = new HomePageDataFeature(mServiceFactory.getHomePageService());
	}
	
	public static FeatureFactory getInstance() {
		if (sFeatureFactory == null) {
			sFeatureFactory = new FeatureFactory();
		}
		return sFeatureFactory;
	}

	public HomePageDataFeature getHomePageDataFeature() {
		return mHomePageDataFeature;
	}
}
