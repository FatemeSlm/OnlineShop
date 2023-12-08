package com.example.digikala.ui.screens.item_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.digikala.R
import com.example.digikala.data.model.item_detail.ItemDetail
import com.example.digikala.ui.theme.bottomBar
import com.example.digikala.ui.theme.elevation
import com.example.digikala.ui.theme.red
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.spacing
import com.example.digikala.util.Constants
import com.example.digikala.util.DigitHelper.applyDiscount
import com.example.digikala.util.DigitHelper.digitByLocate
import com.example.digikala.util.DigitHelper.digitByLocateAndSeparator

@Composable
fun ItemDetailBottomBar(
    itemDetail: ItemDetail,
    navController: NavController
) {

    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.bottomBar,
        elevation = MaterialTheme.elevation.small,
        modifier = Modifier.height(70.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = MaterialTheme.spacing.biggerSmall,
                    horizontal = MaterialTheme.spacing.medium
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colorScheme.red),
                shape = MaterialTheme.roundedShape.small
            ) {
                Text(
                    text = stringResource(id = R.string.add_to_basket),
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(vertical = MaterialTheme.spacing.extraSmall)
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row {
                    Box(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.red,
                                shape = CircleShape
                            )
                            .wrapContentWidth(Alignment.CenterHorizontally)
                            .wrapContentHeight(Alignment.CenterVertically)
                    ) {
                        Text(
                            text = "${digitByLocate(itemDetail.discountPercent.toString())} %",
                            color = Color.White,
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier
                                .padding(horizontal = MaterialTheme.spacing.small),
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.padding(horizontal = MaterialTheme.spacing.extraSmall))

                    Text(
                        text = digitByLocateAndSeparator(itemDetail.price.toString()),
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodySmall,
                        textDecoration = TextDecoration.LineThrough
                    )
                }

                Row {

                    Text(
                        text = digitByLocateAndSeparator(
                            applyDiscount(
                                itemDetail.price,
                                itemDetail.discountPercent
                            ).toString()
                        ),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold
                    )

                    Image(
                        painter = currencyLogoChangeByLang(),
                        contentDescription = "",
                        modifier = Modifier
                            .size(MaterialTheme.spacing.semiLarge)
                            .padding(horizontal = MaterialTheme.spacing.extraSmall)
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