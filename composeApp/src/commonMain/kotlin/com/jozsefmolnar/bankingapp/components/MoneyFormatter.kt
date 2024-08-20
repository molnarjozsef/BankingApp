package com.jozsefmolnar.bankingapp.components

import androidx.compose.runtime.Composable
import bankingapp.composeapp.generated.resources.Res
import bankingapp.composeapp.generated.resources.default_currency
import org.jetbrains.compose.resources.stringResource

@Composable
fun formatMoney(amount: Int) = buildString {
    amount.toString()
        .reversed()
        .forEachIndexed { index, char ->
            if (index % 3 == 0) {
                insert(0, "$char ")
            } else {
                insert(0, char)
            }
        }
    append(stringResource(Res.string.default_currency))
}
