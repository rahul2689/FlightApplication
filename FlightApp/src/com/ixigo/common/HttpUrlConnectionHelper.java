package com.ixigo.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.Map;

import org.json.JSONException;

public class HttpUrlConnectionHelper {
	private final Device mDevice;
    private static final String ENCODING_FORMAT = "UTF-8";
    private static final int CONN_TIMEOUT = 2 * 60 * 1000;
    private static final int READ_TIMEOUT = 1 * 60 * 1000;

    public HttpUrlConnectionHelper(Device device) {
        mDevice = device;
    }

    public String executeRequest(HttpURLConnection httpURLConnection, Map<String, String> postParameterMap) {
        String responseString = "";
        InputStream inputStream = null;
        setRequestParameter(httpURLConnection, postParameterMap);
        try {
            inputStream = httpURLConnection.getInputStream();
            responseString = readStream(inputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return responseString;
    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        String result = "";
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuffer stringBuffer = new StringBuffer("");
            String line = "";
            String LineSeparator = System.getProperty("line.separator");

            while ((line = reader.readLine()) != null) {
                stringBuffer.append(line + LineSeparator);
                // mLogger.logD("HttpUrlConnectionHelper", "Response: " + line);
            }
            result = stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
            result = e.getMessage();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    private String getPostParameterString(Map<String, String> postParameterMap) throws JSONException,
            UnsupportedEncodingException {
        String entity = "";
        for (String key : postParameterMap.keySet()) {
            entity += key + "=" + postParameterMap.get(key) + "&";
        }
        return entity.substring(0, entity.length() - 1);
    }

    private void setRequestParameter(HttpURLConnection httpURLConnection, Map<String, String> postParameterMap) {
        httpURLConnection.setConnectTimeout(CONN_TIMEOUT);
        httpURLConnection.setReadTimeout(READ_TIMEOUT);
        try {
            if (postParameterMap.size() > 0) {
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream output = httpURLConnection.getOutputStream();
                output.write(getPostParameterString(postParameterMap).toString().getBytes(ENCODING_FORMAT));
            } else {
                httpURLConnection.setDoInput(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
