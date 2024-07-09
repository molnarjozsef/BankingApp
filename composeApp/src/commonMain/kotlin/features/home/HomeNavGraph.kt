package features.home
import Routes.RouteDashboard
import Routes.RouteExtras
import Routes.RouteHome
import Routes.RouteProducts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import features.dashboard.DashboardScreen
import features.dashboard.DashboardViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeNavGraph(
    appNavController: NavController,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        route = RouteHome,
        startDestination = RouteDashboard
    ) {
        composable(RouteDashboard) {
            val viewModel = koinViewModel<DashboardViewModel>()

            DashboardScreen(
                viewModel = viewModel,
                navController = appNavController
            )
        }
        composable(RouteProducts) {
            Text(
                text = "products",
                modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center,
            )
        }
        composable(RouteExtras) {
            Text(
                text = "extras",
                modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center,
            )
        }
    }
}
