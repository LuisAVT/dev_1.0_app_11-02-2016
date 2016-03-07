package com.company.ruta_a_tiempo_app;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity
                                        implements OnMapReadyCallback,
                                        NavigationView.OnNavigationItemSelectedListener {

    private GoogleMap mMap;
    private GPSTracker gps;
    private UiSettings mUiSettings;

    private AutoCompleteTextView from;

    private double latitude;
    private double longitude;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    private static final LatLng PBMTY1333 = new LatLng(25.73637314,  -100.3925772);
    private static final LatLng PCMTY1334  = new LatLng(25.73507417, -100.3906368);
    private static final LatLng PSMTY1335 = new LatLng(25.73427757, -100.3885763);
    private static final LatLng PSMTY1336 = new LatLng(25.73506971, -100.3874768);
    private static final LatLng PCMTY1337 = new LatLng(25.73628743, -100.3856738);
    private static final LatLng PSMTY1338 = new LatLng(25.73566575, -100.3849789);
    private static final LatLng PSMTY0824 = new LatLng(25.7352947, -100.3845871);
    private static final LatLng PSMTY0825 = new LatLng(25.73365377, -100.3829025);
    private static final LatLng PCMTY0826 = new LatLng(25.73292558, -100.3821128);
    private static final LatLng PSMTY0038 = new LatLng(25.73223098, -100.3808482);
    private static final LatLng PSMTY0039 = new LatLng(25.73309586, -100.379377);
    private static final LatLng PSMTY0040 = new LatLng(25.73473982, -100.3778059);
    private static final LatLng PSMTY0184 = new LatLng(25.73398542, -100.3766555);
    private static final LatLng PBMTY0185 = new LatLng(25.73327642 , -100.3757373);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent markerDemo = new Intent(MapsActivity.this, MarkerDemoActivity.class);
                Intent markerDemo = new Intent(MapsActivity.this, LiteListDemoActivity.class);
                //Intent howToGo = new Intent(MapsActivity.this, HowToGoActivity.class);
                startActivity(markerDemo);
                finish();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fab.setFocusable(false);
        fab.setFocusableInTouchMode(false);
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



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {

            Intent RMEActivity = new Intent(MapsActivity.this,RMEActivity.class);
            startActivity(RMEActivity);

            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

            Intent FavsActivity = new Intent(MapsActivity.this, FavsActivity.class);
            startActivity(FavsActivity);

        } else if (id == R.id.nav_slideshow) {

            Intent HelpActivity = new Intent(MapsActivity.this, HelpActivity.class);
            startActivity(HelpActivity);

        } else if (id == R.id.nav_manage) {

            Intent ConfigurationActivity = new Intent(MapsActivity.this, ConfigurationActivity.class);
            startActivity(ConfigurationActivity);

        } else if (id == R.id.nav_share) {

            Intent ShareActivity = new Intent(MapsActivity.this, ShareActivity.class);
            startActivity(ShareActivity);

        } else if (id == R.id.nav_cali) {

            Intent QualifyActivity = new Intent(MapsActivity.this, QualifyActivity.class);
            startActivity(QualifyActivity);

        } else if (id == R.id.nav_confi) {

            Intent SettingsActivity = new Intent(MapsActivity.this, SettingsActivity.class);
            startActivity(SettingsActivity);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // create class object
        gps = new GPSTracker(MapsActivity.this);

        // check if GPS enabled
        if (gps.canGetLocation()) {

            latitude = gps.getLatitude();
            longitude = gps.getLongitude();

            //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }

        LatLng myLocation = new LatLng(latitude, longitude);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, mMap.getMaxZoomLevel() - 4));
        mMap.setMyLocationEnabled(true);
        mMap.setBuildingsEnabled(true);

        mUiSettings=mMap.getUiSettings();
        mUiSettings.setCompassEnabled(true);
        mUiSettings.setTiltGesturesEnabled(true);
        mUiSettings.setMapToolbarEnabled(true);
        mUiSettings.setMyLocationButtonEnabled(true);
        //mUiSettings.setZoomControlsEnabled(true);

        /*if ( getactualLocation <> myLocation) {
            mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
        }*/

        addMarkers();
        //mMap.addMarker(new MarkerOptions().position(myLocation).title("My Actual Location").icon(BitmapDescriptorFactory.fromResource(R.drawable.cast_ic_notification_0)));

        mUiSettings.isCompassEnabled();
        mUiSettings.isMapToolbarEnabled();





        /*TextView output = (TextView) findViewById(R.id.textView1);
        String strJson="
        {
            \"Employee\" :[
            {
                \"id\":\"01\",
                \"name\":\"Gopal Varma\",
                \"salary\":\"500000\"
            },
            {
                \"id\":\"02\",
                \"name\":\"Sairamkrishna\",
                \"salary\":\"500000\"
            },
            {
                \"id\":\"03\",
                \"name\":\"Sathish kallakuri\",
                \"salary\":\"600000\"
            }
            ]
        }";*/
        /*String data = "";
        try {
            JSONObject  jsonRootObject = new JSONObject(strJson);

            //Get the instance of JSONArray that contains JSONObjects
            JSONArray jsonArray = jsonRootObject.optJSONArray("Employee");

            //Iterate the jsonArray and print the info of JSONObjects
            for(int i=0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                int id = Integer.parseInt(jsonObject.optString("id").toString());
                String name = jsonObject.optString("name").toString();
                float salary = Float.parseFloat(jsonObject.optString("salary").toString());

                data += "Node"+i+" : \n id= "+ id +" \n Name= "+ name +" \n Salary= "+ salary +" \n ";
            }
            output.setText(data);
        } catch (JSONException e) {e.printStackTrace();}*/
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Maps Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.company.ruta_a_tiempo_app/http/host/path")
        );

        from = (AutoCompleteTextView) findViewById(R.id.from);
        //to = (AutoCompleteTextView) findViewById(R.id.to);

        from.setText("Búsqueda...");
        //to.setText("The Moscone Center, Howard Street, San Francisco, CA, United States");

        from.setAdapter(new PlacesAutoCompleteAdapter(this, android.R.layout.simple_dropdown_item_1line));
        //to.setAdapter(new PlacesAutoCompleteAdapter(this, android.R.layout.simple_dropdown_item_1line));

        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Maps Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.company.ruta_a_tiempo_app/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    /**
     * Add Markers with default info windows to the map.
     **/
    private void addMarkers() {
        mMap.addMarker(new MarkerOptions()
                .position(PBMTY1333)
                .title("Hda. Del Refugio Pte-Ote & Hda. Santa Martha")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)));

        mMap.addMarker(new MarkerOptions()
                .position(PCMTY1334)
                .title("Hda. Del Refugio Pte-Ote & Hda. San Nicolás")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)));

        mMap.addMarker(new MarkerOptions()
                .position(PSMTY1335)
                .title("Hda. Del Refugio Pte-Ote & Hda. Del Molino")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)));

        mMap.addMarker(new MarkerOptions()
                .position(PSMTY1336)
                .title("Hda. El Molino Sur-Nte & Hda. Blanca")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)));

        mMap.addMarker(new MarkerOptions()
                .position(PCMTY1337)
                .title("Hda. El Molino Sur-Nte & Hda. Los Pinos")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)));

        mMap.addMarker(new MarkerOptions()
                .position(PSMTY1338)
                .title("Hda. Los Pinos Pte-Ote & Seguridad Social")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)));

        mMap.addMarker(new MarkerOptions()
                .position(PSMTY0824)
                .title("Comisión Tripartita Pte-Ote & Seguridad Social")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_stop)));

        mMap.addMarker(new MarkerOptions()
                .position(PSMTY0825)
                .title("Comisión Tripartita Pte-Ote & Titanio"));

        mMap.addMarker(new MarkerOptions()
                .position(PCMTY0826)
                .title("1  De Mayo  Sur-Nte & Comisión Tripartita")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        mMap.addMarker(new MarkerOptions()
                .position(PSMTY0038)
                .title("1  De Mayo  Sur-Nte & Comisión Tripartita")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        mMap.addMarker(new MarkerOptions()
                .position(PSMTY0039)
                .title("1  De Mayo  Sur-Nte & Ave. De La Unidad")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

        mMap.addMarker(new MarkerOptions()
                .position(PSMTY0040)
                .title("1  De Mayo  Sur-Nte & Ruiz Cortines  (40 Mt. Antes)")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));

        mMap.addMarker(new MarkerOptions()
                .position(PSMTY0184)
                .title("A. Ruiz Cortines Pte-Ote & Tórtola (Fte)")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));

        mMap.addMarker(new MarkerOptions()
                .position(PBMTY0185)
                .title("A. Ruiz Cortines Pte-Ote & Ave. De La Unidad / Gaviota")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
    }
}
