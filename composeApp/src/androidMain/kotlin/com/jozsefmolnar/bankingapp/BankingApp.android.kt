package com.jozsefmolnar.bankingapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
actual fun BackGestureHandler(
    navController: NavHostController,
    content: @Composable () -> Unit,
) {
    content()
}
