package features.login

import BankConfig
import Routes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import components.BackButton
import components.Header
import components.MainButton
import components.TextField
import features.atmfinder.FullScreenLoading
import org.koin.compose.viewmodel.koinViewModel
import theme.AppTheme
import theme.dp16
import theme.dp24

@Composable
fun LoginScreen(
    navController: NavController,
) {
    val viewModel = koinViewModel<LoginViewModel>()
    val currentBank by viewModel.currentBank.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.loginSuccessfulEvents.collect {
            navController.navigate(Routes.RouteHome)
        }
    }

    Box {
        LoginScreenContent(
            currentBank = currentBank,
            error = viewModel.error,
            login = { email, password ->
                viewModel.login(
                    email = email,
                    password = password
                )
            },
            navigateUp = navController::navigateUp,
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
    currentBank: BankConfig?,
    error: String?,
    login: (email: String, password: String) -> Unit,
    navigateUp: () -> Unit,
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            Header(
                title = "login",
                startButton = { BackButton(onClick = navigateUp) },
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
                .imePadding()
                .verticalScroll(rememberScrollState())
                .padding(dp16)
        ) {
            Text(
                text = "add meg az e-mail címed és jelszavad",
                color = AppTheme.colors.textDark,
                fontSize = 22.sp,
            )

            Spacer(Modifier.height(dp24))

            TextField(
                title = "${currentBank?.bankName} e-bank username",
                value = email,
                onValueChange = { email = it },
                placeholder = "email"
            )

            Spacer(Modifier.height(dp16))

            TextField(
                title = "${currentBank?.bankName} e-bank password",
                value = password,
                onValueChange = { password = it },
                placeholder = "password",
                visualTransformation = PasswordVisualTransformation(),
            )

            Spacer(Modifier.height(dp16))

            error?.let {
                Text(
                    text = error,
                    color = AppTheme.colors.error,
                )
            }

            Spacer(Modifier.height(dp16))
            Spacer(Modifier.weight(1f))

            MainButton(
                modifier = Modifier.padding(horizontal = dp24),
                text = "login",
                onClick = { login(email, password) }
            )
        }
    }
}
