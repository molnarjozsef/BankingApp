object Strings {

    val BankName = "OTP"

    object Login {
        val QrButton = "$BankName e-bank kódbeolvasás"
        val LoginButton = "$BankName mobilbank belépés"
    }

    object Pin {
        val Title = "azonosítás"
        val Heading = "add meg az mPIN kódodat"
        val ForgotPinButton = "elfelejtettem az mPIN-kódomat"
    }


    object Dashboard {
        val Title = "kezdőlap"

        val QuickSettingsCurrentTransfer = "forintátutalás"
        val QuickSettingsLimitChange = "limitmódosítás"
        val QuickSettingsBillPayment = "csekkbefizetés"

        val BottomNavigationHome = "kezdőlap"
        val BottomNavigationProducts = "termékek"
        val BottomNavigationExtras = "$BankName+"
    }
}
