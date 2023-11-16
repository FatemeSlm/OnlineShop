package com.example.digikala.ui.screens.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.data.model.category.Sub
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.grayCategory
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.spacing
import com.example.digikala.util.DigitHelper.digitByLocate

@Composable
fun SubCategoryItem(
    item: Sub
) {
    Card(
        modifier = Modifier
            .width(120.dp)
            .padding(
                vertical = MaterialTheme.spacing.medium,
                horizontal = MaterialTheme.spacing.extraSmall
            ),
        shape = MaterialTheme.roundedShape.small,
        colors = CardDefaults.cardColors(
            containerColor = if (isSystemInDarkTheme()) Color.DarkGray else MaterialTheme.colorScheme.grayCategory
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.semiMedium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(item.image),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = item.name,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.darkText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "+${digitByLocate(item.count.toString())} ${stringResource(id = R.string.commodity)}",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.Gray,
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}