package com.jozsefmolnar.bankingapp.theme

import androidx.compose.material3.Typography
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
    get() = Typography().defaultFontFamily(fontFamily = bankFontFamily)

private val bankFontFamily: FontFamily
    @Composable
    get() = FontFamily(
        Font(Res.font.Museo_Sans_300, FontWeight.Light),
        Font(Res.font.Museo_Sans_500, FontWeight.Medium),
        Font(Res.font.Museo_Sans_700, FontWeight.Bold),
        Font(Res.font.Museo_Sans_900, FontWeight.Black),
    )

fun Typography.defaultFontFamily(fontFamily: FontFamily): Typography {
    return this.copy(
        displayLarge = this.displayLarge.copy(fontFamily = fontFamily),
        displayMedium = this.displayMedium.copy(fontFamily = fontFamily),
        displaySmall = this.displaySmall.copy(fontFamily = fontFamily),
        headlineLarge = this.headlineLarge.copy(fontFamily = fontFamily),
        headlineMedium = this.headlineMedium.copy(fontFamily = fontFamily),
        headlineSmall = this.headlineSmall.copy(fontFamily = fontFamily),
        titleLarge = this.titleLarge.copy(fontFamily = fontFamily),
        titleMedium = this.titleMedium.copy(fontFamily = fontFamily),
        titleSmall = this.titleSmall.copy(fontFamily = fontFamily),
        bodyLarge = this.bodyLarge.copy(fontFamily = fontFamily),
        bodyMedium = this.bodyMedium.copy(fontFamily = fontFamily),
        bodySmall = this.bodySmall.copy(fontFamily = fontFamily),
        labelLarge = this.labelLarge.copy(fontFamily = fontFamily),
        labelMedium = this.labelMedium.copy(fontFamily = fontFamily),
        labelSmall = this.labelSmall.copy(fontFamily = fontFamily)
    )
}
