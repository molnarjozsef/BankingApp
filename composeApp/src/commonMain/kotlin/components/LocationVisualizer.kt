package components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import dev.icerock.moko.geo.LatLng

@Composable
expect fun LocationVisualizer(
    position: GpsPosition,
    markers: List<Marker>,
    modifier: Modifier = Modifier,
)

data class Marker(
    val position: GpsPosition,
    val name: String,
)

data class GpsPosition(
    val latitude: Double,
    val longitude: Double,
) {
    fun toLatLng() = LatLng(
        latitude = latitude,
        longitude = longitude
    )

    fun distanceToInMeters(other: GpsPosition): Double =
        toLatLng().distanceTo(other.toLatLng()) * 1000
}

val DefaultPosition = GpsPosition(47.498056, 19.04)
