package com.example.digikala.ui.screens.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.digikala.ui.theme.red
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.util.DigitHelper.digitByLocateAndSeparator

@Composable
fun CartBadge(
    selectedTabIndex: Int,
    index: Int,
    cartBadge: Int
) {
    var color = Color.Gray
    if (selectedTabIndex == index) color = MaterialTheme.colorScheme.red

    Box(
        modifier = Modifier
            .size(18.dp)
            .clip(shape = MaterialTheme.roundedShape.extraSmall)
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = digitByLocateAndSeparator(cartBadge.toString()),
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            color = Color.White,
        )
    }
}