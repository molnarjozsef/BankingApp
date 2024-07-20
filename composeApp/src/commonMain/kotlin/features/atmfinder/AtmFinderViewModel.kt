package features.atmfinder

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import components.GpsPosition
import dev.icerock.moko.geo.LocationTracker
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionsController
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import repository.BankingRepository

class AtmFinderViewModel(
    val permissionsController: PermissionsController,
    repository: BankingRepository,
) : ViewModel() {

    val locationTracker = getLocationTracker(permissionsController)
    val location = locationTracker.getLocationsFlow()
        .map {
            GpsPosition(
                latitude = it.latitude,
                longitude = it.longitude
            )
        }
        .onEach {
            println("`````` locvm: $it")
            locationTracker.stopTracking()
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    val atms = repository.getAtms()

    init {
        viewModelScope.launch {
            permissionsController.providePermission(Permission.LOCATION)
            locationTracker.startTracking()
            val location = locationTracker.getLocationsFlow().first()
            repository.fetchAtmsIfNeeded(GpsPosition(location.latitude, location.longitude))
        }
    }
}


expect fun getLocationTracker(permissionsController: PermissionsController): LocationTracker

@Composable
expect fun bindPermissionsController(permissionsController: PermissionsController)

@Composable
expect fun bindLocationTracker(locationTracker: LocationTracker)
