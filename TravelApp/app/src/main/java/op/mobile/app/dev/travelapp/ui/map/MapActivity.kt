package op.mobile.app.dev.travelapp.ui.map

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.clustering.ClusterManager
import op.mobile.app.dev.travelapp.R

class MapActivity : AppCompatActivity() , OnMapReadyCallback {
    private val TAG = MapActivity::class.java.simpleName
    lateinit var gmap : GoogleMap
    var isMapStyle =false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        gmap = googleMap
        setMapStyle(googleMap)

        val companies = CompanyJSONReader(this).read()
        val clusterManager: ClusterManager<Company> = ClusterManager(this, googleMap)
        val markerCluster = CompanyMarkerCluster(this, googleMap, clusterManager)
        clusterManager.renderer = markerCluster
        clusterManager.addItems(companies)
        clusterManager.cluster()
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(companies[0].position, 10f))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_map_style, menu)
        return true
    }

    private fun setMapStyle(map: GoogleMap) {

        try {
            val success = map.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    this,
                    R.raw.map_style_retro
                )
            )
            if (!success) {
                Log.e(TAG, "Style parsing failed.")
            }
        } catch (e: Resources.NotFoundException) {
            Log.e(TAG, "Can't find style. Error:", e)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.changestyle -> {

                isMapStyle  = if (isMapStyle) {

                    gmap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style_retro))
                    false
                }
                else {
                    gmap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style_dark))
                    true
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}