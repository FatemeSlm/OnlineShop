package com.example.digikala.ui.screens.item_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.digikala.R
import com.example.digikala.data.model.item_detail.Comment
import com.example.digikala.ui.component.Loading
import com.example.digikala.ui.component.Loading3Dots
import com.example.digikala.ui.theme.GrayAlpha
import com.example.digikala.ui.theme.Green
import com.example.digikala.ui.theme.Orange
import com.example.digikala.ui.theme.darkText
import com.example.digikala.ui.theme.roundedShape
import com.example.digikala.ui.theme.searchBarBg
import com.example.digikala.ui.theme.semiDarkText
import com.example.digikala.ui.theme.spacing
import com.example.digikala.util.DigitHelper.digitByLocate
import com.example.digikala.util.DigitHelper.gregorianToJalali
import com.example.digikala.viewmodel.ItemDetailViewModel

@Composable
fun AllCommentScreen(
    navController: NavHostController,
    itemId: String,
    commentCount: Int,
    viewModel: ItemDetailViewModel = hiltViewModel()
) {

    LaunchedEffect(true) {
        viewModel.getCommentList(itemId)
    }

    val commentList = viewModel.commentList.collectAsLazyPagingItems()

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.medium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = "",
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.medium)
                    .clickable {
                        navController.popBackStack()
                    }
                    .size(24.dp)
            )

            Text(
                text = "${digitByLocate(commentCount.toString())} ${stringResource(id = R.string.comment)}",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.darkText
            )

        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(MaterialTheme.colorScheme.searchBarBg)
        )

        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {

                items(commentList) { comment ->
                    if (comment != null) {
                        CommentItem(comment)
                    }
                }

                commentList.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            item {
                                val config = LocalConfiguration.current
                                Loading(height = config.screenHeightDp.dp, isDark = true)
                            }
                        }

                        loadState.append is LoadState.Loading -> {
                            item {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(MaterialTheme.spacing.biggerMedium),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {

                                    Loading3Dots(isDark = true)

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CommentItem(comment: Comment) {

    val dateParts = comment.updatedAt.substringBefore('T').split('-')

    var iconSuggestion = R.drawable.like
    var colorSuggestion = MaterialTheme.colorScheme.Green
    var textSuggestion = stringResource(id = R.string.good_comment)
    var iconRotation = 0f

    when (comment.star) {
        in Int.MIN_VALUE..2 -> {
            iconSuggestion = R.drawable.like
            colorSuggestion = MaterialTheme.colorScheme.Orange
            textSuggestion = stringResource(id = R.string.bad_comment)
            iconRotation = 180f
        }

        in 2..3 -> {
            iconSuggestion = R.drawable.info
            colorSuggestion = MaterialTheme.colorScheme.semiDarkText
            textSuggestion = stringResource(id = R.string.so_so_comment)
            iconRotation = 0f
        }

        in 3..Int.MAX_VALUE -> {
            iconSuggestion = R.drawable.like
            colorSuggestion = MaterialTheme.colorScheme.Green
            textSuggestion = stringResource(id = R.string.good_comment)
            iconRotation = 0f
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.medium)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MaterialTheme.spacing.medium),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = digitByLocate(comment.star.toString() + ".0"),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier
                    .clip(MaterialTheme.roundedShape.small)
                    .width(MaterialTheme.spacing.large)
                    .background(colorSuggestion),
            )

            Text(
                modifier = Modifier.padding(start = MaterialTheme.spacing.medium),
                text = digitByLocate(
                    gregorianToJalali(
                        dateParts[0].toInt(),
                        dateParts[1].toInt(),
                        dateParts[2].toInt()
                    )
                ),
                color = MaterialTheme.colorScheme.semiDarkText,
                style = MaterialTheme.typography.titleSmall
            )

            Icon(
                painter = painterResource(id = R.drawable.dot),
                contentDescription = "",
                modifier = Modifier
                    .size(20.dp)
                    .padding(horizontal = MaterialTheme.spacing.small),
                tint = MaterialTheme.colorScheme.GrayAlpha
            )

            Text(
                text = comment.userName,
                color = MaterialTheme.colorScheme.GrayAlpha,
                style = MaterialTheme.typography.titleSmall
            )

        }

        Divider(
            modifier = Modifier
                .padding(start = MaterialTheme.spacing.large)
                .fillMaxWidth()
                .height(1.dp)
                .alpha(0.4f)
                .shadow(2.dp),
            color = Color.LightGray
        )

        Row(
            modifier = Modifier
                .padding(vertical = MaterialTheme.spacing.small),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = iconSuggestion),
                contentDescription = "",
                modifier = Modifier
                    .size(16.dp)
                    .rotate(iconRotation),
                tint = colorSuggestion
            )
            Text(
                text = textSuggestion,
                color = colorSuggestion,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 1,
                modifier = Modifier.padding(start = MaterialTheme.spacing.extraSmall)
            )

        }

        Text(
            text = comment.title,
            modifier = Modifier
                .padding(start = MaterialTheme.spacing.large),
            color = MaterialTheme.colorScheme.darkText,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = comment.description,
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.large,
                    top = MaterialTheme.spacing.small,
                    bottom = MaterialTheme.spacing.large
                ),
            color = MaterialTheme.colorScheme.darkText,
            fontWeight = FontWeight.Bold,
            maxLines = 3,
            style = MaterialTheme.typography.titleSmall
        )

    }

}