package com.example.digikala.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.ui.component.CenterBannerCard
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.selectedBottomBar
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.util.Constants.User_Phone
import com.example.digikala.util.DigitHelper.digitByLocate
import com.example.digikala.viewmodel.DataStoreViewModel
import com.example.digikala.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController,
    dataStore: DataStoreViewModel = hiltViewModel(),
    viewModel: ProfileViewModel = hiltViewModel()
) {

    if (!dataStore.getUserToken().isNullOrBlank()) {
        Profile()
    } else {
        when (viewModel.screenState) {
            ProfileScreenState.LoginState -> {
                LoginScreen()
            }

            ProfileScreenState.ProfileState -> {
                Profile()
            }

            ProfileScreenState.RegisterState -> {
                RegisterScreen()
            }
        }
    }
}

@Composable
fun Profile() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 60.dp)
    ) {
        item { ProfileTopBar() }
        item { ProfileHeader() }
        item { ProfileMiddleSection() }
        item { ProfileMyOrders() }
        item { CenterBannerCard(painter = painterResource(id = R.drawable.digiclub1)) }
        item { ProfileMenuSection() }
        item { CenterBannerCard(painter = painterResource(id = R.drawable.digiclub2)) }
    }
}

@Composable
private fun ProfileMenuSection() {
    MenuRowItem(
        painter = painterResource(id = R.drawable.digi_plus_icon),
        text = stringResource(id = R.string.digi_plus)
    )
    MenuRowItem(
        painter = painterResource(id = R.drawable.digi_fav_icon),
        text = stringResource(id = R.string.fav_list)
    )
    MenuRowItem(
        painter = painterResource(id = R.drawable.digi_comments_icon),
        text = stringResource(id = R.string.my_comments)
    )
    MenuRowItem(
        painter = painterResource(id = R.drawable.digi_adresses_icon),
        text = stringResource(id = R.string.addresses)
    )
    MenuRowItem(
        painter = painterResource(id = R.drawable.digi_profile_icon),
        text = stringResource(id = R.string.profile_data),
        isHaveDivider = false
    )
}

@Composable
private fun ProfileTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = {}
        ) {
            Icon(
                painter = painterResource(id = R.drawable.digi_settings),
                contentDescription = null,
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
                    .size(MaterialTheme.spacing.semiLarge),
                tint = MaterialTheme.colorScheme.selectedBottomBar
            )
        }
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "",
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small),
                tint = MaterialTheme.colorScheme.selectedBottomBar
            )
        }
    }
}

@Composable
private fun ProfileHeader() {

    Spacer(modifier = Modifier.height(MaterialTheme.spacing.biggerMedium))

    Text(
        text = "نام یوزر",
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )

    Text(
        text = digitByLocate(User_Phone),
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleSmall,
        color = MaterialTheme.colorScheme.semiDarkText
    )

    Spacer(modifier = Modifier.height(MaterialTheme.spacing.biggerMedium))

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(.49f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.digi_score),
                contentDescription = "",
                modifier = Modifier.size(42.dp)
            )
            Column(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.semiMedium)
            ) {
                Row(
                    modifier = Modifier
                        .padding(bottom = MaterialTheme.spacing.extraSmall),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = digitByLocate("975"),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.semiDarkText
                    )

                    Text(
                        text = stringResource(id = R.string.score),
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.semiDarkText
                    )
                }

                Text(
                    text = stringResource(id = R.string.digiclub_score),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.darkText,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Divider(
            modifier = Modifier
                .width(2.dp)
                .height(60.dp)
                .alpha(.2f),
            color = Color.LightGray
        )

        Column(
            modifier = Modifier.weight(.49f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.digi_wallet),
                contentDescription = "",
                modifier = Modifier.size(34.dp)
            )
            Text(
                text = stringResource(id = R.string.digikala_wallet_active),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.darkText,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = MaterialTheme.spacing.small)
            )

        }
    }

    Spacer(modifier = Modifier.height(MaterialTheme.spacing.biggerMedium))
}

@Composable
private fun ProfileMiddleSection() {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.spacing.small)
            .alpha(.4f)
            .shadow(2.dp),
        color = Color.LightGray
    )

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = MaterialTheme.spacing.biggerMedium)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.digi_user),
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
                    .padding(bottom = MaterialTheme.spacing.small)
            )
            Text(
                text = stringResource(id = R.string.auth),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.semiDarkText,
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.digi_club),
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
                    .padding(bottom = MaterialTheme.spacing.small)
            )
            Text(
                text = stringResource(id = R.string.club),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.semiDarkText,
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.digi_contact_us),
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp)
                    .padding(bottom = MaterialTheme.spacing.small)
            )
            Text(
                text = stringResource(id = R.string.contact_us),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.semiDarkText,
            )
        }
    }

    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.spacing.small)
            .alpha(.4f)
            .shadow(2.dp),
        color = Color.LightGray
    )
}

@Composable
private fun ProfileMyOrders() {
    Text(
        text = stringResource(id = R.string.my_orders),
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(MaterialTheme.spacing.medium)
    )

    LazyRow {
        item {
            MyOrdersItem(
                text = stringResource(id = R.string.unpaid),
                painter = painterResource(id = R.drawable.digi_unpaid)
            )
        }
        item {
            MyOrdersItem(
                text = stringResource(id = R.string.processing),
                painter = painterResource(id = R.drawable.digi_processing)
            )
        }
        item {
            MyOrdersItem(
                text = stringResource(id = R.string.my_orders),
                painter = painterResource(id = R.drawable.digi_delivered)
            )
        }
        item {
            MyOrdersItem(
                text = stringResource(id = R.string.canceled),
                painter = painterResource(id = R.drawable.digi_cancel)
            )
        }
        item {
            MyOrdersItem(
                text = stringResource(id = R.string.returned),
                painter = painterResource(id = R.drawable.digi_returned)
            )
        }

    }
}

@Composable
private fun MyOrdersItem(
    text: String,
    painter: Painter
) {
    Row(
        modifier = Modifier.padding(vertical = MaterialTheme.spacing.biggerMedium)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painter,
                contentDescription = "",
                modifier = Modifier
                    .size(70.dp)
                    .padding(bottom = MaterialTheme.spacing.small)
            )
            Text(
                text = text,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.semiDarkText,
            )
        }

        Divider(
            modifier = Modifier
                .width(1.dp)
                .height(90.dp)
                .alpha(.4f),
            color = Color.LightGray
        )
    }

}