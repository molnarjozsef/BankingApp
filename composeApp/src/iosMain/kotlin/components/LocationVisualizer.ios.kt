package components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
    modifier: Modifier,
    markers: List<Marker>,
    title: String,
    parentScrollEnableState: MutableState<Boolean>,
) {
    val defaultLocation = CLLocationCoordinate2DMake(DefaultPosition.latitude, DefaultPosition.longitude)

    UIKitView(
        modifier = modifier,
        factory = {
            MKMapView()
        },
        update = { mkMapView ->
            mkMapView.setRegion(
                MKCoordinateRegionMakeWithDistance(
                    centerCoordinate = defaultLocation,
                    10_000.0, 10_000.0
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
                    title = null,
                    subtitle = null
                )
                mkMapView.addAnnotation(annotation)
            }
        }
    )
}

