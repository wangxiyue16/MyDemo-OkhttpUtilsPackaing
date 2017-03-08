package com.example.an.networktest;

import android.app.Activity;

import lib.network.NetworkExecutor;
import lib.network.bean.NetworkListener;
import lib.network.bean.NetworkRequest;
import lib.network.bean.NetworkResponse;
import lib.network.error.NetError;

/**
 * description:
 * author: WDSG
 * date: 2017/3/8
 */
public class BaseActivity extends Activity implements INetwok {
    private NetworkExecutor mNetworkExecutor;

    public void exeNetworkRequest(int what, NetworkRequest request) {
        exeNetworkRequest(what, request, this);
    }

    public void exeNetworkRequest(int what, NetworkRequest request, NetworkListener listener) {
        if (isFinishing()) {
            return;
        }
        if (mNetworkExecutor == null) {
            mNetworkExecutor = new NetworkExecutor(getClass().getName(), this);
        }
        mNetworkExecutor.execute(what, request, listener);
    }

    @Override
    public void cancelAllNetworkRequest() {
        if (mNetworkExecutor != null) {
            mNetworkExecutor.cancelAll();
        }
    }

    @Override
    public void cancelNetworkRequest(int tag) {
        if (mNetworkExecutor != null) {
            mNetworkExecutor.cancel(tag);
        }
    }

    @Override
    public Object onNetworkResponse(int id, NetworkResponse nr) {
        return null;
    }

    @Override
    public void onNetworkSuccess(int id, Object result) {

    }

    @Override
    public void onNetworkError(int id, NetError error) {

    }

    @Override
    public void onNetworkProgress(int id, float progress, long totalSize) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mNetworkExecutor != null) {
            mNetworkExecutor.destroy();
            mNetworkExecutor = null;
        }
    }

}
