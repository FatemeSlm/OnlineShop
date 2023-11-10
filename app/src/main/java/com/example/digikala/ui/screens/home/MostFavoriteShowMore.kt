package com.example.digikala.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import com.example.digikala.R
import com.example.digikala.ui.theme.darkCyan
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.spacing

@Composable
fun MostFavoriteShowMore() {
    Column(
        modifier = Modifier
            .size(170.dp, 379.dp)
            .background(Color.White)
            .padding(
                end = MaterialTheme.spacing.medium,
                start = MaterialTheme.spacing.semiSmall,
                top = MaterialTheme.spacing.semiLarge
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.show_more),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.darkCyan,
            modifier = Modifier.size(40.dp, 40.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = R.string.see_all),
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.darkText
        )
    }
}