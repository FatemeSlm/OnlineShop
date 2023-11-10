package com.example.digikala.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.spacing

@Composable
fun CenterBannerCard(imageUrl: String) {
    Card(
        shape = MaterialTheme.roundedShape.semiMedium,
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .padding(MaterialTheme.spacing.medium)
    ) {
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
    }
}