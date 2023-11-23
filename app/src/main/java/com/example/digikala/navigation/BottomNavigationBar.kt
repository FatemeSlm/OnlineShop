package com.example.digikala.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.digikala.R
import com.example.digikala.ui.screens.cart.IconWithBadge
import com.example.digikala.ui.theme.bottomBar
import com.example.digikala.ui.theme.selectedBottomBar
import com.example.digikala.ui.theme.unselectedBottomBar
import com.example.digikala.viewmodel.CartViewModel

@Composable
fun BottomNavigationBar(
    navController: NavController,
    onItemClick: (BottomNavItem) -> Unit,
    viewModel: CartViewModel = hiltViewModel()
) {
    val items = listOf(
        BottomNavItem(
            name = stringResource(id = R.string.home),
            route = Screen.Home.route,
            selectedIcon = painterResource(id = R.drawable.home_fill),
            unSelectedIcon = painterResource(id = R.drawable.home_outline)
        ),
        BottomNavItem(
            name = stringResource(id = R.string.category),
            route = Screen.Category.route,
            selectedIcon = painterResource(id = R.drawable.category_fill),
            unSelectedIcon = painterResource(id = R.drawable.category_outline)
        ),
        BottomNavItem(
            name = stringResource(id = R.string.cart),
            route = Screen.Cart.route,
            selectedIcon = painterResource(id = R.drawable.cart_fill),
            unSelectedIcon = painterResource(id = R.drawable.cart_outline)
        ),
        BottomNavItem(
            name = stringResource(id = R.string.profile),
            route = Screen.Profile.route,
            selectedIcon = painterResource(id = R.drawable.user_fill),
            unSelectedIcon = painterResource(id = R.drawable.user_outline)
        ),
    )

    val backStackEntry = navController.currentBackStackEntryAsState()
    val showBottomBar = backStackEntry.value?.destination?.route in items.map {
        it.route
    }

    if (showBottomBar) {
        NavigationBar(
            modifier = Modifier.height(60.dp),
            containerColor = MaterialTheme.colorScheme.bottomBar,
            tonalElevation = 5.dp
        ) {

            val cartBadge by viewModel.currentCartItemsCount.collectAsState(0)

            items.forEachIndexed { index, item ->
                val selected = item.route == backStackEntry.value?.destination?.route
                NavigationBarItem(
                    selected = selected,
                    onClick = { onItemClick(item) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.selectedBottomBar,
                        unselectedIconColor = MaterialTheme.colorScheme.unselectedBottomBar,
                        selectedTextColor = MaterialTheme.colorScheme.selectedBottomBar,
                        unselectedTextColor = MaterialTheme.colorScheme.unselectedBottomBar,
                        indicatorColor = MaterialTheme.colorScheme.bottomBar
                    ),
                    icon = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            if (selected) {
                                if (index == 2 && cartBadge > 0) {
                                    IconWithBadge(cartBadge, item.selectedIcon)
                                } else {
                                    Icon(
                                        painter = item.selectedIcon,
                                        contentDescription = item.name,
                                        modifier = Modifier.height(24.dp),
                                    )
                                }
                            } else {
                                if (index == 2 && cartBadge > 0) {
                                    IconWithBadge(cartBadge, item.unSelectedIcon)
                                } else {
                                    Icon(
                                        painter = item.unSelectedIcon,
                                        contentDescription = item.name,
                                        modifier = Modifier.height(24.dp),
                                    )
                                }
                            }
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.titleSmall,
                                modifier = Modifier.padding(top = 5.dp)
                            )
                        }
                    })
            }
        }
    }

}