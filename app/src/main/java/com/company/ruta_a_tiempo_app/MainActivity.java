package com.company.ruta_a_tiempo_app;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
//import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
//import com.loopj.android.http.AsyncHttpRequest;
import com.loopj.android.http.AsyncHttpResponseHandler;
//import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;
//import com.loopj.android.http.ResponseHandlerInterface;

/*import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;*/

import org.json.JSONException;

import cz.msebera.android.httpclient.Header;
/*import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.client.methods.HttpUriRequest;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HttpContext;*/

public class MainActivity extends AppCompatActivity  {

    // GPSTracker class
    //GPSTracker gps;

    /*private double latitude;
    private double longitude;*/
    //private int ID;

    private boolean IsLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);




        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        Log.d("Prefers", String.valueOf(prefs));

        IsLogin = prefs.getBoolean("IsLogin", false); // get value of last login status

        if(!IsLogin) {   // condition true means user is already login
            //Intent i = new Intent(this, LoginActivity.class);
            //startActivityForResult(i, 1);
            //finish();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();

            //super.onCreate(savedInstanceState); // Always call the superclass first
        } else {
            // condition false take it user on login form

            //new GpsService().onCreate();

            //ID = 2;
            //LocalizationsServices location = new LocalizationsServices();
            new GpsService();

            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
            finish();

            /*try {
                location.postLocalization(ID, latitude, longitude);
                location.getMyLocalization(ID);
            } catch(JSONException e){
                System.out.println(e);
            }*/

            /*RequestParams params = new RequestParams();
            params.put("idUser", 2);
            params.put("latitude", latitude);
            Log.d("Latitude", String.valueOf(latitude));
            params.put("longitude", longitude);
            Log.d("Longitude", String.valueOf(longitude));*/

            /*AsyncHttpClient client = new AsyncHttpClient();
            client.post(this, "http://preview.16aew3023jv8ia4iehm3aou7twigrpb9t32see9prpo2bj4i.box.codeanywhere.com/WebServiceRutaTiempo/postLocation.php", params, new AsyncHttpResponseHandler() {
                @Override
                public void onStart() {
                    // called before request is started
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                    // called when response HTTP status is "200 OK"
                    String Temp = String.valueOf(statusCode);

                    String responseData = String.valueOf(response);

                    Log.d("StatusCode", Temp);
                    Log.d("Header", String.valueOf(headers));
                    Log.d("Response", responseData);
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                    // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                }

                @Override
                public void onRetry(int retryNo) {
                    // called when request is retried
                }
            });*/
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
