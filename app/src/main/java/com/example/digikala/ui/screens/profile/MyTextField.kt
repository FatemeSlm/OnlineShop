package com.example.digikala.ui.screens.profile

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.digikala.ui.theme.CursorColor
import com.example.digikala.ui.theme.darkCyan
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.searchBarBg
import com.example.digikala.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(
    value: String,
    placeHolder: String,
    onValueChange: () -> Unit
) {
    TextField(
        value = value,
        onValueChange = { onValueChange() },
        modifier = Modifier
            .fillMaxWidth()
            .height(92.dp)
            .padding(
                start = MaterialTheme.spacing.semiLarge,
                end = MaterialTheme.spacing.semiLarge,
                top = MaterialTheme.spacing.medium,
                bottom = MaterialTheme.spacing.semiLarge,
            ),
        shape = MaterialTheme.roundedShape.small,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.searchBarBg,
            focusedIndicatorColor = MaterialTheme.colorScheme.darkCyan,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.searchBarBg,
            cursorColor = MaterialTheme.colorScheme.CursorColor
        ),
        placeholder = {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = placeHolder,
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    )

}