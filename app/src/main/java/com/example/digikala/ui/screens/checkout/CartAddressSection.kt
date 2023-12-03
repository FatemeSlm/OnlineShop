package com.example.digikala.ui.screens.checkout

import android.util.Log
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.data.model.UserAddress
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.ui.component.Loading
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.extraSmall
import com.example.digikala.ui.theme.lightCyan
import com.example.digikala.ui.theme.spacing
import com.example.digikala.viewmodel.AddressViewModel

@Composable
fun CartAddressSection(
    navController: NavHostController,
    viewModel: AddressViewModel = hiltViewModel()
) {

    var addressList by remember {
        mutableStateOf<List<UserAddress>>(emptyList())
    }
    var loading by remember {
        mutableStateOf(false)
    }

    var address = stringResource(id = R.string.no_address)
    var addressName = ""
    var addressBtnText = stringResource(id = R.string.add_address)

    val addressListResult by viewModel.userAddressList.collectAsState()
    when (addressListResult) {
        is NetworkResult.Success -> {
            addressList = addressListResult.data ?: emptyList()

            if (addressList.isNotEmpty()) {
                address = addressList[0].address
                addressName = addressList[0].name
                addressBtnText = stringResource(id = R.string.change_address)
            }

            loading = false

        }

        is NetworkResult.Error -> {
            loading = false
            Log.e("3636", "CartAddressSection error : ${addressListResult.message}")

        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }


    if (loading) {
        Loading(height = 100.dp, isDark = true)
    } else {
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
                    text = addressName,
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
    }

    Divider(
        modifier = Modifier
            .padding(vertical = MaterialTheme.spacing.medium)
            .fillMaxWidth()
            .height(MaterialTheme.spacing.extraSmall)
            .alpha(.4f)
            .shadow(2.dp),
        color = Color.LightGray
    )
}