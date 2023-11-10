package com.example.digikala.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.component.RoundedIconBox
import com.example.digikala.ui.theme.LocalSpacing
import com.example.digikala.ui.theme.amber
import com.example.digikala.ui.theme.grayCategory
import com.example.digikala.util.Constants.AUCTION_URL
import com.example.digikala.util.Constants.DIGIJET_URL
import com.example.digikala.util.Constants.DIGIPAY_URL
import com.example.digikala.util.Constants.DIGIPLUS_URL
import com.example.digikala.util.Constants.GIFT_CARD_URL
import com.example.digikala.util.Constants.MORE_URL
import com.example.digikala.util.Constants.PINDO_URL
import com.example.digikala.util.Constants.SHOPPING_URL

@Composable
fun ShowcaseSection(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = LocalSpacing.current.semiMedium,
                vertical = LocalSpacing.current.biggerSmall
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = LocalSpacing.current.semiSmall),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            RoundedIconBox(
                title = stringResource(id = R.string.digikala_jet),
                image = painterResource(id = R.drawable.digijet),
                onClick = onBoxClick(
                    navController = navController,
                    url = DIGIJET_URL)
            )
            RoundedIconBox(
                title = stringResource(id = R.string.digi_style),
                image = painterResource(id = R.drawable.auction),
                onClick = onBoxClick(
                    navController = navController,
                    url = AUCTION_URL)
            )
            RoundedIconBox(
                title = stringResource(id = R.string.digi_pay),
                image = painterResource(id = R.drawable.digipay),
                onClick = onBoxClick(
                    navController = navController,
                    url = DIGIPAY_URL)
            )
            RoundedIconBox(
                title = stringResource(id = R.string.pindo),
                image = painterResource(id = R.drawable.pindo),
                bgColor = MaterialTheme.colorScheme.amber,
                onClick = onBoxClick(
                    navController = navController,
                    url = PINDO_URL)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = LocalSpacing.current.semiSmall),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            RoundedIconBox(
                title = stringResource(id = R.string.digi_shopping),
                image = painterResource(id = R.drawable.shopping),
                onClick = onBoxClick(
                    navController = navController,
                    url = SHOPPING_URL)
            )
            RoundedIconBox(
                title = stringResource(id = R.string.gift_card),
                image = painterResource(id = R.drawable.giftcard),
                onClick = onBoxClick(
                    navController = navController,
                    url = GIFT_CARD_URL)
            )
            RoundedIconBox(
                title = stringResource(id = R.string.digi_plus),
                image = painterResource(id = R.drawable.digiplus),
                onClick = onBoxClick(
                    navController = navController,
                    url = DIGIPLUS_URL)
            )
            RoundedIconBox(
                title = stringResource(id = R.string.more),
                image = painterResource(id = R.drawable.more),
                bgColor = MaterialTheme.colorScheme.grayCategory,
                onClick = onBoxClick(
                    navController = navController,
                    url = MORE_URL)
            )
        }
    }
}

@Composable
fun onBoxClick(navController: NavHostController, url: String): () -> Unit = {
    navController.navigate(Screen.WebView.route + "?url=$url")
}