package com.bawei.mvp.utils;

import android.content.Context;
import android.icu.text.PluralRules;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bawei.mvp.base.App;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class NetUtils {
    public static boolean Mobile(Context context){
        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo.isAvailable()) {
            return true;
        }else {
            return false;
        }
    }
    private RequestQueue queue;
    private static NetUtils mNetUtils;
    public NetUtils() {
        queue = Volley.newRequestQueue(App.getAppContext());
    }
    private static class SingleInstance{
        private static final NetUtils INSTANCE=new NetUtils();
    }
    public static NetUtils getInstance(){
        return SingleInstance.INSTANCE;
    }
    public void doGet(String url, final ICallBack iCallBack){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                iCallBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iCallBack.onFailure(error.getMessage());
            }
        });
        queue.add(stringRequest);
    }
    public void doPost(String url, final HashMap<String,String> map, final ICallBack iCallBack){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                iCallBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iCallBack.onFailure(error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                return map;
            }
        };
        queue.add(stringRequest);
    }

    public interface ICallBack{
        void onSuccess(String json);
        void onFailure(String msg);
    }
}
