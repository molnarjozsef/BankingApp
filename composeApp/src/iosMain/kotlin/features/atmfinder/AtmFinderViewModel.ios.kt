package features.atmfinder

import androidx.compose.runtime.Composable
import dev.icerock.moko.geo.LocationTracker
import dev.icerock.moko.permissions.PermissionsController

actual fun getLocationTracker(permissionsController: PermissionsController): LocationTracker =
    LocationTracker(permissionsController)

@Composable
actual fun bindPermissionsController(permissionsController: PermissionsController) {
    // no-op
}

@Composable
actual fun bindLocationTracker(locationTracker: LocationTracker) {
}
