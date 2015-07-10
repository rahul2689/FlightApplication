package com.ixigo.entities;

import com.google.gson.annotations.SerializedName;

public class AirportMap {

	@SerializedName("DEL")
	private String mDepartureAirport;

	@SerializedName("MUM")
	private String mArrivalAirport;

	public AirportMap() {
		mDepartureAirport = "";
		mArrivalAirport = "";
	}

	public String getDepartureAirport() {
		return mDepartureAirport;
	}

	public String getArrivalAirport() {
		return mArrivalAirport;
	}

}
