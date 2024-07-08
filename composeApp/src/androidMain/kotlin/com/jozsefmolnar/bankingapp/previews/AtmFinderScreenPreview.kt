package com.jozsefmolnar.bankingapp.previews

import Config
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import features.atmfinder.AtmFinderScreenContent
import model.domain.Atm

@Preview
@Composable
fun AtmFinderScreenPreview() {
    AtmFinderScreenContent(
        atms = listOf(
            Atm(
                lat = 47.5,
                lon = 19.0,
                name = Config.currentBank.bankName,
            )
        ),
        navigateBack = { }
    )
}
