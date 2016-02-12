package com.company.ruta_a_tiempo_app;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

import org.json.*;
import org.json.JSONException;

public class GpsService extends IntentService {
    // GPSTracker class
    GPSTracker gps;

    private double latitude;
    private double longitude;

    /**
     * A constructor is required, and must call the super IntentService(String)
     * constructor with a name for the worker thread.
     */
    public GpsService() {
        super("GPSService");
    }

    /**
     * The IntentService calls this method from the default worker thread with
     * the intent that started the service. When this method returns, IntentService
     * stops the service, as appropriate.
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        // Normally we would do some work here, like download a file.
        // For our sample, we just sleep for 5 seconds.
        /*long endTime = System.currentTimeMillis() + 10000;
        while (System.currentTimeMillis() < endTime) {
            synchronized (this) {
                try {
                    wait(endTime - System.currentTimeMillis());
                } catch (Exception e) {
                }
            }
        }*/

        // create class object
        gps = new GPSTracker(this);

        // check if GPS enabled
        if (gps.canGetLocation()) {

            latitude = gps.getLatitude();
            longitude = gps.getLongitude();

            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }


        synchronized (this) {
            try {
                int ID = 3;
                LocalizationsServices location = new LocalizationsServices();
                location.getLocalizations();
                location.postLocalization(ID, latitude, longitude);
            } catch(JSONException e){
                System.out.println(e);
            }
        }
    }
}