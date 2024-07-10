package theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

internal val LocalColors = staticCompositionLocalOf { lightColors }

data class Colors(
    val main: Color,

    val contentOnMainBackground: Color,
    val contentOnMainSurface: Color,

    val textDark: Color,
    val textDarkHighContrast: Color,
    val textDarker: Color,
    val textLight: Color,

    val backgroundMain: Color,
    val backgroundNeutral: Color,
    val backgroundColored: Color,

    val surfaceNeutral: Color,
    val surfaceNeutralOnColored: Color,

    val bubbleOnMain: Color,
    val arrowsOnMain: Color,
    val bubbleOnColoredBackground: Color,

    val cardSilver: Color,
    val productPurple: Color,
    val productOrange: Color,
    val productYellow: Color,
)

val lightColors = Colors(
    contentOnMainBackground = Color(0xFFFFFFFF),
    contentOnMainSurface = Color(0xFFFFFFFF),

    textDark = Color(0xFF4B7293),
    textDarkHighContrast = Color(0xFF4B7293),
    textDarker = Color(0xFF003665),
    textLight = Color(0xFF6786A3),

    backgroundNeutral = Color(0xFFFFFFFF),
    backgroundColored = Color(0xFFF3F8FB),
    backgroundMain = Color(0xFF00bcf3),

    main = Color(0xFF00ADEF),
    surfaceNeutral = Color(0xFFFFFFFF),
    surfaceNeutralOnColored = Color(0xFFFFFFFF),

    bubbleOnMain = Color(0xFF80D6F7),
    arrowsOnMain = Color(0xFF80D6F7),
    bubbleOnColoredBackground = Color(0xFFE4EDF4),

    cardSilver = Color(0xFFe9e9e9),
    productPurple = Color(0xFF8964A7),
    productOrange = Color(0xFFEE7179),
    productYellow = Color(0xFFEBC000),
)

val darkColors = lightColors.copy(
    contentOnMainBackground = Color(0xFF00ADEF),
    contentOnMainSurface = Color(0xFFFFFFFF),

    textDark = Color(0xFFAEAEAE),
    textDarkHighContrast = Color(0xFFD6D6D6),
    textDarker = Color(0xFFF0F0F0),
    textLight = Color(0xFFD7D7D7),

    backgroundNeutral = Color(0xFF141414),
    backgroundColored = Color(0xFF292929),
    backgroundMain = Color(0xFF141414),

    main = Color(0xFF00ADEF),
    surfaceNeutral = Color(0xFF242424),
    surfaceNeutralOnColored = Color(0xFF383838),

    bubbleOnMain = Color(0xFF00ADEF),
    arrowsOnMain = Color(0xFF2E2E2E),
    bubbleOnColoredBackground = Color(0xFF4D4D4D),
)
