package components

import android.view.MotionEvent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.input.pointer.pointerInteropFilter
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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun LocationVisualizer(
    position: GpsPosition,
    markers: List<Marker>,
    title: String,
    parentScrollEnableState: MutableState<Boolean>,
    modifier: Modifier,
) {
    val defaultPosition = LatLng(position.latitude, position.longitude)
    val cameraPositionState = rememberCameraPositionState()
    LaunchedEffect(position) {
        cameraPositionState.position = CameraPosition.fromLatLngZoom(defaultPosition, 13.5f)
    }
    LaunchedEffect(cameraPositionState.isMoving) {
        // This code helps to use Compose GoogleMap inside scrollable container.
        // Useful code sample: https://github.com/googlemaps/android-maps-compose/blob/abb3e3581681f26316fdd0b8284597f8fc61daa1/app/src/main/java/com/google/maps/android/compose/MapInColumnActivity.kt#L57
        if (!cameraPositionState.isMoving) {
            parentScrollEnableState.value = true
        }
    }
    GoogleMap(
        modifier = modifier.pointerInteropFilter(
            onTouchEvent = {
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        parentScrollEnableState.value = false
                        false
                    }

                    else -> true
                }
            }
        ),
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

