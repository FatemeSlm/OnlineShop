package com.example.digikala.ui.screens.profile

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikala.R
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.searchBarBg
import com.example.digikala.ui.theme.selectedBottomBar
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.util.InputValidation.isValidEmail
import com.example.digikala.util.InputValidation.isValidPhoneNumber
import com.example.digikala.viewmodel.ProfileViewModel

@Composable
fun LoginScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
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
        item {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
        }
        item {
            Image(
                painter = painterResource(id = R.drawable.digi_smile),
                contentDescription = "",
                modifier = Modifier
                    .size(200.dp)
            )
        }
        item { Spacer(modifier = Modifier.height(MaterialTheme.spacing.large)) }
        item {
            Text(
                text = stringResource(id = R.string.loginTxt),
                modifier = Modifier.padding(
                    horizontal = MaterialTheme.spacing.semiLarge
                ),
                color = MaterialTheme.colorScheme.darkText,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleSmall
            )
        }
        item {
            MyTextField(
                value = viewModel.inputPhoneState,
                placeHolder = stringResource(id = R.string.phone_and_email)
            ) {
                viewModel.inputPhoneState = it
            }
        }
        item {
            MyButton(text = stringResource(id = R.string.digikala_entry)) {
                if (isValidEmail(viewModel.inputPhoneState)
                    || isValidPhoneNumber(viewModel.inputPhoneState)
                ) {
                    viewModel.screenState = ProfileScreenState.RegisterState
                } else {
                    Toast.makeText(
                        context,
                        context.resources.getText(R.string.login_error),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
        item {
            Divider(
                color = MaterialTheme.colorScheme.searchBarBg,
                modifier = Modifier
                    .fillMaxWidth()
                    .width(1.dp)
                    .padding(top = MaterialTheme.spacing.medium)
            )
        }
        item {
            TermsAndRulesText(
                fullText = stringResource(id = R.string.terms_and_rules_full),
                textColor = MaterialTheme.colorScheme.semiDarkText,
                underLinedText = listOf(
                    stringResource(id = R.string.terms_and_rules),
                    stringResource(id = R.string.privacy_and_rules)
                ),
                fontSize = 10.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}