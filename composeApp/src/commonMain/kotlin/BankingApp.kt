
import Routes.RouteAtmFinder
import Routes.RouteBankChanger
import Routes.RouteHome
import Routes.RouteLogin
import Routes.RoutePin
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import features.atmfinder.AtmFinderScreen
import features.bankchanger.BankChangerScreen
import features.home.HomeScreen
import features.login.LoginScreen
import features.pin.PinScreen
import org.koin.compose.KoinContext
import theme.AppTheme

@Composable
fun BankingApp() {
    val navController = rememberNavController()

    AppTheme {
        KoinContext {
            BackGestureHandler(
                navController = navController,
            ) {
                NavHost(
                    navController = navController,
                    startDestination = RouteLogin,
                    enterTransition = { fadeIn(animationSpec = tween(NavigationAnimationDurationMillis)) },
                    exitTransition = { fadeOut(animationSpec = tween(NavigationAnimationDurationMillis)) },
                ) {
                    composable(RouteLogin) {
                        LoginScreen(
                            navigateToPinScreen = { navController.navigate(RoutePin) },
                            navigateToBankChanger = { navController.navigate(RouteBankChanger) },
                        )
                    }

                    composable(RoutePin) {
                        PinScreen(
                            navController = navController,
                        )
                    }

                    composable(RouteHome) {
                        HomeScreen(
                            appNavController = navController
                        )
                    }

                    composable(RouteAtmFinder) {
                        AtmFinderScreen(
                            navController = navController,
                        )
                    }

                    composable(RouteBankChanger) {
                        BankChangerScreen()
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
    val RouteLogin = "login"
    val RoutePin = "pin"

    val RouteHome = "home"
    val RouteDashboard = "dashboard"
    val RouteProducts = "products"
    val RouteExtras = "extras"

    val RouteAtmFinder = "atmfinder"

    val RouteBankChanger = "bankchanger"
}

const val NavigationAnimationDurationMillis = 200
