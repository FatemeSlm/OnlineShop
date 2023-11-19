package com.example.digikala.ui.screens.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.digikala.R
import com.example.digikala.data.model.cart.CartDetail
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.red
import com.example.digikala.ui.theme.spacing
import com.example.digikala.util.Constants
import com.example.digikala.util.DigitHelper.digitByLocateAndSeparator

@Composable
fun CartPriceDetail(
    item:CartDetail
) {
    Column(
        modifier = Modifier.padding(MaterialTheme.spacing.medium)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.cart_summary),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.darkText
            )
            Text(
                text = "${digitByLocateAndSeparator(item.totalCount.toString())} ${stringResource(id = R.string.commodity)}",
                style = MaterialTheme.typography.titleSmall,
                color = Color.Gray
            )
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.semiLarge))

        PriceRow(
            title = stringResource(id = R.string.goods_price),
            price = digitByLocateAndSeparator(item.totalPrice.toString())
        )
        PriceRow(
            title = stringResource(id = R.string.goods_discount),
            price = digitByLocateAndSeparator(item.totalDiscount.toString()),
            discount = 1
        )
        PriceRow(
            title = stringResource(id = R.string.goods_total_price),
            price = digitByLocateAndSeparator(item.payablePrice.toString())
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.dot_bullet),
                color = Color.Gray,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(MaterialTheme.spacing.extraSmall)
            )
            Text(
                text = stringResource(id = R.string.shipping_cost_alert),
                color = Color.Gray,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.weight(1f)
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = MaterialTheme.spacing.medium,
                    horizontal = MaterialTheme.spacing.small
                )
                .alpha(.6f),
            color = Color.LightGray
        )
        DigiClubScore(7)
    }
}

@Composable
private fun DigiClubScore(
    score: Int
) {
    Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(horizontalArrangement = Arrangement.Start)
        {
            Image(
                painter = painterResource(id = R.drawable.digi_score),
                contentDescription = "",
                modifier = Modifier
                    .size(22.dp)
                    .padding(MaterialTheme.spacing.extraSmall)
            )
            Text(
                text = stringResource(id = R.string.digiclub_score),
                style = MaterialTheme.typography.titleSmall,
                color = Color.Gray,
                fontWeight = FontWeight.Medium,
            )
        }
        Text(
            text = "${digitByLocateAndSeparator(score.toString())} ${stringResource(id = R.string.score)}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.darkText,
            fontWeight = FontWeight.Medium,
        )
    }

    Spacer(modifier = Modifier.height(MaterialTheme.spacing.biggerSmall))

    Text(
        text = stringResource(id = R.string.digiclub_description),
        style = MaterialTheme.typography.titleSmall,
        color = Color.Gray,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.biggerSmall)
    )
}

@Composable
private fun PriceRow(
    title: String,
    price: String,
    discount: Int = 0
) {
    var color = MaterialTheme.colorScheme.darkText
    if (discount > 0) color = MaterialTheme.colorScheme.red

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = Color.Gray,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Start
        )
        Row {
            Text(
                text = price,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
                color = color,
            )
            Icon(
                currencyLogoChangeByLang(),
                contentDescription = "",
                modifier = Modifier
                    .size(MaterialTheme.spacing.semiLarge)
                    .padding(MaterialTheme.spacing.extraSmall),
                tint = color
            )
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