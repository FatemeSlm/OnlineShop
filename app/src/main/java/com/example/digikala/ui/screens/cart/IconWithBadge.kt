package com.example.digikala.ui.screens.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.digikala.ui.theme.extraSmall
import com.example.digikala.ui.theme.red
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.util.DigitHelper

@Composable
fun IconWithBadge(
    cartBadge: Int,
    icon: Painter,
) {
    Box(
        modifier = Modifier
            .height(28.dp)
    ) {
        Box(
            modifier = Modifier
                .height(28.dp)
                .width(36.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Icon(
                painter = icon,
                contentDescription = "",
                modifier = Modifier.height(24.dp),
            )
        }
        Box(
            contentAlignment = Alignment.BottomStart,
            modifier = Modifier.height(28.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(18.dp)
                    .border(width = 1.dp, Color.White, MaterialTheme.roundedShape.extraSmall)
                    .clip(MaterialTheme.roundedShape.extraSmall)
                    .background(MaterialTheme.colorScheme.red),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = DigitHelper.digitByLocateAndSeparator(cartBadge.toString()),
                    style = MaterialTheme.typography.extraSmall,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                )
            }
        }

    }
}