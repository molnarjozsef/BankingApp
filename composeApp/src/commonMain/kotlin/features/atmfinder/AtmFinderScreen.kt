package features.atmfinder

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.Accessible
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.House
import androidx.compose.material.icons.outlined.MoneyOff
import androidx.compose.material.icons.outlined.NaturePeople
import androidx.compose.material.icons.outlined.NotAccessible
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import bankingapp.composeapp.generated.resources.Res
import bankingapp.composeapp.generated.resources.atm_finder_default_atm_name
import bankingapp.composeapp.generated.resources.atm_finder_title
import components.BackButton
import components.GpsPosition
import components.Header
import components.LocationVisualizer
import components.Marker
import model.domain.Atm
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import theme.AppTheme
import theme.dp16
import theme.dp24
import theme.dp8


@Composable
fun AtmFinderScreen(
    navController: NavHostController,
) {
    val viewModel = koinViewModel<AtmFinderViewModel>()

    AtmFinderScreenContent(
        atms = viewModel.atms.collectAsState().value,
        navigateUp = navController::navigateUp,
    )
}

@Composable
fun AtmFinderScreenContent(
    atms: List<Atm>?,
    navigateUp: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {
            Header(
                title = stringResource(Res.string.atm_finder_title),
                startButton = { BackButton(onClick = navigateUp) },
            )
        }
    ) { contentPadding ->
        Box(Modifier.padding(contentPadding)) {
            if (atms == null) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = AppTheme.colors.main)
                }
            } else {
                val density = LocalDensity.current
                var topPadding by remember { mutableIntStateOf(0) }
                LocationVisualizer(
                    modifier = Modifier.fillMaxWidth().aspectRatio(2f)
                        .onGloballyPositioned {
                            topPadding = it.size.height
                        },
                    markers = atms.take(10).map { atm ->
                        Marker(
                            position = GpsPosition(atm.lat, atm.lon),
                            name = atm.name ?: ""
                        )
                    },
                    title = "Budapest",
                    parentScrollEnableState = remember { mutableStateOf(false) },
                )
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        vertical = dp24 + with(density) { topPadding.toDp() },

                        )
                ) {
                    atms.forEachIndexed { index, atm ->
                        item {
                            Box(
                                modifier = Modifier
                                    .background(AppTheme.colors.backgroundNeutral)
                                    .padding(horizontal = dp16)
                                    .padding(
                                        top = if (index == atms.indices.first) dp16 else dp8,
                                        bottom = if (index == atms.indices.last) dp16 else dp8
                                    )
                            ) {
                                Atm(
                                    atm = atm,
                                    index = index
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun Atm(
    atm: Atm,
    index: Int,
) {
    Surface(
        shadowElevation = dp8,
        color = AppTheme.colors.surfaceNeutral,
        shape = RoundedCornerShape(dp8),
    ) {
        Row(
            modifier = Modifier.padding(dp16).fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(dp8),
        ) {
            AtmPinIcon(index = index)
            Column {
                Text(
                    text = atm.name ?: stringResource(Res.string.atm_finder_default_atm_name),
                    fontSize = 13.sp,
                    color = AppTheme.colors.textDarker
                )
                atm.getFullAddress()?.let { fullAddress ->
                    Text(
                        text = fullAddress,
                        fontSize = 10.sp,
                        color = AppTheme.colors.textDarker,
                    )
                }
                Text(
                    text = "${atm.lat}, ${atm.lon}",
                    fontSize = 10.sp,
                    color = AppTheme.colors.textDarker,
                )
                AtmFeatures(atm = atm)
            }
        }
    }
}

@Composable
private fun AtmPinIcon(
    index: Int,
) {
    val isIndexed = (index + 1) in 1..5

    Box(contentAlignment = Alignment.Center) {
        Icon(
            imageVector = if (isIndexed) {
                Icons.Filled.Circle
            } else {
                Icons.Filled.LocationOn
            },
            tint = AppTheme.colors.textDarker,
            contentDescription = null
        )
        if (isIndexed) {
            Text(
                text = (index + 1).toString(),
                fontSize = 8.sp,
                color = AppTheme.colors.backgroundNeutral
            )
        }
    }
}

@Composable
private fun AtmFeatures(
    atm: Atm,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(dp8)
    ) {
        if (atm.wheelchair != null) {
            Icon(
                imageVector = if (atm.wheelchair) {
                    Icons.Outlined.Accessible
                } else {
                    Icons.Outlined.NotAccessible
                },
                tint = AppTheme.colors.main,
                contentDescription = null
            )
        }
        if (atm.indoor != null) {
            Icon(
                imageVector = if (atm.indoor) {
                    Icons.Outlined.House
                } else {
                    Icons.Outlined.NaturePeople
                },
                tint = AppTheme.colors.main,
                contentDescription = null
            )
        }
        if (atm.cashIn != null) {
            Icon(
                imageVector = if (atm.cashIn) {
                    Icons.Outlined.AttachMoney
                } else {
                    Icons.Outlined.MoneyOff
                },
                tint = AppTheme.colors.main,
                contentDescription = null
            )
        }

    }
}

private fun Atm.getFullAddress(): String? =
    if (postcode == null && city == null && street == null && houseNumber == null) {
        null
    } else {
        listOfNotNull(postcode, city, street, houseNumber).joinToString(" ")
    }
