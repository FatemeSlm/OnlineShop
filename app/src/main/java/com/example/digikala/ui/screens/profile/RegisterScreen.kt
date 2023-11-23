package com.example.digikala.ui.screens.profile

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikala.R
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.selectedBottomBar
import com.example.digikala.ui.theme.spacing
import com.example.digikala.util.InputValidation.isValidPassword
import com.example.digikala.viewmodel.ProfileViewModel

@Composable
fun RegisterScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))

        Text(
            text = stringResource(id = R.string.set_password_text),
            modifier = Modifier.padding(
                horizontal = MaterialTheme.spacing.semiLarge
            ),
            color = MaterialTheme.colorScheme.darkText,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleSmall
        )
        MyTextField(
            value = viewModel.inputPhoneState,
            placeHolder = stringResource(id = R.string.phone_and_email)
        ) {}

        MyTextField(
            value = viewModel.inputPasswordState,
            placeHolder = stringResource(id = R.string.set_password)
        ) {
            viewModel.inputPasswordState = it
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        MyButton(text = stringResource(id = R.string.digikala_entry)) {
            if (isValidPassword(viewModel.inputPasswordState)) {
                viewModel.screenState = ProfileScreenState.ProfileState
            } else {
                Toast.makeText(
                    context,
                    context.resources.getText(R.string.password_format_error),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}