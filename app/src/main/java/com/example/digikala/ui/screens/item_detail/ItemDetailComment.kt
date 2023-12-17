package com.example.digikala.ui.screens.item_detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.data.model.item_detail.Comment
import com.example.digikala.navigation.Screen
import com.example.digikala.ui.theme.darkCyan
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.util.DigitHelper.digitByLocate

@Composable
fun ItemDetailComment(
    navController: NavHostController,
    comments: List<Comment>,
    commentCount: Int,
    itemId: String
) {

    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.spacing.small)
            .alpha(.4f)
            .shadow(2.dp),
        color = Color.LightGray
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.semiLarge),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.user_comments),
                color = MaterialTheme.colorScheme.darkText,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "${digitByLocate(commentCount.toString())} ${stringResource(id = R.string.comment)}",
                color = MaterialTheme.colorScheme.darkCyan,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .clickable {
                        navController.navigate(Screen.AllComment.withArgs(itemId, commentCount))
                    },
            )
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.medium)
        ) {

            items(comments) { comment ->
                CommentCard(comment)
            }
            item {
                CommentShowMoreCard(navController, itemId, commentCount)
            }

        }
    }
}

