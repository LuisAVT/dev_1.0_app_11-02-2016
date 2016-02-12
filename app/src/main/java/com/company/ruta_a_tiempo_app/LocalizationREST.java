package com.company.ruta_a_tiempo_app;

import android.os.Looper;

import com.loopj.android.http.*;

public class LocalizationREST {
    private static final String BASE_URL = "http://preview.16aew3023jv8ia4iehm3aou7twigrpb9t32see9prpo2bj4i.box.codeanywhere.com/WebServiceRutaTiempo";

    public static AsyncHttpClient syncHttpClient= new SyncHttpClient();
    public static AsyncHttpClient client = new AsyncHttpClient();

    private static AsyncHttpClient getClient()
    {
        // Return the synchronous HTTP client when the thread is not prepared
        if (Looper.myLooper() == null) {
            return syncHttpClient;
        } else {
            return client;
        }
    }

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        getClient().get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        getClient().post(getAbsoluteUrl(url), params, asyncHttpResponseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

}