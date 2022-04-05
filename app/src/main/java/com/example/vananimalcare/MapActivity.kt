package com.example.vananimalcare

import android.content.pm.PackageManager
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception
import java.util.jar.Manifest

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    lateinit var myMap: GoogleMap

    val descriptions: MutableList <String> = mutableListOf()

    fun populateData(){
        descriptions.add("ADDRESS: 1500 W Georgia St, Vancouver, BC V6G 2Z6 \n\n" +
                "Animal Protection Organization \n\n" +
                "CONTACT: (604) 780-4840")
        descriptions.add("ADDRESS: 2028 Wall St, Vancouver, BC V5L 1B1 \n\n" +
            "At VOKRA, we rescure, heal then place cats - first in our network of foster homes, then in carefully matched, " +
                "loving, forever homes. We're a passionate, volunteer-driven community with extensive cat care experience. \n\n"+
                "CONTACT: (604) 731-2913")
        descriptions.add("1280 Raymur Ave, Vancouver, BC V6A 3L8 \n\n" +
            "Our goal is to protect and safeguard the health of the community, while promoting responsible pet ownership.\n\n"+
                "CONTACT: (604) 873-7000")
        descriptions.add("ADDRESS: North building, 1205 E 7th Ave, Vancouver, BC V5T 1R1 \n\n" +
            "Speaking for Animals \n\n"+
                "CONTACT: (604) 879-7721")
        descriptions.add("ADDRESS: 800 Hamilton St, Vancouver, BC V6B 6M2 \n\n" +
            "NHDRS is a registered dog rescue organization based in British Columbia, Canada. We are committed to finding loving " +
                "homes for stray or unwanted dogs and puppies from remote Canadian communities.\n\n"+
                "WEB: northernhope.ca")
    }

    var currentLat = 0.0
    var currentLng = 0.0

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val fragmentViewModel : FragmentViewModel by viewModels()
    private val fragmentViewModelB : FragmentViewModelB by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        populateData()

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        fetchLocation()

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun fetchLocation() {
        val task = fusedLocationProviderClient.lastLocation

        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 101)
            return
        }
        task.addOnSuccessListener {
            if(it != null){
                Toast.makeText(applicationContext, "Current Location: ${it.latitude} ${it.longitude}", Toast.LENGTH_SHORT). show()
                currentLat = (it.latitude)
                currentLng = (it.longitude)
                println(it.latitude)
                println(it.longitude)
            }
        }
    }

    override fun onMapReady(gMap: GoogleMap) {
        myMap = gMap!!
        val start = LatLng(49.2537719,-123.1454474)
        val caare = LatLng(49.2819373,-123.1161111)
        val vokra = LatLng(49.2778326,-123.1000548)
        val van_animal_services = LatLng(49.2770792,-123.0904556)
        val scpa = LatLng(49.2693092,-123.0961574)
        val nhdrs = LatLng(49.276861,-123.1179032)
        val currentLocation = LatLng(currentLat, currentLng)

        //val markerOptions0 : MarkerOptions = MarkerOptions().position(start).title("QEP")
        val markerOptions1 : MarkerOptions = MarkerOptions().position(caare).title("CAARE")
        val markerOptions2 : MarkerOptions = MarkerOptions().position(vokra).title("VOKRA - Vancouver Orphan Kitten Rescue")
        val markerOptions3 : MarkerOptions = MarkerOptions().position(van_animal_services).title("City of Vancouver Animal Services")
        val markerOptions4 : MarkerOptions = MarkerOptions().position(scpa).title("BC SCPA Vancouver Community Animal Centre")
        val markerOptions5 : MarkerOptions = MarkerOptions().position(nhdrs).title("Northern Hope Dog Rescue Society")

        val markerOptions0 : MarkerOptions = MarkerOptions().position(currentLocation).title("Current Location")

        myMap.addMarker(markerOptions1)
        myMap.addMarker(markerOptions2)
        myMap.addMarker(markerOptions3)
        myMap.addMarker(markerOptions4)
        myMap.addMarker(markerOptions5)

        //myMap.addMarker(markerOptions0)

        myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(start, 12.0f))

        myMap.setOnMarkerClickListener { marker ->

            fragmentViewModel.setData(marker.title.toString())
            if(marker.title.toString() == "CAARE"){
                fragmentViewModelB.setData(descriptions[0])
            }
            else if(marker.title.toString() == "VOKRA - Vancouver Orphan Kitten Rescue"){
                fragmentViewModelB.setData(descriptions[1])
            }
            else if(marker.title.toString() == "City of Vancouver Animal Services"){
                fragmentViewModelB.setData(descriptions[2])
            }
            else if(marker.title.toString() == "BC SCPA Vancouver Community Animal Centre"){
                fragmentViewModelB.setData(descriptions[3])
            }
            else if(marker.title.toString() == "Northern Hope Dog Rescue Society"){
                fragmentViewModelB.setData(descriptions[4])
            }


            val dialog = FragmentMap()
            dialog.show(supportFragmentManager, "CustomDialog")
            true
        }

        //myMap.addPolyline(PolylineOptions().clickable(true).add(currentLocation, vokra))
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

//    fun getDirectionURL(origin:LatLng, dest:LatLng) : String{
//        return "https://maps.googleapis.com/maps/api/directions/json?origin=${origin.latitude},${origin.longitude}&destination=${dest.latitude},${dest.longitude}&sensor=false&mode=driving"
//    }

//    inner class GetDirection(val url : String) : AsyncTask<Void, Void, List<List<LatLng>>>(){
//        override fun doInBackground(vararg p0: Void?): List<List<LatLng>> {
//            val client = OkHttpClient()
//            val request = Request.Builder().url(url).build()
//            val response = client.newCall(request). execute()
//            val data = response.body.toString()
//            val result = ArrayList<List<LatLng>>()
//
//            try{
//                val respObj =
//            }catch (e:Exception){
//
//            }
//            return result
//        }
//
//    }

