package features.welcome

import BankConfig
import DefaultBank
import Routes.RouteAccountOpening
import Routes.RouteBankChanger
import Routes.RouteLogin
import Routes.RoutePin
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.QrCodeScanner
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import bankingapp.composeapp.generated.resources.Res
import bankingapp.composeapp.generated.resources.login_arrows
import bankingapp.composeapp.generated.resources.welcome_account_opening_button
import bankingapp.composeapp.generated.resources.welcome_authenticate_button
import bankingapp.composeapp.generated.resources.welcome_login_button
import bankingapp.composeapp.generated.resources.welcome_qr_button
import components.SecondaryButton
import components.TertiaryButton
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import theme.AppTheme
import theme.dp16
import theme.dp24
import theme.dp48
import theme.dp56
import theme.dp64
import theme.dp8


@Composable
fun WelcomeScreen(
    navController: NavController,
) {
    val viewModel = koinViewModel<WelcomeViewModel>()
    val currentBank by viewModel.currentBank.collectAsState(DefaultBank)
    val isLoggedIn by viewModel.isLoggedIn.collectAsState(null)

    WelcomeScreenContent(
        currentBank = currentBank,
        navigateToPinScreen = { navController.navigate(RoutePin) },
        navigateToBankChanger = { navController.navigate(RouteBankChanger) },
        navigateToAccountOpeningScreen = { navController.navigate(RouteAccountOpening) },
        navigateToLoginScreen = { navController.navigate(RouteLogin) },
        isLoggedIn = isLoggedIn
    )
}

@Composable
fun WelcomeScreenContent(
    currentBank: BankConfig,
    isLoggedIn: Boolean?,
    navigateToPinScreen: () -> Unit,
    navigateToBankChanger: () -> Unit,
    navigateToAccountOpeningScreen: () -> Unit,
    navigateToLoginScreen: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.backgroundMain)
            .systemBarsPadding()
            .padding(
                top = dp56,
                bottom = dp64,
            )
    ) {
        AnimatedBubbles(
            currentBank = currentBank,
            navigateToBankChanger = navigateToBankChanger
        )

        Spacer(modifier = Modifier.height(dp24))


        Column(
            modifier = Modifier.padding(horizontal = dp48),
            verticalArrangement = Arrangement.spacedBy(dp8),
        ) {
            when (isLoggedIn) {
                true -> LoggedInButtons(
                    currentBank = currentBank,
                    navigateToPinScreen = navigateToPinScreen,
                )

                false -> NotLoggedInButtons(
                    currentBank = currentBank,
                    navigateToAccountOpeningScreen = navigateToAccountOpeningScreen,
                    navigateToLoginScreen = navigateToLoginScreen
                )

                else -> {}
            }
        }
    }
}

@Suppress("UnusedReceiverParameter")
@Composable
private fun ColumnScope.LoggedInButtons(
    currentBank: BankConfig,
    navigateToPinScreen: () -> Unit,
) {
    TertiaryButton(
        text = stringResource(Res.string.welcome_qr_button, currentBank),
        icon = rememberVectorPainter(Icons.Outlined.QrCodeScanner),
        textColor = AppTheme.colors.contentOnMainBackground,
        onClick = {}
    )

    SecondaryButton(
        text = stringResource(Res.string.welcome_authenticate_button, currentBank),
        onClick = navigateToPinScreen,
    )
}

@Suppress("UnusedReceiverParameter")
@Composable
private fun ColumnScope.NotLoggedInButtons(
    currentBank: BankConfig,
    navigateToAccountOpeningScreen: () -> Unit,
    navigateToLoginScreen: () -> Unit,
) {
    TertiaryButton(
        text = stringResource(Res.string.welcome_account_opening_button, currentBank),
        textColor = AppTheme.colors.contentOnMainBackground,
        onClick = navigateToAccountOpeningScreen
    )

    SecondaryButton(
        text = stringResource(Res.string.welcome_login_button, currentBank),
        onClick = navigateToLoginScreen,
    )
}

@Composable
fun ColumnScope.AnimatedBubbles(
    currentBank: BankConfig,
    navigateToBankChanger: () -> Unit,
) {
    var isShown by remember { mutableStateOf(false) }
    val density = LocalDensity.current
    val iconXOffset by animateIntOffsetAsState(
        targetValue = IntOffset(
            y = 0,
            x = if (isShown) {
                with(density) { dp16.roundToPx() }
            } else {
                with(density) { IconBubbleSize.roundToPx() }
            },
        ),
        animationSpec = tween(1000)
    )
    val arrowsXOffset by animateIntOffsetAsState(
        targetValue = IntOffset(
            y = 0,
            x = if (isShown) {
                with(density) { dp16.roundToPx() }
            } else {
                with(density) { -ArrowsSize.roundToPx() }
            },
        ),
        animationSpec = tween(1000)
    )

    SideEffect { isShown = true }

    Box(
        modifier = Modifier
            .offset { iconXOffset }
            .align(Alignment.End)
            .size(IconBubbleSize)
            .clip(CircleShape)
            .background(AppTheme.colors.bubbleOnMain)
            .clickable { navigateToBankChanger() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(80.dp),
            painter = painterResource(currentBank.iconRes),
            contentDescription = null,
            tint = AppTheme.colors.contentOnMainSurface,
        )
    }

    Spacer(Modifier.weight(1f))

    Icon(
        modifier = Modifier
            .size(ArrowsSize)
            .offset { arrowsXOffset }
            .align(Alignment.Start),
        painter = painterResource(Res.drawable.login_arrows),
        contentDescription = null,
        tint = AppTheme.colors.arrowsOnMain,
    )
}

val IconBubbleSize = 140.dp
val ArrowsSize = 120.dp
