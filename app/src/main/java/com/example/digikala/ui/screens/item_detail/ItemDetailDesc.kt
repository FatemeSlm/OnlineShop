package com.example.digikala.ui.screens.item_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.extraSmall
import com.example.digikala.ui.theme.grayCategory
import com.example.digikala.ui.theme.settingArrow
import com.example.digikala.ui.theme.spacing
import com.example.digikala.ui.theme.unselectedBottomBar

@Composable
fun ItemDetailDesc(
    navController: NavHostController,
    description: String
) {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.spacing.small)
            .alpha(.4f)
            .shadow(2.dp),
        color = Color.LightGray
    )

    Text(
        text = stringResource(id = R.string.product_desc),
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.darkText,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(MaterialTheme.spacing.small)
    )

    Spacer(
        modifier = Modifier
            .padding(MaterialTheme.spacing.medium)
            .fillMaxWidth()
            .height(1.dp)
            .background(MaterialTheme.colorScheme.grayCategory)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(horizontal = MaterialTheme.spacing.medium),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = stringResource(id = R.string.technical_specifications),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.darkText,
            fontWeight = FontWeight.Bold,
        )

        Icon(
            imageVector = Icons.Outlined.KeyboardArrowLeft,
            contentDescription = "",
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.settingArrow
        )
    }

    if (description.isNotBlank()) {

        Spacer(
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium)
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colorScheme.grayCategory)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate(Screen.ItemDescription.withArgs(description))
                }
                .padding(horizontal = MaterialTheme.spacing.medium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = stringResource(id = R.string.product_introduce),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.darkText,
                fontWeight = FontWeight.Bold,
            )

            Icon(
                imageVector = Icons.Outlined.KeyboardArrowLeft,
                contentDescription = "",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.settingArrow
            )
        }
    }

    Row(
        modifier = Modifier
            .padding(MaterialTheme.spacing.semiMedium)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {

        Text(
            text = stringResource(id = R.string.product_desc_feedback),
            style = MaterialTheme.typography.extraSmall,
            color = MaterialTheme.colorScheme.unselectedBottomBar,
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
        )

        Image(
            painter = painterResource(id = R.drawable.info),
            contentDescription = "",
            modifier = Modifier.size(20.dp)
        )
    }


}