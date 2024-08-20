package com.jozsefmolnar.bankingapp.features.pin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import bankingapp.composeapp.generated.resources.Res
import bankingapp.composeapp.generated.resources.pin_biometric_cancel
import bankingapp.composeapp.generated.resources.pin_biometric_description
import bankingapp.composeapp.generated.resources.pin_biometric_title
import bankingapp.composeapp.generated.resources.pin_forgot_pin_button
import bankingapp.composeapp.generated.resources.pin_heading
import bankingapp.composeapp.generated.resources.pin_title
import com.jozsefmolnar.bankingapp.Routes
import com.jozsefmolnar.bankingapp.components.BackButton
import com.jozsefmolnar.bankingapp.components.Header
import com.jozsefmolnar.bankingapp.theme.AppTheme
import com.jozsefmolnar.bankingapp.theme.dp24
import dev.icerock.moko.biometry.compose.BindBiometryAuthenticatorEffect
import dev.icerock.moko.biometry.compose.rememberBiometryAuthenticatorFactory
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf


@Composable
fun PinScreen(
    navController: NavController,
) {
    val biometryAuthenticatorFactory = rememberBiometryAuthenticatorFactory()
    val viewModel = koinViewModel<PinViewModel> {
        parametersOf(biometryAuthenticatorFactory.createBiometryAuthenticator())
    }

    val biometricResult = viewModel.biometricResult.collectAsState().value
    val navigateToHome = {
        navController.navigate(route = Routes.RouteHome) {
            popUpTo(Routes.RouteWelcome)
        }
    }

    val biometricTitle = stringResource(Res.string.pin_biometric_title)
    val biometricDescription = stringResource(Res.string.pin_biometric_description)
    val biometricCancelButtonText = stringResource(Res.string.pin_biometric_cancel)

    BindBiometryAuthenticatorEffect(viewModel.biometryAuthenticator)

    LaunchedEffect(Unit) {
        viewModel.tryToAuth(
            title = biometricTitle,
            description = biometricDescription,
            cancelButtonText = biometricCancelButtonText,
        )
    }

    LaunchedEffect(biometricResult) {
        if (biometricResult == BiometricResult.Success) {
            navigateToHome()
        }
    }

    PinScreenContent(
        navigateToDashboard = navigateToHome,
        navigateUp = navController::navigateUp,
    )
}

@Composable
fun PinScreenContent(
    navigateToDashboard: () -> Unit,
    navigateUp: () -> Unit,
) {
    var pinCount by remember { mutableIntStateOf(0) }

    LaunchedEffect(pinCount) {
        if (pinCount >= 6) {
            navigateToDashboard()
        }
    }

    Scaffold(
        topBar = {
            Header(
                title = stringResource(Res.string.pin_title),
                startButton = { BackButton(onClick = navigateUp) },
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(contentPadding)
                .verticalScroll(rememberScrollState())
                .padding(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(Res.string.pin_heading),
                fontSize = 20.sp,
                color = AppTheme.colors.textDark,
                textAlign = TextAlign.Center,
            )

            Spacer(Modifier.weight(1f))
            Spacer(Modifier.height(dp24))

            Text(
                text = buildString { repeat(pinCount) { append("•") } }
                    .ifBlank { " " },
                fontSize = 40.sp,
                color = AppTheme.colors.main,
                textAlign = TextAlign.Center,
            )

            Spacer(Modifier.height(dp24))

            PinPad(
                modifier = Modifier.padding(horizontal = dp24),
                onClick = { pinCount += 1 },
            )

            Spacer(Modifier.height(dp24))
            Spacer(Modifier.weight(1f))

            TextButton(
                colors = ButtonDefaults.textButtonColors(
                    contentColor = AppTheme.colors.main
                ),
                onClick = {},
            ) {
                Text(
                    text = stringResource(Res.string.pin_forgot_pin_button),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.sp,
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    }
}
