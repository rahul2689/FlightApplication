package com.ixigo.common;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class ParserHelper {

	public <T> T parseResponse(HttpCustomResponse httpCustomResponse,
			Class<T> clz) {

		T baseEntity;
		try {
			baseEntity = new Gson().fromJson(
					httpCustomResponse.getResponseBody(), clz);
		} catch (JsonSyntaxException ex) {
			baseEntity = null;
		}

		return baseEntity;
	}

}