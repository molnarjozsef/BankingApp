package features.atmfinder

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import components.GpsPosition
import dev.icerock.moko.geo.LocationTracker
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionsController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import repository.BankingRepository

class AtmFinderViewModel(
    val permissionsController: PermissionsController,
    repository: BankingRepository,
) : ViewModel() {

    private val _loadingState = MutableStateFlow(LoadingState.Initial)
    val loadingState = _loadingState.asStateFlow()

    val locationTracker = getLocationTracker(permissionsController)
    private val _location = MutableStateFlow<GpsPosition?>(null)
    val location = _location.asStateFlow()

    val atms = repository.getAtms()

    init {
        viewModelScope.launch {
            _loadingState.value = LoadingState.WaitingForPermission
            permissionsController.providePermission(Permission.LOCATION)

            _loadingState.value = LoadingState.WaitingForLocation
            locationTracker.startTracking()
            val location = locationTracker.getLocationsFlow().first()
            _location.value = GpsPosition(
                latitude = location.latitude,
                longitude = location.longitude
            )

            _loadingState.value = LoadingState.LoadingAtms
            repository.fetchAtmsIfNeeded(GpsPosition(location.latitude, location.longitude))

            _loadingState.value = LoadingState.Success
        }
    }
}

enum class LoadingState {
    Initial,
    WaitingForPermission,
    WaitingForLocation,
    LoadingAtms,
    Success,
    ErrorNoLocation,
    ErrorNoInternet,
}


expect fun getLocationTracker(permissionsController: PermissionsController): LocationTracker

@Composable
expect fun bindPermissionsController(permissionsController: PermissionsController)

@Composable
expect fun bindLocationTracker(locationTracker: LocationTracker)
