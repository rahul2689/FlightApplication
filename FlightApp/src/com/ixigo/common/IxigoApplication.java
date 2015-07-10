package com.ixigo.common;

import android.app.Application;

public class IxigoApplication extends Application {
	private Device mDevice;
	private static IxigoApplication sInstance;
	private ServiceFactory mServiceFactory;
	private FeatureFactory mFeatureFactory;

	@Override
	public void onCreate() {
		super.onCreate();
		sInstance = this;
		mDevice = Device.getInstance(this);
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				appInit();
			}
		});
		thread.start();
	}

	private void appInit() {
		mServiceFactory = new ServiceFactory();
		mFeatureFactory = FeatureFactory.getInstance();
	}
	
	public static IxigoApplication getInstance() {
        return sInstance;
    }
	
	public Device getDevice() {
        return mDevice;
    }
	
	public ServiceFactory getServiceFactory() {
		return mServiceFactory;
	}

	public FeatureFactory getFeatureFactory() {
		return mFeatureFactory;
	}

}
