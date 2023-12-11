package com.example.digikala.ui.screens.item_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.digikala.R
import com.example.digikala.data.model.item_detail.ItemDetail
import com.example.digikala.ui.theme.Gold
import com.example.digikala.ui.theme.GrayAlpha
import com.example.digikala.ui.theme.darkCyan
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.grayCategory
import com.example.digikala.ui.theme.lightGreen
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.util.DigitHelper.digitByLocate

@Composable
fun ItemDetailHeader(
    item: ItemDetail
) {

    Column {
        Text(
            text = "${stringResource(id = R.string.category)} / ${item.category}}",
            color = MaterialTheme.colorScheme.darkCyan,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
        )

        Text(
            text = item.name,
            color = MaterialTheme.colorScheme.darkText,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            modifier = Modifier
                .padding(
                    horizontal = MaterialTheme.spacing.medium,
                    vertical = MaterialTheme.spacing.small
                ),
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.medium)
                .fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.star),
                contentDescription = "",
                modifier = Modifier.size(15.dp),
                tint = MaterialTheme.colorScheme.Gold
            )
            Text(
                text = digitByLocate(item.star.toString()),
                color = MaterialTheme.colorScheme.semiDarkText,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(horizontal = 2.dp)
            )
            Text(
                text = digitByLocate("(${item.starCount})"),
                color = MaterialTheme.colorScheme.GrayAlpha,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(end = MaterialTheme.spacing.small)
            )
            Icon(
                painter = painterResource(id = R.drawable.dot),
                contentDescription = "",
                modifier = Modifier
                    .size(7.dp)
                    .padding(horizontal = 1.dp),
                tint = MaterialTheme.colorScheme.GrayAlpha
            )
            Text(
                text = "${digitByLocate(item.commentCount.toString())} ${stringResource(id = R.string.user_comments)}",
                color = MaterialTheme.colorScheme.darkCyan,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
            )
            Icon(
                painter = painterResource(id = R.drawable.dot),
                contentDescription = "",
                modifier = Modifier
                    .size(7.dp)
                    .padding(horizontal = 1.dp),
                tint = MaterialTheme.colorScheme.GrayAlpha
            )
            Text(
                text = "${digitByLocate(item.viewCount.toString())} ${stringResource(id = R.string.view)}",
                color = MaterialTheme.colorScheme.darkCyan,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
            )


        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(
                    horizontal = MaterialTheme.spacing.medium,
                    vertical = MaterialTheme.spacing.small
                )
                .fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.like),
                contentDescription = "",
                modifier = Modifier.size(20.dp),
                tint = MaterialTheme.colorScheme.lightGreen
            )
            Text(
                text = digitByLocate("90% (80 نفر) از خریداران این کالا را پیشنهاد کرده اند."),
                color = MaterialTheme.colorScheme.semiDarkText,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
            )
        }

        Divider(
            color = MaterialTheme.colorScheme.grayCategory,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
        )
    }
}