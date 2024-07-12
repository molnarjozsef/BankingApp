@file:OptIn(ExperimentalMaterial3Api::class)

package features.home

import Routes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import features.dashboard.Menu
import theme.AppTheme
import theme.dp8

@Composable
fun HomeScreen(
    appNavController: NavController,
) {
    val homeNavController = rememberNavController()
    var showMenu by remember { mutableStateOf(false) }

    if (showMenu) {
        ModalBottomSheet(
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
            onDismissRequest = { showMenu = false },
            containerColor = AppTheme.colors.backgroundNeutral,
            dragHandle = null,
            shape = RoundedCornerShape(
                topStart = dp8,
                topEnd = dp8,
            ),
            windowInsets = BottomSheetDefaults.windowInsets.only(WindowInsetsSides.Bottom),
        ) {
            Menu(
                navigateToAtmFinder = { appNavController.navigate(Routes.RouteAtmFinder) },
                modifier = Modifier.navigationBarsPadding(),
            )
        }
    }

    Scaffold(
        topBar = {
            HomeHeader(
                homeNavController = homeNavController,
                showMenu = { showMenu = true })
        },
        bottomBar = {
            HomeBottomNavigation(homeNavController = homeNavController)
        },
        containerColor = AppTheme.colors.backgroundNeutral
    ) { contentPadding ->

        Box(Modifier.padding(contentPadding)) {
            HomeNavGraph(
                appNavController = appNavController,
                navController = homeNavController,
            )
        }
    }
}
