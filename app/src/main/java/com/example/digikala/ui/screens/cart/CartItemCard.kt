package com.example.digikala.ui.screens.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.data.model.cart.CartItem
import com.example.digikala.data.model.cart.CartStatus
import com.example.digikala.ui.theme.darkCyan
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.extraSmall
import com.example.digikala.ui.theme.lightGreen
import com.example.digikala.ui.theme.lightRed
import com.example.digikala.ui.theme.red
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.ui.theme.veryExtraSmall
import com.example.digikala.util.Constants
import com.example.digikala.util.DigitHelper.digitByLocateAndSeparator
import com.example.digikala.viewmodel.CartViewModel

@Composable
fun CartItemCard(
    item: CartItem,
    cartStatus: CartStatus,
    viewModel: CartViewModel = hiltViewModel()
) {

    var count by remember {
        mutableIntStateOf(item.count)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = MaterialTheme.spacing.extraSmall),
        colors = CardDefaults.cardColors(
            containerColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.White
        ),
        shape = RoundedCornerShape(0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.medium)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column {
                    Text(
                        text = stringResource(id = R.string.shopping_list),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.darkText
                    )
                    Text(
                        text = "${digitByLocateAndSeparator(count.toString())} ${stringResource(id = R.string.commodity)}",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.Gray
                    )
                }
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.darkText
                )

            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberAsyncImagePainter(item.image),
                    contentDescription = "",
                    modifier = Modifier
                        .width(130.dp)
                        .height(90.dp)
                        .weight(.3f)
                )
                Column(
                    modifier = Modifier
                        .weight(.7f)
                ) {
                    Text(
                        text = item.name,
                        modifier = Modifier
                            .padding(vertical = MaterialTheme.spacing.extraSmall),
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.darkText,
                        maxLines = 2,
                    )
                    DetailRow(
                        icon = painterResource(id = R.drawable.warranty),
                        text = stringResource(id = R.string.warranty),
                        color = MaterialTheme.colorScheme.darkText,
                        fonStyle = MaterialTheme.typography.extraSmall
                    )
                    DetailRow(
                        icon = painterResource(id = R.drawable.store),
                        text = stringResource(id = R.string.digikala),
                        color = MaterialTheme.colorScheme.darkText,
                        fonStyle = MaterialTheme.typography.extraSmall
                    )
                    Row {
                        Column(
                            modifier = Modifier
                                .width(16.dp)
                                .fillMaxHeight()
                                .padding(vertical = MaterialTheme.spacing.extraSmall),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.in_stock),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(16.dp),
                                tint = MaterialTheme.colorScheme.darkCyan
                            )
                            Divider(
                                modifier = Modifier
                                    .width(2.dp)
                                    .height(13.dp)
                                    .alpha(.6f),
                                color = Color.LightGray
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.circle),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(10.dp)
                                    .padding(1.dp),
                                tint = MaterialTheme.colorScheme.darkCyan
                            )
                            Divider(
                                modifier = Modifier
                                    .width(2.dp)
                                    .height(13.dp)
                                    .alpha(.6f),
                                color = Color.LightGray
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.circle),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(10.dp)
                                    .padding(1.dp),
                                tint = MaterialTheme.colorScheme.darkCyan
                            )
                        }
                        Column(
                            modifier = Modifier
                                .padding(horizontal = MaterialTheme.spacing.small)
                        ) {
                            Text(
                                text = item.seller,
                                style = MaterialTheme.typography.extraSmall,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.semiDarkText,
                                modifier = Modifier
                                    .padding(vertical = MaterialTheme.spacing.extraSmall)
                            )
                            DetailRow(
                                icon = painterResource(id = R.drawable.k1),
                                text = stringResource(id = R.string.digikala_send),
                                color = MaterialTheme.colorScheme.lightRed,
                                fonStyle = MaterialTheme.typography.veryExtraSmall
                            )
                            DetailRow(
                                icon = painterResource(id = R.drawable.k2),
                                text = stringResource(id = R.string.fast_send),
                                color = MaterialTheme.colorScheme.lightGreen,
                                fonStyle = MaterialTheme.typography.veryExtraSmall
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.semiLarge))

            Row(
                modifier = Modifier
                    .align(Alignment.Start),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier
                        .clip(MaterialTheme.roundedShape.semiSmall)
                        .border(
                            1.dp,
                            Color.LightGray.copy(alpha = 0.6f),
                            MaterialTheme.roundedShape.semiSmall
                        )
                ) {
                    if (cartStatus == CartStatus.CURRENT_CART) {
                        Row(
                            modifier = Modifier
                                .padding(
                                    horizontal = MaterialTheme.spacing.small,
                                    vertical = MaterialTheme.spacing.extraSmall
                                )
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_increase_24),
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.red,
                                modifier = Modifier
                                    .clickable {
                                        count++
                                        viewModel.updateItemCount(item.id, count)
                                    }
                            )
                            Text(
                                text = digitByLocateAndSeparator(count.toString()),
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.red,
                                modifier = Modifier
                                    .padding(horizontal = MaterialTheme.spacing.medium)
                            )
                            if (count == 1) {
                                Icon(
                                    painter = painterResource(id = R.drawable.digi_trash),
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.red,
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                        .clickable {
                                            viewModel.removeItem(item)
                                        }
                                )
                            } else {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_decrease_24),
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.red,
                                    modifier = Modifier
                                        .clickable {
                                            count--
                                            viewModel.updateItemCount(item.id, count)
                                        }
                                )
                            }
                        }
                    } else {
                        Row(
                            modifier = Modifier
                                .padding(
                                    horizontal = 40.dp,
                                    vertical = MaterialTheme.spacing.extraSmall
                                )
                        ){
                            Icon(
                                painter = painterResource(id = R.drawable.ic_baseline_shopping_cart_checkout),
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.red,
                                modifier = Modifier
                                    .size(22.dp)
                                    .clickable {
                                        viewModel.updateItemStatus(item.id, CartStatus.CURRENT_CART)
                                    }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.padding(MaterialTheme.spacing.semiMedium))

                val discountAmount = (item.price * item.discountPercent / 100) * item.count

                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "${digitByLocateAndSeparator(discountAmount.toString())} ${
                            stringResource(
                                id = R.string.discount
                            )
                        }",
                        style = MaterialTheme.typography.extraSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.red,
                    )
                    Row {
                        Text(
                            text = digitByLocateAndSeparator((item.price * item.count).toString()),
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

            Spacer(modifier = Modifier.padding(MaterialTheme.spacing.biggerSmall))

            if (cartStatus == CartStatus.CURRENT_CART) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModel.updateItemStatus(item.id, CartStatus.NEXT_CART)
                        },
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = stringResource(id = R.string.save_to_next_list),
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Light,
                        color = MaterialTheme.colorScheme.darkCyan,
                    )
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.darkCyan
                    )
                }
            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModel.removeItem(item)
                        },
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = stringResource(id = R.string.delete_from_list),
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Light,
                        color = MaterialTheme.colorScheme.red,
                    )
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.red
                    )
                }
            }
        }
    }
}

@Composable
private fun DetailRow(
    icon: Painter,
    text: String,
    color: Color,
    fonStyle: TextStyle
) {
    Row(
        modifier = Modifier
            .padding(vertical = MaterialTheme.spacing.extraSmall),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            contentDescription = "",
            modifier = Modifier
                .size(16.dp),
            tint = color
        )
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))
        Text(
            text = text,
            style = fonStyle,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.semiDarkText,
        )
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