package features.atmfinder

import com.jozsefmolnar.bankingapp.BankingApplication
import dev.icerock.moko.permissions.PermissionsController

actual fun getPermissionsController(): PermissionsController =
    PermissionsController(applicationContext = BankingApplication.appContext)
