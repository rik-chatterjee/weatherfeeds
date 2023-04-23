package com.rikChatterjee.weatherfeeds.data.location

import android.app.Application
import android.location.Location
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.rikChatterjee.weatherfeeds.domain.location.LocationTracker
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class DefaultLocationTracker @Inject constructor(
    private val locationClient : FusedLocationProviderClient,
    private val application: Application
) : LocationTracker {
    override suspend fun getCurrentLocation(): Location? {

        val hasAccessFineLocation = ContextCompat.checkSelfPermission(
            application,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED

        val hasAccessCoarseLocation = ContextCompat.checkSelfPermission(
            application,Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

        val locationManager = application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        if(!hasAccessCoarseLocation || !hasAccessFineLocation || !isGpsEnabled){
            return null
        }

        return suspendCancellableCoroutine { cont ->

            locationClient.lastLocation.apply {
                if (isComplete){
                    if(isSuccessful){
                        cont.resume(result)
                    } else{
                        cont.resume(null)
                    }
                    return@suspendCancellableCoroutine
                }
                addOnSuccessListener {
                    cont.resume(it)
                }
                addOnFailureListener{
                    cont.resume(null)
                }
                addOnCanceledListener {
                    cont.cancel()
                }
            }
        }
    }
}