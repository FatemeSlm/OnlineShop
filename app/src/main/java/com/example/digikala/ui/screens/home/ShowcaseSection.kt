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
import com.example.digikala.ui.component.RoundedIconBox
import com.example.digikala.ui.theme.LocalSpacing
import com.example.digikala.ui.theme.amber
import com.example.digikala.ui.theme.grayCategory

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
                onClick = {}
            )
            RoundedIconBox(
                title = stringResource(id = R.string.digi_style),
                image = painterResource(id = R.drawable.auction),
                onClick = {}
            )
            RoundedIconBox(
                title = stringResource(id = R.string.digi_pay),
                image = painterResource(id = R.drawable.digipay),
                onClick = {}
            )
            RoundedIconBox(
                title = stringResource(id = R.string.pindo),
                image = painterResource(id = R.drawable.pindo),
                bgColor = MaterialTheme.colorScheme.amber,
                onClick = {}
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
                onClick = {}
            )
            RoundedIconBox(
                title = stringResource(id = R.string.gift_card),
                image = painterResource(id = R.drawable.giftcard),
                onClick = {}
            )
            RoundedIconBox(
                title = stringResource(id = R.string.digi_plus),
                image = painterResource(id = R.drawable.digiplus),
                onClick = {}
            )
            RoundedIconBox(
                title = stringResource(id = R.string.more),
                image = painterResource(id = R.drawable.more),
                bgColor = MaterialTheme.colorScheme.grayCategory,
                onClick = {}
            )
        }
    }
}