package features.atmfinder

import dev.icerock.moko.permissions.PermissionsController

actual fun getPermissionsController(): PermissionsController =
    dev.icerock.moko.permissions.ios.PermissionsController()
