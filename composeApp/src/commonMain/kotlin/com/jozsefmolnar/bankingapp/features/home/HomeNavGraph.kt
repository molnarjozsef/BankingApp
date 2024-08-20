package com.jozsefmolnar.bankingapp.features.home

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jozsefmolnar.bankingapp.NavigationAnimationDurationMillis
import com.jozsefmolnar.bankingapp.Routes
import com.jozsefmolnar.bankingapp.features.dashboard.DashboardScreen
import com.jozsefmolnar.bankingapp.features.extras.ExtrasScreen
import com.jozsefmolnar.bankingapp.features.products.ProductsScreen

@Composable
fun HomeNavGraph(
    appNavController: NavController,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        route = Routes.RouteHome,
        startDestination = Routes.RouteDashboard,
        enterTransition = { fadeIn(animationSpec = tween(NavigationAnimationDurationMillis)) },
        exitTransition = { fadeOut(animationSpec = tween(NavigationAnimationDurationMillis)) },
    ) {
        composable(Routes.RouteDashboard) {
            DashboardScreen(
                navController = appNavController,
            )
        }
        composable(Routes.RouteProducts) {
            ProductsScreen()
        }
        composable(Routes.RouteExtras) {
            ExtrasScreen()
        }
    }
}
