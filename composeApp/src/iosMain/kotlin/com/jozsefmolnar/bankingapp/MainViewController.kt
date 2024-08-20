package com.jozsefmolnar.bankingapp

import androidx.compose.ui.window.ComposeUIViewController
import com.jozsefmolnar.bankingapp.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) {
    BankingApp()
}
