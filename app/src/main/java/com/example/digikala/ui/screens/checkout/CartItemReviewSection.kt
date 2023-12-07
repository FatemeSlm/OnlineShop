package com.example.digikala.ui.screens.checkout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.digikala.R
import com.example.digikala.data.model.cart.CartDetail
import com.example.digikala.data.model.cart.CartItem
import com.example.digikala.ui.theme.darkCyan
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.extraSmall
import com.example.digikala.ui.theme.red
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.spacing
import com.example.digikala.util.DigitHelper.digitByLocate

@Composable
fun CartItemReviewSection(
    cartDetail: CartDetail,
    currentCartItem: List<CartItem>,
    onDeliveryTimeClick: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {

        Text(
            text = stringResource(id = R.string.deliveryAndTimeMethod),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.darkText,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(MaterialTheme.spacing.medium)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.small),
            shape = MaterialTheme.roundedShape.small,
            colors = CardDefaults.cardColors(
                containerColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.spacing.semiMedium)
            ) {

                Text(
                    text = digitByLocate(stringResource(id = R.string.delivery_1)),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.darkText,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = MaterialTheme.spacing.medium)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.k1),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.red,
                        modifier = Modifier.size(16.dp)
                    )

                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

                    Text(
                        text = stringResource(id = R.string.fast_send),
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.extraSmall,
                        color = MaterialTheme.colorScheme.red
                    )

                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))

                    Text(
                        text = digitByLocate("${cartDetail.totalCount} ${stringResource(id = R.string.commodity)}"),
                        style = MaterialTheme.typography.extraSmall,
                        color = Color.Gray,
                        modifier = Modifier.padding(MaterialTheme.spacing.small)

                    )
                }

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    items(currentCartItem) { item ->
                        CheckoutItemCard(item)
                    }
                }

                Row {
                    Text(
                        text = stringResource(id = R.string.ready_to_send),
                        style = MaterialTheme.typography.extraSmall,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(vertical = MaterialTheme.spacing.medium)
                    )
                    Text(
                        text = digitByLocate(
                            " : ${stringResource(id = R.string.pishtaz_post)} (${
                                stringResource(
                                    id = R.string.delivery_delay
                                )
                            })"
                        ),
                        style = MaterialTheme.typography.extraSmall,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(vertical = MaterialTheme.spacing.medium)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = MaterialTheme.spacing.medium)
                        .clickable { onDeliveryTimeClick() },
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically

                ) {

                    Text(
                        text = stringResource(id = R.string.choose_time),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.darkCyan,
                        fontWeight = FontWeight.Bold
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.arrow_back),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.darkCyan,
                        modifier = Modifier
                            .padding(horizontal = MaterialTheme.spacing.small)
                            .size(12.dp)
                            .align(Alignment.CenterVertically)
                            .clickable {

                            }
                    )

                }
            }

        }

    }

}