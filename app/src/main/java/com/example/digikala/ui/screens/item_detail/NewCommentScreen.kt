package com.example.digikala.ui.screens.item_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.digikala.R
import com.example.digikala.ui.theme.GrayAlpha
import com.example.digikala.ui.theme.amber
import com.example.digikala.ui.theme.darkCyan
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.grayCategory
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.spacing

@Composable
fun NewCommentScreen(
    navController: NavHostController,
    itemId: String,
    itemName: String,
    imageUrl: String
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(
                rememberScrollState()
            )
    ) {
        Header(navController, itemName, imageUrl)
        ScoreSlider()
        CommentForm()
    }


}

@Composable
private fun Header(
    navController: NavHostController,
    itemName: String,
    imageUrl: String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = MaterialTheme.spacing.extraSmall,
                horizontal = MaterialTheme.spacing.small
            ),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(
            onClick = { navController.popBackStack() }
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = ""
            )

        }

        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = "",
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.small)
                .clip(MaterialTheme.roundedShape.small)
                .size(50.dp)
        )

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = stringResource(id = R.string.your_comment),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.darkText
            )

            Text(
                text = itemName,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.semiDarkText
            )


        }
    }

    Divider(
        color = MaterialTheme.colorScheme.grayCategory,
        thickness = 2.dp
    )

}

@Composable
private fun ScoreSlider() {
    var sliderValue by remember {
        mutableFloatStateOf(0f)
    }

    val score = when (sliderValue.toInt()) {
        1 -> ""
        2 -> stringResource(id = R.string.very_bad)
        3 -> stringResource(id = R.string.bad)
        4 -> stringResource(id = R.string.normal)
        5 -> stringResource(id = R.string.good)
        6 -> stringResource(id = R.string.very_good)
        else -> ""
    }

    Text(
        text = score,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = MaterialTheme.spacing.medium),
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.darkText,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )

    Slider(
        modifier = Modifier
            .padding(horizontal = MaterialTheme.spacing.large),
        value = sliderValue,
        onValueChange = {
            sliderValue = it
        },
        onValueChangeFinished = {

        },
        valueRange = 1f..6f,
        steps = 4,
        colors = SliderDefaults.colors(
            thumbColor = MaterialTheme.colorScheme.amber,
            activeTrackColor = MaterialTheme.colorScheme.amber,
            inactiveTrackColor = MaterialTheme.colorScheme.grayCategory,
            activeTickColor = MaterialTheme.colorScheme.amber,
            inactiveTickColor = MaterialTheme.colorScheme.GrayAlpha
        )
    )

    Divider(
        modifier = Modifier
            .padding(top = MaterialTheme.spacing.semiMedium),
        color = MaterialTheme.colorScheme.grayCategory,
        thickness = 1.dp
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CommentForm() {

    var commentTitle by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var commentBody by remember {
        mutableStateOf(TextFieldValue(""))
    }


    Column(
        modifier = Modifier
            .padding(horizontal = MaterialTheme.spacing.medium)
    ) {
        Text(
            text = stringResource(id = R.string.say_your_comment),
            modifier = Modifier
                .padding(vertical = MaterialTheme.spacing.medium),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.darkText,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = stringResource(id = R.string.comment_title),
            modifier = Modifier
                .padding(MaterialTheme.spacing.extraSmall),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.darkText,
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = commentTitle,
            onValueChange = {
                commentTitle = it
            },
            maxLines = 1,
            singleLine = true,
            shape = MaterialTheme.roundedShape.small,
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colorScheme.darkText,
                containerColor = MaterialTheme.colorScheme.grayCategory,
                focusedIndicatorColor = MaterialTheme.colorScheme.darkCyan,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent

            )
        )

        Text(
            text = stringResource(id = R.string.comment_text),
            modifier = Modifier
                .padding(
                    top = MaterialTheme.spacing.biggerMedium,
                    start = MaterialTheme.spacing.extraSmall,
                    bottom = MaterialTheme.spacing.extraSmall
                ),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.darkText,
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            value = commentBody,
            onValueChange = {
                commentBody = it
            },
            shape = MaterialTheme.roundedShape.small,
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colorScheme.darkText,
                containerColor = MaterialTheme.colorScheme.grayCategory,
                focusedIndicatorColor = MaterialTheme.colorScheme.darkCyan,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent

            ),
            maxLines = 3,
        )

    }
}