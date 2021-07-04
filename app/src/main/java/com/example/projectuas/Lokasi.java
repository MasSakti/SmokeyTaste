package com.example.projectuas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

public class Lokasi extends AppCompatActivity {

    MapView mv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));
        setContentView(R.layout.activity_lokasi);

        IconFactory iconFactory = IconFactory.getInstance(Lokasi.this);
        Icon icnew = iconFactory.fromResource(R.drawable.marker);
        mv = (MapView) findViewById(R.id.map_layout);
        mv.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                mapboxMap.setStyle(new Style.Builder().fromUri("mapbox://styles/mapbox/outdoors-v11"));
                MarkerOptions options1 = new MarkerOptions();
                options1.title("Smokey Taste");
                options1.icon(icnew);
                options1.position(new LatLng(-7.928280, 112.602863));
                mapboxMap.addMarker(options1);

                MarkerOptions options2 = new MarkerOptions();
                options2.title("Smokey Taste");
                options2.icon(icnew);
                options2.position(new LatLng(-7.908275, 112.597346));
                mapboxMap.addMarker(options2);

                MarkerOptions options3 = new MarkerOptions();
                options3.title("Smokey Taste");
                options3.icon(icnew);
                options3.position(new LatLng(-7.941117, 112.622685));
                mapboxMap.addMarker(options3);

                MarkerOptions options4 = new MarkerOptions();
                options4.title("Smokey Taste");
                options4.icon(icnew);
                options4.position(new LatLng(-7.917173, 112.588716));
                mapboxMap.addMarker(options4);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mv.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mv.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mv.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mv.onResume();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mv.onLowMemory();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mv.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mv.onSaveInstanceState(outState);
    }
}