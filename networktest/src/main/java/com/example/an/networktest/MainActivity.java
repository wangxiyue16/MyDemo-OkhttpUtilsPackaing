package com.example.an.networktest;

import android.os.Bundle;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import lib.network.bean.NetworkRequest;
import lib.network.bean.NetworkResponse;

/**
 * LibNetWork库的小Demo,数据解析并没有写完,只是单纯展示一下库的使用,欢迎把解析补充完整
 */
public class MainActivity extends BaseActivity{
    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    public void init(){
        initData();
    }

    public void initData(){
        getDataFromNet();
    }

    public void getDataFromNet(){
        String url = "http://test.xdyapi.haodai.net/Wallet/getAccountList?os_type=1" +
                "&appid=1&imei=867614023363542&app_version=35000&channel=web&auth_tms=20170308124838&auth_did=5793&" +
                "auth_dsig=0d9883732e6f07b7&auth_uid=193594&auth_usig=3dcf6f44ae6b2b63?os_type=1&appid=1&imei=867614023363542" +
                "&app_version=35000&channel=" +
                "web&auth_tms=20170308124838&auth_did=5793&auth_dsig=0d9883732e6f07b7&auth_uid=193594&auth_usig=3dcf6f44ae6b2b63";
        NetworkRequest task = NetworkRequest.newPost(url);
        task.addParam("xid", "193594");

        exeNetworkRequest(1, task);
    }

    //普通方式的onNetworkResponse
//    @Override
//    public Object onNetworkResponse(int id, NetworkResponse nr) {
///*        System.out.println("nr==="+nr.getText());
//        Repo repo = new Repo();
//        try {
//            parseMakeMoneyPage(nr.getText(), repo);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return repo;*/
//    }

    //普通方式的onNetworkSuccess
//    @Override
//    public void onNetworkSuccess(int id, Object result) {
//        Repo repo = (Repo) result;
//        System.out.println("result==="+repo.getRs_msg()+"===="+repo.getRs_code());
//    }

    //使用Gson的onNetworkResponse
    @Override
    public Object onNetworkResponse(int id, NetworkResponse nr) {
        Gson gson = new Gson();
        MakeMoney makeMoney = gson.fromJson(nr.getText(), MakeMoney.class);
        return makeMoney;
    }

    //使用Gson的onNetworkSuccess
    @Override
    public void onNetworkSuccess(int id, Object result) {
        MakeMoney makeMoney = (MakeMoney) result;
        System.out.println("result==="+makeMoney.getRs_msg()+"===="+makeMoney.getRs_code()+"===="+makeMoney.getDetails().getRemain()
                +"===="+makeMoney.getDetails().getIncome().get(1).getTitle()
                +"===="+makeMoney.getDetails().getInform().get(1));
    }

    public void parseMakeMoneyPage(String text, Repo repo) throws JSONException {

        JSONObject object = new JSONObject(text);
        if (object == null) {
            return;
        }

        repo.setRs_code(getInt(object, "rs_code"));
        repo.setRs_msg(getString(object, "rs_msg"));
    }

    public static int getInt(JSONObject json, String tag) {
        int ret = 0;
        try {
            ret = json.optInt(tag);
        } catch (Exception e) {
        }
        return ret;
    }

    public static String getString(JSONObject json, String tag) {
        String ret = null;
        try {
            ret = json.optString(tag);
        } catch (Exception e) {
        }

        return ret;
    }
}
