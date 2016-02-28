package com.company.ruta_a_tiempo_app;

import android.content.res.Resources;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceDetectionApi;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.google.android.gms.maps.CameraUpdateFactory.*;

public class HowToGoActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private TextView mPlaceDetailsText;
    private TextView mPlaceAttribution;

    private GPSTracker gps;
    private UiSettings mUiSettings;

    private AutoCompleteTextView from;

    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_go);

        //PlaceDetectionApi.getCurrentPlace();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Retrieve the place_autocomplete_fragment.
        PlaceAutocompleteFragment autocompleteFragmentTo = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        // Register a listener to receive callbacks when a place has been selected or an error has
        // occurred.
        //autocompleteFragmentTo.setOnPlaceSelectedListener(this);
        autocompleteFragmentTo.setOnPlaceSelectedListener(new PlaceSelectionListener() {
              @Override
              public void onPlaceSelected(Place place) {
                  Log.i("Place Selected:", "Place Selected: " + place.getName());

                  // Retrieve the place_autocomplete_fragment.
                  PlaceAutocompleteFragment autocompleteFragmentTo = (PlaceAutocompleteFragment)
                          getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

                  // Retrieve the place_autocomplete_fragmentfrom.
                  PlaceAutocompleteFragment autocompleteFragmentFrom = (PlaceAutocompleteFragment)
                          getFragmentManager().findFragmentById(R.id.place_autocomplete_fragmentfrom);

                  if ( autocompleteFragmentTo.toString() != "" && autocompleteFragmentFrom.toString() == "" ) {
                      mMap.addMarker(new MarkerOptions().position(place.getLatLng()).title("Marker in" + place.getName().toString()));
                      mMap.moveCamera(newLatLngZoom(place.getLatLng(), 3));
                  } else if ( autocompleteFragmentFrom.toString() != "" && autocompleteFragmentTo.toString() != "" ) {
                      Log.i("autocompleteFragmentFrom", "autocompleteFragmentTo");
                  } else if ( autocompleteFragmentTo.toString() == "" && autocompleteFragmentFrom.toString() != "" ) {
                      Log.i("autocompleteFragmentFrom", "autocompleteFragmentTo Empty");
                  }
              }

            @Override
            public void onError(Status status) {

            }
        });

        // Retrieve the place_autocomplete_fragmentfrom.
        PlaceAutocompleteFragment autocompleteFragmentFrom = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragmentfrom);

        // Register a listener to receive callbacks when a place has been selected or an error has
        // occurred.
        autocompleteFragmentFrom.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {

            }

            @Override
            public void onError(Status status) {

            }
        });

        // Retrieve the TextViews that will display details about the selected place.
        /*mPlaceDetailsText = (TextView) findViewById(R.id.place_details);
        mPlaceDetailsText.setVisibility(View.INVISIBLE);
        mPlaceAttribution = (TextView) findViewById(R.id.place_attribution);
        mPlaceAttribution.setVisibility(View.INVISIBLE);*/
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
        gps = new GPSTracker(HowToGoActivity.this);

        // check if GPS enabled
        if (gps.canGetLocation()) {

            latitude = gps.getLatitude();
            longitude = gps.getLongitude();

            // \n is for new line
            //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }

        LatLng myLocation = new LatLng(latitude, longitude);
        CameraPosition camera = CameraPosition.builder().target(myLocation)
                .zoom(mMap.getMaxZoomLevel() - 4)
                .bearing(0)
                .tilt(45)
                .build();

        //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, mMap.getMaxZoomLevel() - 4));
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camera), 10000, null);
        //mMap.moveCamera(CameraUpdateFactory.newCameraPosition(camera));

        mMap.setMyLocationEnabled(true);
        mMap.setBuildingsEnabled(true);

        mUiSettings=mMap.getUiSettings();
        mUiSettings.setCompassEnabled(true);
        mUiSettings.setTiltGesturesEnabled(true);
        mUiSettings.setMapToolbarEnabled(true);
        mUiSettings.setMyLocationButtonEnabled(true);
    }

    /**
     * Callback invoked when a place has been selected from the PlaceAutocompleteFragment.
     */
    /*@Override
    public void onPlaceSelected(Place place) {
        Log.i("Place Selected:", "Place Selected: " + place.getName());

        // Retrieve the place_autocomplete_fragment.
        PlaceAutocompleteFragment autocompleteFragmentTo = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        // Retrieve the place_autocomplete_fragmentfrom.
        PlaceAutocompleteFragment autocompleteFragmentFrom = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragmentfrom);

        if ( autocompleteFragmentTo.toString() != "" && autocompleteFragmentFrom.toString() == "" ) {
            mMap.addMarker(new MarkerOptions().position(place.getLatLng()).title("Marker in" + place.getName().toString()));
            mMap.moveCamera(newLatLngZoom(place.getLatLng(), 3));
        } else if ( autocompleteFragmentFrom.toString() != "" && autocompleteFragmentTo.toString() != "" ) {
            Log.i("autocompleteFragmentFrom", "autocompleteFragmentTo");
        } else if ( autocompleteFragmentTo.toString() == "" && autocompleteFragmentFrom.toString() != "" ) {
            Log.i("autocompleteFragmentFrom", "autocompleteFragmentTo Empty");
        }

        // Format the returned place's details and display them in the TextView.
        //mPlaceDetailsText.setText(formatPlaceDetails(getResources(), place.getName(), place.getId(),
                //place.getAddress(), place.getPhoneNumber(), place.getWebsiteUri()));

        /*CharSequence attributions = place.getAttributions();
        if (!TextUtils.isEmpty(attributions)) {
            mPlaceAttribution.setText(Html.fromHtml(attributions.toString()));
        } else {
            mPlaceAttribution.setText("");
        }*/
    /*}*/

    /**
     * Callback invoked when PlaceAutocompleteFragment encounters an error.
     */
    /*@Override
    public void onError(Status status) {
        Log.e("onError", "onError: Status = " + status.toString());

        Toast.makeText(this, "Place selection failed: " + status.getStatusMessage(),
                Toast.LENGTH_SHORT).show();
    }*/

    /**
     * Helper method to format information about a place nicely.
     */
    /*private static Spanned formatPlaceDetails(Resources res, CharSequence name, String id,
                                              CharSequence address, CharSequence phoneNumber, Uri websiteUri) {
        //Log.e(TAG, res.getString(R.string.place_details, name, id, address, phoneNumber,
                //websiteUri));
        //return Html.fromHtml(res.getString(R.string.place_details, name, id, address, phoneNumber,
                //websiteUri));
        return Html.fromHtml("<b>Name</b>");
    }*/
}
