package features.home

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

fun navigateToHomeRoute(
    homeNavController: NavController,
    route: String,
) {
    homeNavController.navigate(route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(homeNavController.graph.findStartDestination().route!!) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }
}
