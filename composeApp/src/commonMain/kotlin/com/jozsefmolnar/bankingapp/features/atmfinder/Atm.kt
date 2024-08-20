package com.jozsefmolnar.bankingapp.features.atmfinder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import bankingapp.composeapp.generated.resources.Res
import bankingapp.composeapp.generated.resources.atm_finder_default_atm_name
import bankingapp.composeapp.generated.resources.atm_finder_distance_meters
import com.jozsefmolnar.bankingapp.model.domain.Atm
import org.jetbrains.compose.resources.stringResource
import com.jozsefmolnar.bankingapp.theme.AppTheme
import com.jozsefmolnar.bankingapp.theme.DefaultCardElevation
import com.jozsefmolnar.bankingapp.theme.dp16
import com.jozsefmolnar.bankingapp.theme.dp8
import kotlin.math.roundToInt

@Composable
internal fun Atm(
    atm: Atm,
    index: Int,
    distanceInMeters: Double?,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shadowElevation = DefaultCardElevation,
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
                distanceInMeters?.let {
                    Text(
                        text = stringResource(Res.string.atm_finder_distance_meters, distanceInMeters.roundToInt()),
                        fontSize = 10.sp,
                        color = AppTheme.colors.textDarker,
                    )
                }
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
                color = AppTheme.colors.backgroundNeutral,
                lineHeight = 10.sp,
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
