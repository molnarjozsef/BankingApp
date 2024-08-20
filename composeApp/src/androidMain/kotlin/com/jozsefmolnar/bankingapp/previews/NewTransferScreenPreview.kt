package com.jozsefmolnar.bankingapp.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.jozsefmolnar.bankingapp.features.transfer.NewTransferScreenContent
import com.jozsefmolnar.bankingapp.model.domain.DefaultBank
import com.jozsefmolnar.bankingapp.theme.AppTheme

@Preview(showBackground = true)
@Composable
fun NewTransferScreenPreview() {
    AppTheme {
        NewTransferScreenContent(
            currentBank = DefaultBank,
            currentAmount = 1_234_567,
            recipientEmail = "test@email.com",
            onContinueClick = { },
            navigateUp = { },
        )
    }
}
