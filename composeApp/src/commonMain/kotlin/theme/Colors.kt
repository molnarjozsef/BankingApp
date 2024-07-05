package theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

internal val LocalColors = staticCompositionLocalOf { lightColors }

data class Colors(
    val main: Color,
    val onMain: Color,
    val linkOnMain: Color,
    val mainOnMain: Color,
    val lightOnMain: Color,

    val dark: Color,
    val darker: Color,
    val lightDark: Color,

    val mainBackground: Color,
    val background: Color,
    val lightBackground: Color,

    val surface: Color,
    val cardSilver: Color,
)

val lightColors = Colors(
    main = Color(0xFF00bcf3),
    onMain = Color(0xFFFFFFFF),
    linkOnMain = Color(0xFFFFFFFF),
    mainOnMain = Color(0xFF80D6F7),
    lightOnMain = Color(0xFF80D6F7),

    dark = Color(0xFF4b7293),
    darker = Color(0xFF003665),
    lightDark = Color(0xFF6686a3),

    background = Color(0xFFFFFFFF),
    lightBackground = Color(0xFFf5f9fc),
    mainBackground = Color(0xFF00bcf3),

    cardSilver = Color(0xFFe9e9e9),
    surface = Color(0xFFFFFFFF)
)

val darkColors = lightColors.copy(
    mainOnMain = Color(0xFF00bcf3),
    lightOnMain = Color(0xFF2E2E2E),
    linkOnMain = Color(0xFF00bcf3),

    dark = Color(0xFFFFFFFF),
    darker = Color(0xFFFFFFFF),
    lightDark = Color(0xFFFFFFFF),

    background = Color(0xFF141414),
    mainBackground = Color(0xFF141414),
    lightBackground = Color(0xFFf5f9fc),

    cardSilver = Color(0xFFe9e9e9),
    surface = Color(0xFF242424)
)
