package com.example.digikala.ui.screens.item_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.searchBarBg
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.spacing

@Composable
fun ItemDescriptionScreen(
    navController: NavHostController,
    description: String,
) {

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.medium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = "",
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.medium)
                    .size(MaterialTheme.spacing.semiLarge)
                    .clickable {
                        navController.popBackStack()
                    }
            )

            Text(
                text = stringResource(id = R.string.review),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.darkText,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(MaterialTheme.colorScheme.searchBarBg)
        )

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {

            Text(
                text = stringResource(id = R.string.product_introduce),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.semiMedium),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.darkText,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = description,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.semiDarkText,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(MaterialTheme.spacing.semiMedium)
                    .fillMaxWidth()
            )

        }
    }


}