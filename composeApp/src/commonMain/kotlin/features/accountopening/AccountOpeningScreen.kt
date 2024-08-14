package features.accountopening

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
import bankingapp.composeapp.generated.resources.Res
import bankingapp.composeapp.generated.resources.account_opening_continue
import bankingapp.composeapp.generated.resources.account_opening_email
import bankingapp.composeapp.generated.resources.account_opening_heading
import bankingapp.composeapp.generated.resources.account_opening_loading
import bankingapp.composeapp.generated.resources.account_opening_password
import bankingapp.composeapp.generated.resources.account_opening_title
import components.BackButton
import components.Header
import components.MainButton
import components.TextField
import features.atmfinder.FullScreenLoading
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import theme.AppTheme
import theme.dp16
import theme.dp24

@Composable
fun AccountOpeningScreen(
    navController: NavController,
) {
    val viewModel = koinViewModel<AccountOpeningViewModel>()
    val currentBank by viewModel.currentBank.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.accountOpeningSuccessfulEvents.collect {
            navController.navigate(Routes.RouteHome) {
                popUpTo(Routes.RouteWelcome)
            }
        }
    }


    Box {
        AccountOpeningScreenContent(
            currentBank = currentBank,
            error = viewModel.error,
            openAccount = { email, password ->
                viewModel.openAccount(
                    email = email,
                    password = password
                )
            },
            navigateUp = navController::navigateUp,
        )

        if (viewModel.isLoading) {
            FullScreenLoading(
                text = stringResource(Res.string.account_opening_loading),
                isOverlay = true,
            )
        }
    }
}

@Composable
fun AccountOpeningScreenContent(
    currentBank: BankConfig?,
    error: String?,
    openAccount: (email: String, password: String) -> Unit,
    navigateUp: () -> Unit,
) {
    Scaffold(
        topBar = {
            currentBank?.let {
                Header(
                    title = stringResource(Res.string.account_opening_title, currentBank),
                    startButton = { BackButton(onClick = navigateUp) },
                )
            }
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
            var email by rememberSaveable { mutableStateOf("") }
            var password by rememberSaveable { mutableStateOf("") }

            Text(
                text = stringResource(Res.string.account_opening_heading),
                color = AppTheme.colors.textDark,
                fontSize = 22.sp,
            )

            Spacer(Modifier.height(dp24))

            TextField(
                title = stringResource(Res.string.account_opening_email),
                value = email,
                onValueChange = { email = it },
            )

            Spacer(Modifier.height(dp16))

            TextField(
                title = stringResource(Res.string.account_opening_password),
                value = password,
                onValueChange = { password = it },
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
                text = stringResource(Res.string.account_opening_continue),
                onClick = { openAccount(email, password) }
            )
        }
    }
}
