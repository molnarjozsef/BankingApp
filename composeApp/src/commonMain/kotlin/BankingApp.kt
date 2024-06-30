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
import features.dashboard.DashboardScreen
import features.login.LoginScreen
import features.pin.PinScreen
import theme.AppTheme


@Composable
fun BankingApp() {
    val navController = rememberNavController()

    AppTheme {
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
                PinScreen(
                    navigateToDashboard = { navController.navigate(RouteDashboard) }
                )
            }
            composable(RouteDashboard) {
                DashboardScreen()
            }
        }
    }
}

object Routes {
    val RouteLogin = "login"
    val RoutePin = "pin"
    val RouteDashboard = "dashboard"
}
