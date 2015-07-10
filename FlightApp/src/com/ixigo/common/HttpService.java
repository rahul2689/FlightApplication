package com.ixigo.common;

import java.util.HashMap;

import com.ixigo.HomePage.EntityStatusCodes;
import com.ixigo.common.NetworkStateManager.NetworkState;

public class HttpService {

    private final Device mDevice;
    private HttpUrlConnectionClient mHttpUrlConnectionClient;

    public HttpService(Device device) {
        mDevice = device;
        mHttpUrlConnectionClient = new HttpUrlConnectionClient(mDevice);
    }

    public HttpCustomResponse getHttpResponse(String url) {
        if (mDevice.getNetworkStateManager().getNetworkState().equals(NetworkState.CONNECTED)) {
            url = url.trim().replaceAll("\\s+", "%20");
            return mHttpUrlConnectionClient.getResponse(url, new HashMap<String, String>());
        } else {
            return new HttpCustomResponse("", EntityStatusCodes.NETWORK_ERROR);
        }
    }
}

