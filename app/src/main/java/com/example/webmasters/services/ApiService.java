package com.example.webmasters.services;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

class ApiService {
    private static ApiService mInstance;
    private RequestQueue mRequestQueue;
    private static Context mContext;

    private ApiService(Context context) {
        mContext = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized ApiService getInstance(Context context) {
        if (mInstance == null)
            mInstance = new ApiService(context);
        return mInstance;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null)
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        return mRequestQueue;
    }
}
