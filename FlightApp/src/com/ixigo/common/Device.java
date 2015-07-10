package com.ixigo.common;

import com.ixigo.common.NetworkStateManager.NetworkState;
import com.ixigo.utils.UiHelper;

import android.content.Context;

public class Device {

	private static Device mInstance;
	private Context mContext;
	private NetworkStateManager mNetworkStateManager;

	private Device(Context context) {
        mContext = context;
        UiHelper.initContext(mContext);
    }

    public static Device getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new Device(context);
        }
        return mInstance;
    }

    public Context getContext() {
        return mContext;
    }

    public NetworkStateManager getNetworkStateManager() {
        if (mNetworkStateManager == null) {
            mNetworkStateManager = new NetworkStateManager(mContext);
        }
        return mNetworkStateManager;
    }

    public boolean isConnected() {
        if (mNetworkStateManager.getNetworkState().equals(NetworkState.CONNECTED)) {
            return true;
        }
        return false;
    }
}
