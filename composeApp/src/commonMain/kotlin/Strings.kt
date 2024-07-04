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

        val BiometricTitle = "add meg a biometrikus azonosítód"
        val BiometricCancel = "inkább mPIN-t használok"
    }


    object Dashboard {
        val Title = "kezdőlap"

        val AccountName = "${Strings.BankName} számlacsomag"

        val QuickSettingsCurrentTransfer = "forintátutalás"
        val QuickSettingsLimitChange = "limitmódosítás"
        val QuickSettingsBillPayment = "csekkbefizetés"

        val BottomNavigationHome = "kezdőlap"
        val BottomNavigationProducts = "termékek"
        val BottomNavigationExtras = "$BankName+"

        val MoreTransactions = "további tranzakciók"

        val Todos = "teendőim"
        val FinancialTransactions = "pénzügyi tranzakciók"
        val AdministrativeTransactions = "adminisztratív tranzakciók"

        val MenuTitle = "menü"

        val MenuNavigation = "navigáció"

        val MenuExtras = "további funkciók és beállítások"
        val MenuSzepCard = "$BankName SZÉP Kártya"
        val MenuAtmFinder = "ATM & ügyfélpontkereső"
        val MenuSettings = "beállítások"
        val MenuContact = "kapcsolat"
        val MenuWhatsNew = "újdonságok"
    }

    object AtmFinder {
        val Title = "ATM és ügyfélpontkereső"
        val DefaultAtmName = "ATM"
    }
}
