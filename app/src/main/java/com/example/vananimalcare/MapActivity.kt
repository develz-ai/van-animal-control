package com.example.vananimalcare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

   lateinit var myMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(gMap: GoogleMap) {
        myMap = gMap!!
        val latLng = LatLng(49.2537719,-123.1454474)


        val markerOptions : MarkerOptions =
            MarkerOptions().position(latLng).title("QEP")

        myMap.addMarker(markerOptions)

        myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11.0f))

        myMap.setOnMarkerClickListener {
            marker -> marker.showInfoWindow()
            true
        }
    }
}