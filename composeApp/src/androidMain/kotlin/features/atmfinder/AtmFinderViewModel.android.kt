package features.atmfinder

import android.content.Context
import android.content.ContextWrapper
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.fragment.app.FragmentActivity
import dev.icerock.moko.geo.LocationTracker
import dev.icerock.moko.permissions.PermissionsController

actual fun getLocationTracker(permissionsController: PermissionsController): LocationTracker =
    LocationTracker(permissionsController)

@Composable
actual fun bindPermissionsController(permissionsController: PermissionsController) {
    val context = LocalContext.current
    val activity = context.getActivityOrNull()
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    activity?.let {
        permissionsController.bind(
            lifecycle = lifecycle,
            fragmentManager = activity.supportFragmentManager
        )
    }
}

private fun Context.getActivityOrNull(): FragmentActivity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is FragmentActivity) return context
        context = context.baseContext
    }

    return null
}

@Composable
actual fun bindLocationTracker(locationTracker: LocationTracker) {
    val context = LocalContext.current
    val activity = context.getActivityOrNull()
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    activity?.let {
        locationTracker.bind(
            lifecycle = lifecycle,
            context = context,
            fragmentManager = activity.supportFragmentManager
        )
    }
}
