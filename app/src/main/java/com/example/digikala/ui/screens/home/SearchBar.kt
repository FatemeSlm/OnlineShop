package com.example.digikala.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.digikala.R
import com.example.digikala.ui.theme.LocalElevation
import com.example.digikala.ui.theme.LocalShape
import com.example.digikala.ui.theme.LocalSpacing
import com.example.digikala.ui.theme.searchBarBg
import com.example.digikala.ui.theme.unselectedBottomBar
import com.example.digikala.util.Constants.App_Language
import com.example.digikala.util.Constants.English_Lang

@Composable
fun SearchBar() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .padding(LocalSpacing.current.small),
        elevation = CardDefaults.cardElevation(
            defaultElevation = LocalElevation.current.extraSmall
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(LocalShape.current.biggerSmall)
                .background(MaterialTheme.colorScheme.searchBarBg)
        ) {
            SearchContent()
        }
    }
}


@Composable
private fun SearchContent() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            modifier = Modifier.height(24.dp),
            painter = painterResource(id = R.drawable.search),
            contentDescription = ""
        )
        Text(
            modifier = Modifier.padding(start = 20.dp),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.unselectedBottomBar,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Normal,
            text = stringResource(id = R.string.search_in)
        )
        Image(
            modifier = Modifier
                .width(80.dp)
                .padding(start = 5.dp),
            painter = digikalaLogoChangeByLang(),
            contentDescription = ""
        )
    }
}

@Composable
private fun digikalaLogoChangeByLang(): Painter {
    return if (App_Language == English_Lang) {
        painterResource(id = R.drawable.digi_red_english)
    } else {
        painterResource(id = R.drawable.digi_red_persian)
    }
}