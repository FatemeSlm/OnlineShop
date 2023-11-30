package com.example.digikala.ui.screens.cart

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.theme.amber
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.elevation
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.spacing

@Composable
fun LoginOrRegisterSection(
    navController: NavHostController
) {

    Card(
        modifier = Modifier
            .padding(MaterialTheme.spacing.medium)
            .clickable { navController.navigate(Screen.Profile.route) },
        shape = MaterialTheme.roundedShape.small,
        elevation = CardDefaults.cardElevation(
            defaultElevation = MaterialTheme.elevation.extraSmall
        ),
        colors = CardDefaults.cardColors(
            containerColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.White
        ),
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.medium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {

            Icon(
                painter = painterResource(id = R.drawable.import_96_orenge),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.amber,
                modifier = Modifier
                    .size(30.dp)
                    .weight(.1f)
                    .align(Alignment.Top)
            )

            Spacer(modifier = Modifier.weight(.05f))

            Column(
                modifier = Modifier.weight(.8f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.login_or_register),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.darkText
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = stringResource(id = R.string.login_or_register_msg),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.weight(.05f))

            Icon(
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = "",
                tint = Color.Gray,
                modifier = Modifier
                    .size(22.dp)
                    .weight(.1f)
                    .align(Alignment.Top)
                    .padding(top = MaterialTheme.spacing.small)
            )
        }

    }

}