package com.ixigo.common;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.ixigo.HomePage.EntityStatusCodes;

import android.os.StrictMode;

public class HttpUrlConnectionClient {
	private final Device mDevice;
	private final HttpUrlConnectionHelper mHelper;

	public HttpUrlConnectionClient(Device device) {
		mDevice = device;
		mHelper = new HttpUrlConnectionHelper(mDevice);
	}

	public HttpCustomResponse getResponse(String urlString,
			Map<String, String> postParameterMap) {
		String responseString = "";
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		try {
			URL url = new URL(urlString);
			responseString = getResponseFromHttp(url, postParameterMap);

			if (responseString.trim().isEmpty()) {
				return new HttpCustomResponse(responseString,
						EntityStatusCodes.DATA_ERROR);
			} else {
				return new HttpCustomResponse(responseString,
						EntityStatusCodes.OKAY);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new HttpCustomResponse(e.getMessage(),
					EntityStatusCodes.CONNECTION_ERROR);
		}
	}

	private String getResponseFromHttp(URL url,
			Map<String, String> postParameterMap) {
		String responseString = "";
		try {
			HttpURLConnection httpURLConnection = (HttpURLConnection) url
					.openConnection();
			return mHelper.executeRequest(httpURLConnection, postParameterMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseString;
	}
}
