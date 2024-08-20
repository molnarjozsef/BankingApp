package com.jozsefmolnar.bankingapp


import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jozsefmolnar.bankingapp.features.accountopening.AccountOpeningScreen
import com.jozsefmolnar.bankingapp.features.atmfinder.AtmFinderScreen
import com.jozsefmolnar.bankingapp.features.bankchanger.BankChangerScreen
import com.jozsefmolnar.bankingapp.features.home.HomeScreen
import com.jozsefmolnar.bankingapp.features.login.LoginScreen
import com.jozsefmolnar.bankingapp.features.pin.PinScreen
import com.jozsefmolnar.bankingapp.features.profile.ProfileScreen
import com.jozsefmolnar.bankingapp.features.transfer.NewTransferScreen
import com.jozsefmolnar.bankingapp.features.transfer.SignTransferScreen
import com.jozsefmolnar.bankingapp.features.transfer.SuccessTransferScreen
import com.jozsefmolnar.bankingapp.features.welcome.WelcomeScreen
import com.jozsefmolnar.bankingapp.theme.AppTheme
import org.koin.compose.KoinContext

@Composable
fun BankingApp() {
    val navController = rememberNavController()

    AppTheme {
        KoinContext {
            BackGestureHandler(
                navController = navController,
            ) {
                Box(Modifier.background(AppTheme.colors.backgroundNeutral)) {
                    NavHost(
                        navController = navController,
                        startDestination = Routes.RouteWelcome,
                        enterTransition = { fadeIn(animationSpec = tween(NavigationAnimationDurationMillis)) },
                        exitTransition = { fadeOut(animationSpec = tween(NavigationAnimationDurationMillis)) },
                    ) {
                        composable(Routes.RouteWelcome) {
                            WelcomeScreen(
                                navController = navController,
                            )
                        }

                        composable(Routes.RoutePin) {
                            PinScreen(
                                navController = navController,
                            )
                        }

                        composable(Routes.RouteAccountOpening) {
                            AccountOpeningScreen(
                                navController = navController,
                            )
                        }

                        composable(Routes.RouteHome) {
                            HomeScreen(
                                appNavController = navController
                            )
                        }

                        composable(Routes.RouteAtmFinder) {
                            AtmFinderScreen(
                                navController = navController,
                            )
                        }

                        composable(Routes.RouteBankChanger) {
                            BankChangerScreen(
                                navController = navController
                            )
                        }

                        composable(Routes.RouteNewTransfer) {
                            NewTransferScreen(
                                navController = navController,
                            )
                        }

                        composable(Routes.RouteSignTransfer) {
                            SignTransferScreen(
                                navController = navController,
                            )
                        }

                        composable(Routes.RouteSuccessTransfer) {
                            SuccessTransferScreen(
                                navController = navController,
                            )
                        }

                        composable(Routes.RouteProfile) {
                            ProfileScreen(
                                navController = navController,
                            )
                        }

                        composable(Routes.RouteLogin) {
                            LoginScreen(
                                navController = navController,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
expect fun BackGestureHandler(
    navController: NavHostController,
    content: @Composable () -> Unit,
)

object Routes {
    val RouteWelcome = "welcome"
    val RoutePin = "pin"
    val RouteAccountOpening = "accountopening"
    val RouteLogin = "login"
    val RouteProfile = "profile"

    val RouteHome = "home"
    val RouteDashboard = "dashboard"
    val RouteProducts = "products"
    val RouteExtras = "extras"

    val RouteAtmFinder = "atmfinder"

    val RouteBankChanger = "bankchanger"

    val RouteNewTransfer = "newtransfer"
    val RouteSignTransfer = "signtransfer"
    val RouteSuccessTransfer = "success"
}

const val NavigationAnimationDurationMillis = 200
