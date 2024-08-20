package com.jozsefmolnar.bankingapp.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.jozsefmolnar.bankingapp.features.extras.ExtrasScreenContent
import com.jozsefmolnar.bankingapp.model.domain.DefaultBank
import com.jozsefmolnar.bankingapp.theme.AppTheme

@Preview(showBackground = true)
@Composable
fun ExtrasScreenPreview() {
    AppTheme {
        ExtrasScreenContent(
            currentBank = DefaultBank,
        )
    }
}
