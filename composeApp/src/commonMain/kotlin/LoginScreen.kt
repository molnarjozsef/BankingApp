import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.QrCodeScanner
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun LoginScreen(
    navigateToPinScreen: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(BankColors.main)
            .padding(40.dp)
    ) {
        AnimatedBubbles()

        Spacer(modifier = Modifier.height(24.dp))

        BankSecondaryButton(
            text = "K&H e-bank kódbeolvasás",
            icon = rememberVectorPainter(Icons.Outlined.QrCodeScanner),
            textColor = Color.White,
            onClick = {}
        )

        Spacer(modifier = Modifier.height(16.dp))

        BankMainButton(
            text = "K&H mobilbank belépés",
            backgroundColor = Color.White,
            onClick = navigateToPinScreen,
        )
    }
}

@Composable
fun ColumnScope.AnimatedBubbles() {
    var isShown by remember { mutableStateOf(false) }
    val density = LocalDensity.current
    val xOffset by animateIntOffsetAsState(
        targetValue = IntOffset(
            y = 0,
            x = if (isShown) {
                150
            } else {
                500
            },
        ),
        animationSpec = tween(1000)
    )

    SideEffect { isShown = true }

    Box(
        modifier = Modifier
            .offset { xOffset }
            .align(Alignment.End)
            .size(100.dp)
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.4f)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "K&H",
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }

    Spacer(Modifier.weight(1f))

    Text(
        modifier = Modifier
            .size(100.dp)
            .offset { -xOffset }
            .align(Alignment.Start),
        text = ">>",
        color = Color.White.copy(alpha = 0.4f),
        fontSize = 64.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}

@Composable
fun BankMainButton(
    text: String,
    backgroundColor: Color,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = BankColors.main,
        ),
        shape = CircleShape,
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        elevation = null,
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun BankSecondaryButton(
    text: String,
    icon: Painter? = null,
    textColor: Color = Color.White,
    onClick: () -> Unit,
) {
    TextButton(
        onClick = onClick,
        colors = ButtonDefaults.textButtonColors(contentColor = textColor),
        shape = CircleShape,
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
    ) {
        icon?.let {
            Icon(
                painter = icon,
                contentDescription = null,
            )
            Spacer(Modifier.width(8.dp))
        }
        Text(
            text = text,
            color = textColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun PreviewKHandHMobileBankingApp() {
    BankingApp()
}

object BankColors {
    val main = Color(0xFF00AEEF)
    val dark = Color(0xFF6085A4)
}