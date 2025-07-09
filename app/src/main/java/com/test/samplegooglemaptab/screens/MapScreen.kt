package com.test.samplegooglemaptab.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.test.samplegooglemaptab.viewmodel.MapViewModel

@Composable
fun MapScreen(viewModel: MapViewModel = viewModel()) {
    val colleges by viewModel.colleges.collectAsState()
    val singapore = LatLng(1.3521, 103.8198)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore,15f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        colleges.forEach { college ->
            val lat = college.lat.toDoubleOrNull() ?: return@forEach
            val lon = college.lon.toDoubleOrNull() ?: return@forEach

            Marker(
                state = MarkerState(position = LatLng(lat,lon)),
                title = college.display_name
            )
        }
    }
}