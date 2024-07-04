import Routes.RouteAtmFinder
import Routes.RouteDashboard
import Routes.RouteLogin
import Routes.RoutePin
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.icerock.moko.biometry.compose.rememberBiometryAuthenticatorFactory
import features.atmfinder.AtmFinderScreen
import features.atmfinder.AtmFinderViewModel
import features.dashboard.DashboardScreen
import features.dashboard.DashboardViewModel
import features.login.LoginScreen
import features.pin.PinScreen
import features.pin.PinViewModel
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import theme.AppTheme


@Composable
fun BankingApp() {
    val navController = rememberNavController()

    AppTheme {
        KoinContext {
            NavHost(
                navController = navController,
                startDestination = RouteLogin,
                enterTransition = { fadeIn(animationSpec = tween(200)) },
                exitTransition = { fadeOut(animationSpec = tween(200)) },
            ) {
                composable(RouteLogin) {
                    LoginScreen(
                        navigateToPinScreen = { navController.navigate(RoutePin) }
                    )
                }
                composable(RoutePin) {
                    val biometryAuthenticatorFactory = rememberBiometryAuthenticatorFactory()
                    val viewModel = koinViewModel<PinViewModel> {
                        parametersOf(biometryAuthenticatorFactory.createBiometryAuthenticator())
                    }

                    PinScreen(
                        navController = navController,
                        viewModel = viewModel
                    )
                }
                composable(RouteDashboard) {
                    val viewModel = koinViewModel<DashboardViewModel>()

                    DashboardScreen(
                        viewModel = viewModel,
                        navController = navController,
                    )
                }
                composable(RouteAtmFinder) {
                    val viewModel = koinViewModel<AtmFinderViewModel>()

                    AtmFinderScreen(
                        viewModel = viewModel,
                        navController = navController,
                    )
                }
            }
        }
    }
}

object Routes {
    val RouteLogin = "login"
    val RoutePin = "pin"
    val RouteDashboard = "dashboard"
    val RouteAtmFinder = "atmfinder"
}
