package com.ixigo.entities;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class FlightResponseData {

	@SerializedName("airlineMap")
	private AirlineMap mAirlineMap;

	@SerializedName("airportMap")
	private AirportMap mAirportMap;

	@SerializedName("flightsData")
	private List<FlightsData> mFlightsData;

	public FlightResponseData() {
		mAirlineMap = new AirlineMap();
		mAirportMap = new AirportMap();
		mFlightsData = new ArrayList<FlightsData>();
	}

	public AirlineMap getAirlineMap() {
		return mAirlineMap;
	}

	public AirportMap getAirportMap() {
		return mAirportMap;
	}

	public List<FlightsData> getFlightsData() {
		return mFlightsData;
	}
}
