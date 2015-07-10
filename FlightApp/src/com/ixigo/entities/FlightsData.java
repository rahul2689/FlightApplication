package com.ixigo.entities;

import com.google.gson.annotations.SerializedName;

public class FlightsData {

	@SerializedName("originCode")
	private String mOriginCode;

	@SerializedName("destinationCode")
	private String mDestinationCode;

	@SerializedName("takeoffTime")
	private long mTakingOffTime;

	@SerializedName("landingTime")
	private long mLandingTime;

	@SerializedName("price")
	private String mPrice;

	@SerializedName("airlineCode")
	private String mAirlineCode;

	@SerializedName("class")
	private String mFlightClass;

	public FlightsData() {
		mOriginCode = "";
		mDestinationCode = "";
		mPrice = "";
		mAirlineCode = "";
		mFlightClass = "";
	}

	public String getOriginCode() {
		return mOriginCode;
	}

	public String getDestinationCode() {
		return mDestinationCode;
	}

	public long getDepartureTime() {
		return mTakingOffTime;
	}

	public long getArrivalTime() {
		return mLandingTime;
	}

	public String getPrice() {
		return mPrice;
	}

	public String getAirlineCode() {
		return mAirlineCode;
	}

	public String getFlightClass() {
		return mFlightClass;
	}
	
}
