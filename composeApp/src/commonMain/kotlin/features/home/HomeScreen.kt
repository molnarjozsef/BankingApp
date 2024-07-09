package features.home
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import features.dashboard.BankBottomNavigation

@Composable
fun HomeScreen(
    appNavController: NavController,
) {
    val homeNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            BankBottomNavigation(homeNavController = homeNavController)
        }
    ) {
        HomeNavGraph(
            appNavController = appNavController,
            navController = homeNavController,
        )
    }
}
