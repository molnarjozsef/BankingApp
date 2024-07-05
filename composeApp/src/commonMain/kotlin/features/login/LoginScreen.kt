package features.login

import Config
import Strings
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.QrCodeScanner
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import bankingapp.composeapp.generated.resources.Res
import bankingapp.composeapp.generated.resources.login_arrows
import components.MainButton
import components.SecondaryButton
import org.jetbrains.compose.resources.painterResource
import theme.BankColors
import theme.dp16
import theme.dp24
import theme.dp48
import theme.dp56
import theme.dp64
import theme.dp8


@Composable
fun LoginScreen(
    navigateToPinScreen: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(BankColors.main)
            .padding(
                top = dp56,
                bottom = dp64,
            )
    ) {
        AnimatedBubbles()

        Spacer(modifier = Modifier.height(dp24))

        Column(
            modifier = Modifier.padding(horizontal = dp48)
        ) {
            SecondaryButton(
                text = Strings.Login.QrButton,
                icon = rememberVectorPainter(Icons.Outlined.QrCodeScanner),
                textColor = Color.White,
                onClick = {}
            )

            Spacer(modifier = Modifier.height(dp8))

            MainButton(
                text = Strings.Login.LoginButton,
                backgroundColor = Color.White,
                onClick = navigateToPinScreen,
            )
        }
    }
}

@Composable
fun ColumnScope.AnimatedBubbles() {
    var isShown by remember { mutableStateOf(false) }
    val density = LocalDensity.current
    val iconXOffset by animateIntOffsetAsState(
        targetValue = IntOffset(
            y = 0,
            x = if (isShown) {
                with(density) { dp16.roundToPx() }
            } else {
                with(density) { IconBubbleSize.roundToPx() }
            },
        ),
        animationSpec = tween(1000)
    )
    val arrowsXOffset by animateIntOffsetAsState(
        targetValue = IntOffset(
            y = 0,
            x = if (isShown) {
                with(density) { dp16.roundToPx() }
            } else {
                with(density) { -ArrowsSize.roundToPx() }
            },
        ),
        animationSpec = tween(1000)
    )

    SideEffect { isShown = true }

    Box(
        modifier = Modifier
            .offset { iconXOffset }
            .align(Alignment.End)
            .size(IconBubbleSize)
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.4f)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(80.dp),
            painter = painterResource(Config.currentBank.iconRes),
            contentDescription = null,
            tint = BankColors.white
        )
    }

    Spacer(Modifier.weight(1f))

    Icon(
        modifier = Modifier
            .size(ArrowsSize)
            .offset { arrowsXOffset }
            .align(Alignment.Start),
        painter = painterResource(Res.drawable.login_arrows),
        contentDescription = null,
        tint = Color.White.copy(alpha = 0.4f),
    )
}

val IconBubbleSize = 140.dp
val ArrowsSize = 120.dp
