package com.example.digikala.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.digikala.util.Constants.App_Language
import com.example.digikala.util.Constants.English_Lang

@Composable
fun IconWithRotate(
    imageVector: ImageVector
) {
    if (App_Language == English_Lang) {
        Icon(
            imageVector = imageVector,
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier
                .graphicsLayer {
                    rotationZ = 180f
                }
        )
    } else {
        Icon(
            imageVector = imageVector,
            contentDescription = "",
            tint = Color.White,
        )
    }
}

@Composable
fun IconWithRotate(
    painter: Painter,
    tint: Color
) {
    if (App_Language == English_Lang) {
        Icon(
            painter = painter,
            contentDescription = "",
            tint = tint,
            modifier = Modifier
                .graphicsLayer {
                    rotationZ = 180f
                }
                .size(40.dp, 40.dp)
        )
    } else {
        Icon(
            painter = painter,
            contentDescription = "",
            tint = tint,
            modifier = Modifier.size(40.dp, 40.dp)
        )
    }
}