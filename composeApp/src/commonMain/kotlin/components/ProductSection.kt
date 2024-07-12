package components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp
import theme.AppTheme
import theme.DefaultCardElevation
import theme.ProductCardWidth
import theme.dp16
import theme.dp4

@Composable
fun ProductSection(
    title: String,
    products: List<Product>,
    color: Color,
) {
    Column {
        Text(
            text = title,
            color = AppTheme.colors.textDark,
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = dp16)
        )

        Spacer(Modifier.height(dp16))

        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = dp16),
            horizontalArrangement = Arrangement.spacedBy(dp16),
        ) {
            products.forEach { product ->
                ProductCard(
                    text = product.name,
                    icon = product.icon,
                    color = color,
                )
            }
        }
    }
}

data class Product(
    val name: String,
    val icon: ImageVector,
)

@Composable
private fun ProductCard(
    text: String,
    icon: ImageVector,
    color: Color,
) {
    Card(
        modifier = Modifier
            .width(ProductCardWidth)
            .aspectRatio(2f),
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.surfaceNeutral,
            contentColor = AppTheme.colors.textDarker,
        ),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = DefaultCardElevation)
    ) {
        Row {
            Text(
                text = text,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = dp16)
                    .padding(vertical = dp16),
                fontSize = 12.sp,
                lineHeight = 14.sp,
                letterSpacing = 0.sp
            )
            Icon(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .offset(x = dp16, y = dp16)
                    .rotate(IconRotationDegrees),
                imageVector = icon,
                tint = color,
                contentDescription = null,
            )
        }
    }
}

private val IconRotationDegrees = -15f
