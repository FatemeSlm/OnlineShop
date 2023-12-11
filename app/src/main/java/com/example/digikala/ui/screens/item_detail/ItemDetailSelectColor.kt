package com.example.digikala.ui.screens.item_detail

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.digikala.data.model.item_detail.ItemColor
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.spacing

@Composable
fun ItemDetailSelectColor(
    colors: List<ItemColor>
) {

    Column(
        modifier = Modifier.padding(MaterialTheme.spacing.small)
    ) {

        Text(
            text = "رنگ: انتخاب نشده",
            color = MaterialTheme.colorScheme.darkText,
            modifier = Modifier.padding(MaterialTheme.spacing.small),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        LazyRow {
            items(colors) {
                ItemColorChip(it)
            }
        }
    }
}

@Composable
fun ItemColorChip(
    itemColor: ItemColor
) {

    Surface(
        modifier = Modifier.padding(MaterialTheme.spacing.extraSmall),
        shadowElevation = 1.dp,
        shape = MaterialTheme.roundedShape.biggerMedium
    ) {

        Row(
            modifier = Modifier.padding(MaterialTheme.spacing.small),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Canvas(
                modifier = Modifier
                    .size(20.dp)
                    .border(1.dp, Color.Gray, CircleShape),
                onDraw = {
                    drawCircle(
                        Color(("ff" + itemColor.code.removePrefix("#").lowercase()).toLong(16))
                    )
                }
            )

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

            Text(
                text = itemColor.color,
                style = MaterialTheme.typography.titleSmall
            )

        }

    }

}