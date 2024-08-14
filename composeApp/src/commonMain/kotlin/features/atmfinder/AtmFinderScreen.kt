package features.atmfinder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import bankingapp.composeapp.generated.resources.Res
import bankingapp.composeapp.generated.resources.atm_finder_loading_loading_atms
import bankingapp.composeapp.generated.resources.atm_finder_loading_waiting_for_location
import bankingapp.composeapp.generated.resources.atm_finder_loading_waiting_for_permission
import bankingapp.composeapp.generated.resources.atm_finder_title
import components.BackButton
import components.DefaultPosition
import components.GpsPosition
import components.Header
import components.LocationVisualizer
import components.Marker
import dev.icerock.moko.permissions.PermissionsController
import model.domain.Atm
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import theme.AppTheme
import theme.dp16
import theme.dp8

@Composable
fun AtmFinderScreen(
    navController: NavHostController,
) {
    val viewModel = koinViewModel<AtmFinderViewModel> {
        parametersOf(getPermissionsController())
    }
    val location = viewModel.location.collectAsState(null).value
    val loadingState by viewModel.loadingState.collectAsState()
    val atms = viewModel.atms.collectAsState().value

    bindPermissionsController(viewModel.permissionsController)
    bindLocationTracker(viewModel.locationTracker)

    when (loadingState) {
        LoadingState.Initial -> FullScreenLoading()
        LoadingState.WaitingForPermission ->
            FullScreenLoading(stringResource(Res.string.atm_finder_loading_waiting_for_permission))

        LoadingState.WaitingForLocation ->
            FullScreenLoading(stringResource(Res.string.atm_finder_loading_waiting_for_location))

        LoadingState.LoadingAtms ->
            FullScreenLoading(stringResource(Res.string.atm_finder_loading_loading_atms))

        LoadingState.Success ->
            AtmFinderScreenContent(
                location = location,
                atms = atms ?: emptyList(),
                navigateUp = navController::navigateUp,
            )

        LoadingState.ErrorNoLocation -> TODO()
        LoadingState.ErrorNoInternet -> TODO()
    }
}

@Composable
fun FullScreenLoading(
    text: String? = null,
    isOverlay: Boolean = false,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                AppTheme.colors.backgroundNeutral.copy(alpha = if (isOverlay) 0.9f else 1f)
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularProgressIndicator(color = AppTheme.colors.main)
        text?.let {
            Text(
                text = text,
                fontSize = 16.sp,
                color = AppTheme.colors.textDarker,
            )
        }
    }
}

@Composable
fun AtmFinderScreenContent(
    location: GpsPosition?,
    atms: List<Atm>,
    navigateUp: () -> Unit,
) {
    Scaffold(
        topBar = {
            Header(
                title = stringResource(Res.string.atm_finder_title),
                startButton = { BackButton(onClick = navigateUp) },
            )
        }
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier.padding(contentPadding),
            contentPadding = PaddingValues(bottom = dp16)
        ) {
            item {
                LocationVisualizer(
                    position = location ?: DefaultPosition,
                    modifier = Modifier.fillMaxWidth().aspectRatio(1.5f),
                    markers = atms.take(10).map { atm ->
                        Marker(
                            position = GpsPosition(atm.lat, atm.lon),
                            name = atm.name ?: ""
                        )
                    },
                )
            }
            atms.sortedBy { atm ->
                location?.distanceToInMeters(atm.getLocation())
            }
                .forEachIndexed { index, atm ->
                    item {
                        Atm(
                            atm = atm,
                            index = index,
                            distanceInMeters = location?.distanceToInMeters(atm.getLocation()),
                            modifier = Modifier
                                .padding(horizontal = dp16)
                                .padding(
                                    top = if (index == atms.indices.first) dp16 else dp8,
                                    bottom = if (index == atms.indices.last) dp16 else dp8
                                )
                        )
                    }
                }
        }
    }
}


expect fun getPermissionsController(): PermissionsController
