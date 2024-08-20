package com.jozsefmolnar.bankingapp.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.jozsefmolnar.bankingapp.components.DefaultPosition
import com.jozsefmolnar.bankingapp.features.atmfinder.AtmFinderScreenContent
import com.jozsefmolnar.bankingapp.model.domain.Atm
import com.jozsefmolnar.bankingapp.theme.AppTheme

@Preview(showBackground = true)
@Composable
fun AtmFinderScreenPreview() {
    AppTheme {
        AtmFinderScreenContent(
            location = DefaultPosition,
            atms = listOf(
                Atm(
                    lat = 47.5,
                    lon = 19.0,
                )
            ),
            navigateUp = { },
        )
    }
}
