package com.example.vananimalcare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.jar.Manifest

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    lateinit var myMap: GoogleMap

    private val fragmentViewModel : FragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        //ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 100)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(gMap: GoogleMap) {
        myMap = gMap!!
        val start = LatLng(49.2537719,-123.1454474)
        val caare = LatLng(49.2819373,-123.1161111)
        val vokra = LatLng(49.2778326,-123.1000548)
        val van_animal_services = LatLng(49.2770792,-123.0904556)
        val scpa = LatLng(49.2693092,-123.0961574)


        //val markerOptions0 : MarkerOptions = MarkerOptions().position(start).title("QEP")
        val markerOptions1 : MarkerOptions = MarkerOptions().position(caare).title("CAARE")
        val markerOptions2 : MarkerOptions = MarkerOptions().position(vokra).title("VOKRA - Vancouver Orphan Kitten Rescue")
        val markerOptions3 : MarkerOptions = MarkerOptions().position(van_animal_services).title("City of Vancouver Animal Services")
        val markerOptions4 : MarkerOptions = MarkerOptions().position(scpa).title("BC SCPA Vancouver Community Animal Centre")

        myMap.addMarker(markerOptions1)
        myMap.addMarker(markerOptions2)
        myMap.addMarker(markerOptions3)
        myMap.addMarker(markerOptions4)

        myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(vokra, 13.0f))

        myMap.setOnMarkerClickListener { marker ->

            fragmentViewModel.setData(marker.title.toString())

            val dialog = FragmentMap()
            dialog.show(supportFragmentManager, "CustomDialog")
            true
        }
    }

}

//ARCHIVED BACKUP CODE

//        if(savedInstanceState == null) {
//            supportFragmentManager
//                .beginTransaction()
//                .add(
//                    R.id.fragmentContainer,
//                    FragmentMap.newInstance(),
//                    "MyTag"
//                ).commit()
//        }

//            if (marker.isInfoWindowShown) {
//                marker.hideInfoWindow()
//
//            } else {
//                marker.showInfoWindow()
//
//
//            }

//            if(marker.title == "CAARE"){
//                println("caare")
//
//                fragmentViewModel.setData(title.toString())
//
////                intent.putExtra("title", "testing1")
////                startActivity(intent)
//
////                val bundle = Bundle()
////                bundle.putString("message", "test1")
////
////                val transaction = this.supportFragmentManager.beginTransaction()
////                val fragmentMap = FragmentMap()
////                fragmentMap.arguments = bundle
////
////                transaction.replace(R.id.shelterTitle, fragmentMap)
////                transaction.commit()
//            } else if(marker.title == "VOKRA - Vancouver Orphan Kitten Rescue") {
//                fragmentViewModel.setData("text1")
//            } else {
//                println("nothing")
//            }

