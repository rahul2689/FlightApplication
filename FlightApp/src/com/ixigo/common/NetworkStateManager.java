package com.ixigo.common;

import android.content.Context;
import android.net.ConnectivityManager;

public class NetworkStateManager {

    public enum NetworkState {
        CONNECTED, DISCONNECTED
    }

    private final Context mContext;
    private NetworkState mNetworkState;

    public NetworkStateManager(Context context) {
        mContext = context;
        mNetworkState = NetworkState.CONNECTED;
    }

    public NetworkState getNetworkState() {
        mNetworkState = getCurrentNetworkState();
        return mNetworkState;
    }

    private NetworkState getCurrentNetworkState() {
        ConnectivityManager conMgr = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        final android.net.NetworkInfo wifi = conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        final android.net.NetworkInfo mobile = conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifi != null && wifi.isConnectedOrConnecting()) {
            return NetworkState.CONNECTED;
        }
        if (mobile != null && mobile.isConnectedOrConnecting()) {
            return NetworkState.CONNECTED;
        }
        return NetworkState.DISCONNECTED;
    }
}
