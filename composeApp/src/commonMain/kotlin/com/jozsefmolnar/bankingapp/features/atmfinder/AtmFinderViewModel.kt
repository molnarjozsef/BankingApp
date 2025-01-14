package com.jozsefmolnar.bankingapp.features.atmfinder

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jozsefmolnar.bankingapp.components.GpsPosition
import com.jozsefmolnar.bankingapp.repository.AtmFinderRepository
import dev.icerock.moko.geo.LocationTracker
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionsController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class AtmFinderViewModel(
    val permissionsController: PermissionsController,
    atmFinderRepository: AtmFinderRepository,
) : ViewModel() {

    private val _loadingState = MutableStateFlow(LoadingState.Initial)
    val loadingState = _loadingState.asStateFlow()

    val locationTracker = getLocationTracker(permissionsController)
    private val _location = MutableStateFlow<GpsPosition?>(null)
    val location = _location.asStateFlow()

    val atms = atmFinderRepository.getAtms()

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
            atmFinderRepository.fetchAtmsIfNeeded(GpsPosition(location.latitude, location.longitude))

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
