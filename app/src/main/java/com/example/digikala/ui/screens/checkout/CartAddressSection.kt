package com.example.digikala.ui.screens.checkout

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.extraSmall
import com.example.digikala.ui.theme.lightCyan
import com.example.digikala.ui.theme.spacing

@Composable
fun CartAddressSection(
    navController: NavHostController
) {

    val address = stringResource(id = R.string.no_address)
    val addressBtnText = stringResource(id = R.string.add_address)

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {

        Image(
            painter = painterResource(id = R.drawable.location),
            contentDescription = "",
            modifier = Modifier
                .size(22.dp)
                .weight(0.15f)
                .align(Alignment.CenterVertically)
        )

        Column(
            modifier = Modifier
                .weight(.85f)
                .padding(vertical = MaterialTheme.spacing.medium),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = stringResource(id = R.string.send_to),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

            Text(
                text = address,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.darkText,
                fontWeight = FontWeight.SemiBold,
                maxLines = 3
            )

            Text(
                text = "",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.darkText,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.medium),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = addressBtnText,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.extraSmall,
            color = MaterialTheme.colorScheme.lightCyan,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.extraSmall)
                .clickable { }
        )

        Icon(
            painter = painterResource(id = R.drawable.arrow_back),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.lightCyan,
            modifier = Modifier
                .size(12.dp)
                .align(Alignment.CenterVertically)

        )

    }

    Divider(
        modifier = Modifier
            .padding(vertical = MaterialTheme.spacing.medium)
            .fillMaxWidth()
            .height(MaterialTheme.spacing.small)
            .alpha(.4f)
            .shadow(2.dp),
        color = Color.LightGray
    )
}