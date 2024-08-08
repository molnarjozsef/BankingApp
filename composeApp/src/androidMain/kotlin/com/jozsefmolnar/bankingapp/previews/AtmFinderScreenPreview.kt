package com.jozsefmolnar.bankingapp.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import components.DefaultPosition
import features.atmfinder.AtmFinderScreenContent
import model.domain.Atm
import theme.AppTheme

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
