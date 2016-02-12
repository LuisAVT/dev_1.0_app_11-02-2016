package com.company.ruta_a_tiempo_app;

import android.os.Looper;
import android.util.Log;

import org.json.*;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class LocalizationsServices {

    private boolean Auth = false;

    RequestParams params = new RequestParams();

    public void getMyLocalization(int ID) throws JSONException {
        params.put("ID", ID);
        LocalizationREST.get("/getLocalization.php", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray userLocalization) {
                // Pull out the first event on the public timeline
                //JSONObject firstEvent = timeline.get(0);
                //String tweetText = firstEvent.getString("text");

                // Do something with the response
                //System.out.println(tweetText);
                System.out.println(userLocalization);
            }
        });
    }

    public void getLocalizations() throws JSONException {
        LocalizationREST.get("/getLocalizations.php", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray localizations) {
                // Pull out the first event on the public timeline
                //JSONObject firstEvent = timeline.get(0);
                //String tweetText = firstEvent.getString("text");

                // Do something with the response
                //System.out.println(tweetText);
                System.out.println(localizations);
            }
        });
    }

    public void postLocalization(int ID, double latitude, double longitude) throws JSONException {
        params.put("idUser", ID);
        params.put("latitude", latitude);
        Log.d("Latitude", String.valueOf(latitude));
        params.put("longitude", longitude);
        Log.d("Longitude", String.valueOf(longitude));

        LocalizationREST.post("/postLocalization.php", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray userLocalization) {
                // Pull out the first event on the public timeline
                //JSONObject firstEvent = timeline.get(0);
                //String tweetText = firstEvent.getString("text");

                // Do something with the response
                //System.out.println(tweetText);
                System.out.println(userLocalization);
            }
        });
    }

    //public void postLogin(String email, String password) throws JSONException {
    public void postLogin(final String email) throws JSONException {
        params.put("nickname", email);
        //params.put("password", password);
        LocalizationREST.post("/UserLogin.php", params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                String nick = null;
                try {
                    nick = response.getString("nickname");
                    //Asignar shared Preferences
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("User nickname JSON", String.valueOf(nick));
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray userData) {
                // Pull out the first event on the public timeline
                JSONObject nick = null;
                try {
                    nick = (JSONObject) userData.get(1);
                    Auth = true;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("User nickname JSON", String.valueOf(nick));
                String NickName = null;
                try {
                    NickName = nick.getString("nickname");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                System.out.println(NickName);
                Log.d("User Info", String.valueOf(NickName));
            }
        });
    }
}