package components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier

@Composable
expect fun LocationVisualizer(
    modifier: Modifier,
    markers: List<Marker>,
    title: String,
    parentScrollEnableState: MutableState<Boolean>,
)

data class Marker(
    val position: GpsPosition,
    val name: String,
)

data class GpsPosition(
    val latitude: Double,
    val longitude: Double,
)

val DefaultPosition = GpsPosition(47.498056, 19.04)
