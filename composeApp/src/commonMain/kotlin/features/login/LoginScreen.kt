package features.login

import Routes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import bankingapp.composeapp.generated.resources.Res
import bankingapp.composeapp.generated.resources.pin_title
import components.BackButton
import components.Header
import components.MainButton
import components.TextField
import features.atmfinder.FullScreenLoading
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import theme.AppTheme
import theme.dp40

@Composable
fun LoginScreen(
    navController: NavController,
) {
    val viewModel = koinViewModel<LoginViewModel>()

    LaunchedEffect(viewModel) {
        viewModel.loginSuccessfulEvents.collect {
            navController.navigate(Routes.RouteHome)
        }
    }


    Box {
        LoginScreenContent(
            openAccount = { email, password ->
                viewModel.login(
                    email = email,
                    password = password
                )
            },
            navigateUp = navController::navigateUp,
            error = viewModel.error,
        )

        if (viewModel.isLoading) {
            FullScreenLoading(
                text = "login in progress",
                isOverlay = true,
            )
        }
    }
}

@Composable
fun LoginScreenContent(
    openAccount: (email: String, password: String) -> Unit,
    error: String?,
    navigateUp: () -> Unit,
) {
    Scaffold(
        topBar = {
            Header(
                title = stringResource(Res.string.pin_title),
                startButton = { BackButton(onClick = navigateUp) },
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .verticalScroll(rememberScrollState())
                .padding(dp40)
        ) {
            var email by rememberSaveable { mutableStateOf("") }
            var password by rememberSaveable { mutableStateOf("") }

            TextField(
                value = email,
                onValueChange = { email = it },
                placeholder = "email"
            )
            TextField(
                value = password,
                onValueChange = { password = it },
                placeholder = "password"
            )
            MainButton(
                text = "register",
                onClick = { openAccount(email, password) }
            )
            error?.let {
                Text(
                    text = error,
                    color = AppTheme.colors.textDark,
                )
            }
        }
    }
}
