import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun BankingApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = RouteLogin
    ) {
        composable(RouteLogin) {
            LoginScreen(
                navigateToPinScreen = { navController.navigate(RoutePin) }
            )
        }
        composable(RoutePin) {
            PinScreen()
        }
    }
}

val RouteLogin = "login"
val RoutePin = "pin"