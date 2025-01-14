package com.jozsefmolnar.bankingapp.features.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import bankingapp.composeapp.generated.resources.Res
import bankingapp.composeapp.generated.resources.profile_logout
import bankingapp.composeapp.generated.resources.profile_logout_loading
import bankingapp.composeapp.generated.resources.profile_title
import com.jozsefmolnar.bankingapp.Routes
import com.jozsefmolnar.bankingapp.components.BackButton
import com.jozsefmolnar.bankingapp.components.Header
import com.jozsefmolnar.bankingapp.components.MainButton
import com.jozsefmolnar.bankingapp.features.atmfinder.FullScreenLoading
import com.jozsefmolnar.bankingapp.theme.AppTheme
import com.jozsefmolnar.bankingapp.theme.dp16
import com.jozsefmolnar.bankingapp.theme.dp40
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
) {
    val viewModel = koinViewModel<ProfileViewModel>()

    LaunchedEffect(viewModel) {
        viewModel.logoutSuccessfulEvents.collect {
            navController.navigate(Routes.RouteWelcome) {
                popUpTo(Routes.RouteWelcome)
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
                text = stringResource(Res.string.profile_logout_loading),
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
                title = stringResource(Res.string.profile_title),
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
            error?.let {
                Text(
                    text = error,
                    color = AppTheme.colors.error,
                )
                Spacer(Modifier.height(dp16))
            }
            MainButton(
                text = stringResource(Res.string.profile_logout),
                onClick = logout,
            )
        }
    }
}
