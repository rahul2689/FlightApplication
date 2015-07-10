package com.ixigo.entities;

import com.google.gson.annotations.SerializedName;

public class AirlineMap {

	@SerializedName("SJ")
	private String mSJCodeValue;

	@SerializedName("AI")
	private String mAICodeValue;

	@SerializedName("G8")
	private String mG8CodeValue;

	@SerializedName("JA")
	private String mJACodeValue;

	@SerializedName("IN")
	private String mINCodeValue;

	public AirlineMap() {
		mSJCodeValue = "";
		mAICodeValue = "";
		mG8CodeValue = "";
		mJACodeValue = "";
		mINCodeValue = "";
	}

	public String getSJCodeValue() {
		return mSJCodeValue;
	}

	public String getAICodeValue() {
		return mAICodeValue;
	}

	public String getG8CodeValue() {
		return mG8CodeValue;
	}

	public String getJACodeValue() {
		return mJACodeValue;
	}

	public String getINCodeValue() {
		return mINCodeValue;
	}

}
