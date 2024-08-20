package com.jozsefmolnar.bankingapp.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreLocation.CLLocationCoordinate2DMake
import platform.MapKit.MKCoordinateRegionMakeWithDistance
import platform.MapKit.MKMapView
import platform.MapKit.MKPointAnnotation

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun LocationVisualizer(
    position: GpsPosition,
    markers: List<Marker>,
    modifier: Modifier,
) {
    val defaultLocation = CLLocationCoordinate2DMake(position.latitude, position.longitude)

    UIKitView(
        modifier = modifier,
        factory = {
            MKMapView().apply {
                scrollEnabled = false
                zoomEnabled = false
                showsUserLocation = true
            }
        },
        update = { mkMapView ->
            mkMapView.setRegion(
                region = MKCoordinateRegionMakeWithDistance(
                    centerCoordinate = defaultLocation,
                    latitudinalMeters = 1_500.0,
                    longitudinalMeters = 1_500.0,
                ),
                animated = false
            )

            markers.forEach { marker ->
                val location = CLLocationCoordinate2DMake(
                    latitude = marker.position.latitude,
                    longitude = marker.position.longitude
                )

                val annotation = MKPointAnnotation(
                    coordinate = location,
                    title = marker.name,
                    subtitle = null
                )
                mkMapView.addAnnotation(annotation)
            }
        }
    )
}

