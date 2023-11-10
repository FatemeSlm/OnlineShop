package com.example.digikala.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
val Red = Color(0xFFed1b34)


val ColorScheme.splashBg: Color
    @Composable
    get() = Color(0xFFed1b34)

val ColorScheme.selectedBottomBar: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) Color(0xFF43474c) else Color(0xFFCFD4DA)

val ColorScheme.unselectedBottomBar: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) Color(0xFFA4A1A1) else Color(0xFF575A5E)

val ColorScheme.searchBarBg:Color
@Composable
get() = if(!isSystemInDarkTheme()) Color(0XFFF1F0EE) else Color(0XFF303235)

val ColorScheme.darkText:Color
    @Composable
    get() = if(!isSystemInDarkTheme()) Color(0XFF414244) else Color(0XFFD8D8D8)

val ColorScheme.amber: Color
    @Composable
    get() = Color(0xFFFFBF00)

val ColorScheme.grayCategory: Color
    @Composable
    get() = Color(0xFFF1F0EE)

val ColorScheme.lightRed: Color
    @Composable
    get() = Color(0xFFEF4056)

val ColorScheme.darkRed: Color
    @Composable
    get() = Color(0xFFe6123D)

val ColorScheme.semiDarkText:Color
    @Composable
    get() = if(!isSystemInDarkTheme()) Color(0XFF5C5E61) else Color(0XFFD8D8D8)


val ColorScheme.darkCyan: Color
    @Composable
    get() = Color(0xFF0FABC6)

val ColorScheme.lightGreen: Color
    @Composable
    get() = Color(0xFF86BF3C)