import Routes.RouteAccountOpening
import Routes.RouteAtmFinder
import Routes.RouteBankChanger
import Routes.RouteHome
import Routes.RoutePin
import Routes.RouteWelcome
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import features.accountopening.AccountOpeningScreen
import features.atmfinder.AtmFinderScreen
import features.bankchanger.BankChangerScreen
import features.home.HomeScreen
import features.pin.PinScreen
import features.welcome.WelcomeScreen
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
                    startDestination = RouteWelcome,
                    enterTransition = { fadeIn(animationSpec = tween(NavigationAnimationDurationMillis)) },
                    exitTransition = { fadeOut(animationSpec = tween(NavigationAnimationDurationMillis)) },
                ) {
                    composable(RouteWelcome) {
                        WelcomeScreen(
                            navController = navController,
                        )
                    }

                    composable(RoutePin) {
                        PinScreen(
                            navController = navController,
                        )
                    }

                    composable(RouteAccountOpening) {
                        AccountOpeningScreen(
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
                        BankChangerScreen(
                            navController = navController
                        )
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

    val RouteHome = "home"
    val RouteDashboard = "dashboard"
    val RouteProducts = "products"
    val RouteExtras = "extras"

    val RouteAtmFinder = "atmfinder"

    val RouteBankChanger = "bankchanger"
}

const val NavigationAnimationDurationMillis = 200
