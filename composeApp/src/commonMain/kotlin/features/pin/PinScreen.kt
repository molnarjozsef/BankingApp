package features.pin

import Routes
import Strings
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.navigation.NavOptions
import components.BackButton
import components.Header
import dev.icerock.moko.biometry.compose.BindBiometryAuthenticatorEffect
import theme.BankColors
import theme.dp24


@Composable
fun PinScreen(
    viewModel: PinViewModel,
    navController: NavController,
) {
    val biometricResult = viewModel.biometricResult.collectAsState().value
    val navigateToDashboard = {
        navController.navigate(
            route = Routes.RouteDashboard,
            navOptions = NavOptions.Builder()
                .setPopUpTo(Routes.RouteLogin, inclusive = false)
                .build()
        )
    }

    BindBiometryAuthenticatorEffect(viewModel.biometryAuthenticator)

    LaunchedEffect(Unit) {
        viewModel.tryToAuth()
    }

    LaunchedEffect(biometricResult) {
        if (biometricResult == BiometricResult.Success) {
            navigateToDashboard()
        }
    }

    PinScreenContent(
        navigateToDashboard = navigateToDashboard,
        navigateBack = navController::navigateUp,
    )
}

@Composable
fun PinScreenContent(
    navigateToDashboard: () -> Unit,
    navigateBack: () -> Unit,
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
                title = Strings.Pin.Title,
                startButton = { BackButton(navigateBack = navigateBack) },
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(contentPadding)
                .padding(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = Strings.Pin.Heading,
                fontSize = 20.sp,
                color = BankColors.dark,
                textAlign = TextAlign.Center,
            )

            Spacer(Modifier.weight(1f))
            Spacer(Modifier.height(dp24))

            Text(
                text = buildString { repeat(pinCount) { append("•") } }
                    .ifBlank { " " },
                fontSize = 40.sp,
                color = BankColors.main,
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
                    contentColor = BankColors.main
                ),
                onClick = {},
            ) {
                Text(
                    text = Strings.Pin.ForgotPinButton,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.sp,
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    }
}
