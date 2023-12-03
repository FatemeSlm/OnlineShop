package com.example.digikala.ui.screens.checkout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.data.model.cart.CartItem
import com.example.digikala.ui.theme.extraSmall
import com.example.digikala.ui.theme.spacing
import com.example.digikala.util.DigitHelper.digitByLocate

@Composable
fun CheckoutItemCard(
    item: CartItem
) {

    Box(
        modifier = Modifier
            .padding(MaterialTheme.spacing.small)
            .size(75.dp),
        contentAlignment = Alignment.BottomEnd
    ) {

        Image(
            painter = rememberAsyncImagePainter(item.image),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.size(75.dp)
        )

        Text(
            text = digitByLocate(item.count.toString()),
            style = MaterialTheme.typography.extraSmall
        )

    }

    Divider(
        color = Color.LightGray,
        modifier = Modifier
            .height(70.dp)
            .alpha(.4f)
            .width(1.dp)
    )
}