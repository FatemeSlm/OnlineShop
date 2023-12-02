package com.example.digikala.ui.screens.cart

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.digikala.R
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.elevation
import com.example.digikala.ui.theme.red
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.util.Constants
import com.example.digikala.util.DigitHelper

@Composable
fun CompleteThePurchase(
    price: Long,
    onClick: () -> Unit
) {
    Card(
        shape = MaterialTheme.roundedShape.extraSmall,
        colors = CardDefaults.cardColors(
            containerColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = MaterialTheme.elevation.extraSmall
        ),
        border = BorderStroke(width = 1.dp, color = Color.LightGray.copy(alpha = .2f))
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = MaterialTheme.spacing.medium,
                    vertical = MaterialTheme.spacing.semiMedium
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.red),
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    text = stringResource(id = R.string.purchase_process),
                    color = Color.White,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier
                        .padding(
                            horizontal = MaterialTheme.spacing.biggerMedium,
                            vertical = MaterialTheme.spacing.extraSmall
                        )
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.goods_total_price),
                    color = MaterialTheme.colorScheme.semiDarkText,
                    style = MaterialTheme.typography.titleSmall
                )
                Row {
                    Text(
                        text = DigitHelper.digitByLocateAndSeparator(price.toString()),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.darkText,
                    )
                    Icon(
                        currencyLogoChangeByLang(),
                        contentDescription = "",
                        modifier = Modifier
                            .size(MaterialTheme.spacing.semiLarge)
                            .padding(MaterialTheme.spacing.extraSmall)
                    )
                }

            }
        }
    }
}

@Composable
private fun currencyLogoChangeByLang(): Painter {
    return if (Constants.App_Language == Constants.English_Lang) {
        painterResource(id = R.drawable.dollar)
    } else {
        painterResource(id = R.drawable.toman)
    }
}