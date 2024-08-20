package com.jozsefmolnar.bankingapp.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jozsefmolnar.bankingapp.theme.AppTheme
import com.jozsefmolnar.bankingapp.theme.dp8

@Composable
fun MainButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Button(
        modifier = modifier,
        text = text,
        onClick = onClick,
        containerColor = AppTheme.colors.main,
        contentColor = AppTheme.colors.backgroundNeutral,
        disabledContainerColor = AppTheme.colors.buttonDisabled,
        disabledContentColor = AppTheme.colors.textLight,
        enabled = enabled,
    )
}

@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
) {
    Button(
        text = text,
        onClick = onClick,
        containerColor = AppTheme.colors.contentOnMainBackground,
        contentColor = AppTheme.colors.backgroundMain,
    )
}


@Composable
private fun Button(
    text: String,
    onClick: () -> Unit,
    containerColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier,
    disabledContainerColor: Color = ButtonDefaults.buttonColors().disabledContainerColor,
    disabledContentColor: Color = ButtonDefaults.buttonColors().disabledContentColor,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor,
        ),
        shape = CircleShape,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        contentPadding = PaddingValues(dp8),
        elevation = null,
        enabled = enabled,
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}
