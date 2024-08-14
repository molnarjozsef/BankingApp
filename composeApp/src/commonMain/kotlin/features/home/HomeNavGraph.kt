package features.home

import NavigationAnimationDurationMillis
import Routes.RouteDashboard
import Routes.RouteExtras
import Routes.RouteHome
import Routes.RouteProducts
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import features.dashboard.DashboardScreen
import features.extras.ExtrasScreen
import features.products.ProductsScreen

@Composable
fun HomeNavGraph(
    appNavController: NavController,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        route = RouteHome,
        startDestination = RouteDashboard,
        enterTransition = { fadeIn(animationSpec = tween(NavigationAnimationDurationMillis)) },
        exitTransition = { fadeOut(animationSpec = tween(NavigationAnimationDurationMillis)) },
    ) {
        composable(RouteDashboard) {
            DashboardScreen(
                navController = appNavController,
            )
        }
        composable(RouteProducts) {
            ProductsScreen()
        }
        composable(RouteExtras) {
            ExtrasScreen()
        }
    }
}
