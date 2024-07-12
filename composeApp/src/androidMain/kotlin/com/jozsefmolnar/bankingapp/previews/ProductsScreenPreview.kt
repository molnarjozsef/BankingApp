package com.jozsefmolnar.bankingapp.previews

import BankConfig
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import features.products.ProductsScreenContent

@Preview
@Composable
fun ProductsScreenPreview() {
    ProductsScreenContent(
        currentBank = BankConfig.Otp,
    )
}
