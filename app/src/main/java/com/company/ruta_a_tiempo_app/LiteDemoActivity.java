package com.company.ruta_a_tiempo_app;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolygonOptions;

/**
 * This demo shows some features supported in lite mode.
 * In particular it demonstrates the use of {@link com.google.android.gms.maps.model.Marker}s to
 * launch the Google Maps Mobile application, {@link com.google.android.gms.maps.CameraUpdate}s
 * and {@link com.google.android.gms.maps.model.Polygon}s.
 */
public class LiteDemoActivity extends AppCompatActivity implements OnMapReadyCallback {

    /*Hda. Del Refugio Pte-Ote & Hda. Santa Martha
    private static final LatLng PBMTY1333 = new LatLng(25.73637314,  -100.3925772);

    Hda. Del Refugio Pte-Ote & Hda. San Nicolás
    private static final LatLng PCMTY1334  = new LatLng(25.73507417, -100.3906368);

    Hda. Del Refugio Pte-Ote & Hda. Del Molino
    private static final LatLng PSMTY1335 = new LatLng(25.73427757, -100.3885763);

    Hda. El Molino Sur-Nte & Hda. Blanca
    private static final LatLng PSMTY1336 = new LatLng(25.73506971, -100.3874768);

    Hda. El Molino Sur-Nte & Hda. Los Pinos
    private static final LatLng PCMTY1337 = new LatLng(25.73628743, -100.3856738);

    Hda. Los Pinos Pte-Ote & Seguridad Social
    private static final LatLng PSMTY1338 = new LatLng(25.73566575, -100.3849789);

    Comisión Tripartita Pte-Ote & Seguridad Social
    private static final LatLng PSMTY0824 = new LatLng(25.7352947, -100.3845871);

    Comisión Tripartita Pte-Ote & Titanio
    private static final LatLng PSMTY0825 = new LatLng(25.73365377, -100.3829025);

    Comisión Tripartita Pte-Ote & Uranio
    private static final LatLng PCMTY0826 = new LatLng(25.73292558, -100.3821128);

    1  De Mayo  Sur-Nte & Comisión Tripartita
    private static final LatLng PSMTY0038 = new LatLng(25.73223098, -100.3808482);

    1  De Mayo  Sur-Nte & Ave. De La Unidad
    private static final LatLng PSMTY0039 = new LatLng(25.73309586, -100.379377);

    1  De Mayo  Sur-Nte & Ruiz Cortines  (40 Mt. Antes)
    private static final LatLng PSMTY0040 = new LatLng(25.73473982, -100.3778059);

    A. Ruiz Cortines Pte-Ote & Tórtola (Fte)
    private static final LatLng PSMTY0184 = new LatLng(25.73398542, -100.3766555);

    A. Ruiz Cortines Pte-Ote & Ave. De La Unidad / Gaviota
    private static final LatLng PBMTY0185 = new LatLng(25.73327642 , -100.3757373);*/

    /**
     * A Polygon with five points in the Norther Territory, Australia.
     */
    private static final LatLng[] POLYGON = new LatLng[]{
            new LatLng(-18.000328, 130.473633), new LatLng(-16.173880, 135.087891),
            new LatLng(-19.663970, 137.724609), new LatLng(-23.202307, 135.395508),
            new LatLng(-19.705347, 129.550781)};

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the layout
        setContentView(R.layout.lite_demo);

        // Get the map and register for the ready callback
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Move the camera to center on Darwin.
     */
    public void showDarwin(View v) {
        // Wait until map is ready
        if (mMap == null) {
            return;
        }

        // Center camera on Adelaide marker
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DARWIN, 10f));
    }

    /**
     * Move the camera to center on Adelaide.
     */
    public void showAdelaide(View v) {
        // Wait until map is ready
        if (mMap == null) {
            return;
        }

        // Center camera on Adelaide marker
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ADELAIDE, 10f));
    }

    /**
     * Move the camera to show all of Australia.
     * Construct a {@link com.google.android.gms.maps.model.LatLngBounds} from markers positions,
     * then move the camera.
     */
    public void showAustralia(View v) {
        // Wait until map is ready
        if (mMap == null) {
            return;
        }

        // Create bounds that include all locations of the map
        /*LatLngBounds.Builder boundsBuilder = LatLngBounds.builder()
                .include(PERTH)
                .include(ADELAIDE)
                .include(MELBOURNE)
                .include(SYDNEY)
                .include(DARWIN)
                .include(BRISBANE);*/

        // Move camera to show all markers and locations
       // mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), 20));
    }

    /**
     * Called when the map is ready to add all markers and objects to the map.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        addMarkers();
        addPolyobjects();

        final View mapView = getSupportFragmentManager().findFragmentById(R.id.map).getView();
        if (mapView.getViewTreeObserver().isAlive()) {
            mapView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                @SuppressWarnings("deprecation") // We use the new method when supported
                @SuppressLint("NewApi") // We check which build version we are using.
                @Override
                public void onGlobalLayout() {
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                        mapView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    } else {
                        mapView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                    showAustralia(null);
                }
            });
        }
    }

    /**
     * Add a Polyline and a Polygon to the map.
     * The Polyline connects Melbourne, Adelaide and Perth. The Polygon is located in the Northern
     * Territory (Australia).
     */
    private void addPolyobjects() {
        /*mMap.addPolyline((new PolylineOptions())
                .add(MELBOURNE, ADELAIDE, PERTH)
                .color(Color.GREEN)
                .width(5f));*/

        mMap.addPolygon(new PolygonOptions()
                .add(POLYGON)
                .fillColor(Color.CYAN)
                .strokeColor(Color.BLUE)
                .strokeWidth(5));
    }

    /**
     * Add Markers with default info windows to the map.
     */
    private void addMarkers() {
        /*mMap.addMarker(new MarkerOptions()
                .position(BRISBANE)
                .title("Brisbane"));

        mMap.addMarker(new MarkerOptions()
                .position(MELBOURNE)
                .title("Melbourne")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        mMap.addMarker(new MarkerOptions()
                .position(SYDNEY)
                .title("Sydney")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        mMap.addMarker(new MarkerOptions()
                .position(ADELAIDE)
                .title("Adelaide")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

        mMap.addMarker(new MarkerOptions()
                .position(PERTH)
                .title("Perth")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));*/

        /*mMap.addMarker(new MarkerOptions()
                .position(PBMTY1333)
                .title("Hda. Del Refugio Pte-Ote & Hda. Santa Martha")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_icon_android_map)));

        mMap.addMarker(new MarkerOptions()
                .position(PCMTY1334)
                .title("Hda. Del Refugio Pte-Ote & Hda. San Nicolás")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_icon_android_map)));

        mMap.addMarker(new MarkerOptions()
                .position(PSMTY1335)
                .title("Hda. Del Refugio Pte-Ote & Hda. Del Molino")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_icon_android_map)));

        mMap.addMarker(new MarkerOptions()
                .position(PSMTY1336)
                .title("Hda. El Molino Sur-Nte & Hda. Blanca")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_icon_android_map)));

        mMap.addMarker(new MarkerOptions()
                .position(PCMTY1337)
                .title("Hda. El Molino Sur-Nte & Hda. Los Pinos")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus_icon_android_map)));*/
    }
}
