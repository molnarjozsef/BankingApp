import bankingapp.composeapp.generated.resources.Res
import bankingapp.composeapp.generated.resources.logo_erste
import bankingapp.composeapp.generated.resources.logo_granit
import bankingapp.composeapp.generated.resources.logo_kh
import bankingapp.composeapp.generated.resources.logo_otp
import bankingapp.composeapp.generated.resources.logo_raiffeisen
import bankingapp.composeapp.generated.resources.logo_revolut
import org.jetbrains.compose.resources.DrawableResource

object Config {
    val currentBank = BankConfig.Raiffeisen
}

enum class BankConfig(
    val bankName: String,
    val iconRes: DrawableResource,
) {
    OTP(
        bankName = "OTP",
        iconRes = Res.drawable.logo_otp
    ),
    Raiffeisen(
        bankName = "Raiffeisen",
        iconRes = Res.drawable.logo_raiffeisen
    ),
    KH(
        bankName = "K&H",
        iconRes = Res.drawable.logo_kh
    ),
    Erste(
        bankName = "Erste",
        iconRes = Res.drawable.logo_erste
    ),
    Revolut(
        bankName = "Revolut",
        iconRes = Res.drawable.logo_revolut
    ),
    Granit(
        bankName = "Gránit",
        iconRes = Res.drawable.logo_granit
    )
}
