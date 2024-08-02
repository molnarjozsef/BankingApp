package components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asAndroidBitmap
import bankingapp.composeapp.generated.resources.Res
import bankingapp.composeapp.generated.resources.map_marker
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import org.jetbrains.compose.resources.imageResource

@Composable
actual fun LocationVisualizer(
    position: GpsPosition,
    markers: List<Marker>,
    modifier: Modifier,
) {
    val cameraPositionState = rememberCameraPositionState()
    LaunchedEffect(position) {
        val positionLatLng = LatLng(position.latitude, position.longitude)
        cameraPositionState.position = CameraPosition.fromLatLngZoom(positionLatLng, 13.5f)
    }
    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState,
        uiSettings = MapUiSettings(
            zoomControlsEnabled = false,
            compassEnabled = false,
            myLocationButtonEnabled = false,
            mapToolbarEnabled = false,
            zoomGesturesEnabled = false,
            scrollGesturesEnabled = false,
        ),
        properties = MapProperties(
            isMyLocationEnabled = true
        )
    ) {
        markers.forEach { marker ->
            MapMarker(
                position = LatLng(marker.position.latitude, marker.position.longitude),
                title = marker.name,
            )
        }
    }
}

@Composable
fun MapMarker(
    position: LatLng,
    title: String,
) {
    val markerState = rememberMarkerState(
        key = position.toString(),
        position = position
    )
    MarkerInfoWindow(
        state = markerState,
        icon = BitmapDescriptorFactory.fromBitmap(imageResource(Res.drawable.map_marker).asAndroidBitmap()),
        title = title,
        onClick = { markerState.showInfoWindow(); true }
    )
}

