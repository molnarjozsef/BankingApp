package com.jozsefmolnar.bankingapp.previews

import BankConfig
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import features.products.ProductsScreenContent
import theme.AppTheme

@Preview(showBackground = true)
@Composable
fun ProductsScreenPreview() {
    AppTheme {
        ProductsScreenContent(
            currentBank = BankConfig.Otp,
        )
    }
}
