package com.bengisusahin.days_15

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.bengisusahin.days_15.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val galata = LatLng(41.025573,28.9715537)
        mMap.addMarker(MarkerOptions().position(galata).title("Marker in Galata Tower"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(galata, 15f))

        val karakoy = LatLng(41.0216919,28.9723472)
        mMap.addMarker(MarkerOptions().position(karakoy).title("Marker in Karakoy"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(karakoy))

        // marker pin click
        mMap.setOnInfoWindowClickListener {
            if (it.id == "m0") {
                val url = "https://maps.app.goo.gl/pvneVYNWzmgYD1B47"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            } else if (it.id == "m1") {
                val gsm = "sms:05311111111"
                val intent = Intent(Intent.ACTION_SENDTO, Uri.parse(gsm))
                intent.putExtra("sms_body", "Hello from Karakoy")
                startActivity(intent)
            }
        }
    }
}