package com.example.digikala.ui.screens.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.R
import com.example.digikala.ui.theme.red
import com.example.digikala.ui.theme.spacing
import com.example.digikala.viewmodel.CartViewModel

@Composable
fun CartScreen(navController: NavHostController) {
    Cart(navController = navController)
}

@Composable
fun Cart(
    navController: NavHostController,
    viewModel: CartViewModel = hiltViewModel()
) {
    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }
    val tabTitles = listOf(
        stringResource(id = R.string.cart_tab),
        stringResource(id = R.string.next_cart_list)
    )

    Column {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.medium),
            contentColor = MaterialTheme.colorScheme.red,
            indicator = { line ->
                Box(
                    modifier = Modifier
                        .tabIndicatorOffset(line[selectedTabIndex])
                        .height(3.dp)
                        .background(MaterialTheme.colorScheme.red)

                )
            }
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = index == selectedTabIndex,
                    onClick = {
                        selectedTabIndex = index
                    },
                    selectedContentColor = MaterialTheme.colorScheme.red,
                    unselectedContentColor = Color.Gray,
                    text = {
                        Row {
                            Text(
                                text = title,
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                )
            }
        }

        when (selectedTabIndex) {
            0 -> ShoppingCart()
            1 -> NextShoppingList()
        }
    }
}