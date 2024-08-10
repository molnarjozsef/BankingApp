package features.profile

import Routes.RouteWelcome
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import bankingapp.composeapp.generated.resources.Res
import bankingapp.composeapp.generated.resources.pin_title
import components.BackButton
import components.Header
import components.MainButton
import features.atmfinder.FullScreenLoading
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import theme.dp40

@Composable
fun ProfileScreen(
    navController: NavController,
) {
    val viewModel = koinViewModel<ProfileViewModel>()

    LaunchedEffect(viewModel) {
        viewModel.logoutSuccessfulEvents.collect {
            navController.navigate(RouteWelcome) {
                popUpTo(RouteWelcome)
            }
        }
    }


    Box {
        ProfileScreenContent(
            logout = viewModel::logout,
            navigateUp = navController::navigateUp,
            error = viewModel.error,
        )

        if (viewModel.isLoading) {
            FullScreenLoading(
                text = "registration in progress",
                isOverlay = true,
            )
        }
    }
}

@Composable
fun ProfileScreenContent(
    logout: () -> Unit,
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
            MainButton(
                text = "logout",
                onClick = logout,
            )
        }
    }
}
