package theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import bankingapp.composeapp.generated.resources.Museo_Sans_300
import bankingapp.composeapp.generated.resources.Museo_Sans_500
import bankingapp.composeapp.generated.resources.Museo_Sans_700
import bankingapp.composeapp.generated.resources.Museo_Sans_900
import bankingapp.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font

val defaultTypography: Typography
    @Composable
    get() = Typography(defaultFontFamily = bankFontFamily)

private val bankFontFamily: FontFamily
    @Composable
    get() = FontFamily(
        Font(Res.font.Museo_Sans_300, FontWeight.Light),
        Font(Res.font.Museo_Sans_500, FontWeight.Medium),
        Font(Res.font.Museo_Sans_700, FontWeight.Bold),
        Font(Res.font.Museo_Sans_900, FontWeight.Black),
    )
