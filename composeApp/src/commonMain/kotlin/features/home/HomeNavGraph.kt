package features.home

import Routes.RouteDashboard
import Routes.RouteExtras
import Routes.RouteHome
import Routes.RouteProducts
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import features.dashboard.DashboardScreen
import features.dashboard.DashboardViewModel
import features.extras.ExtrasScreen
import features.products.ProductsScreen
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

            DashboardScreen(viewModel = viewModel)
        }
        composable(RouteProducts) {
            ProductsScreen()
        }
        composable(RouteExtras) {
            ExtrasScreen()
        }
    }
}
