package com.example.an.networktest;

import lib.network.bean.NetworkListener;
import lib.network.bean.NetworkRequest;

/**
 * description:
 * author: WDSG
 * date: 2017/3/8
 */
public interface INetwok extends NetworkListener {
    /**
     * @param tag
     * @param request
     */
    void exeNetworkRequest(int tag, NetworkRequest request);

    /**
     * 可以自行设置重试次数及超时时间, 多用于一些需要不断重试的任务
     *
     * @param tag
     * @param request
     * @param listener
     */
    void exeNetworkRequest(int tag, NetworkRequest request, NetworkListener listener);

    /**
     * 取消所有网络任务
     */
    void cancelAllNetworkRequest();

    void cancelNetworkRequest(int tag);
}
