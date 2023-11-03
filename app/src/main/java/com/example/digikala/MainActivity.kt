package com.example.digikala

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.digikala.navigation.BottomNavigationBar
import com.example.digikala.navigation.SetUpNavGraph
import com.example.digikala.ui.component.AppConfig
import com.example.digikala.ui.theme.DigikalaTheme
import com.example.digikala.util.Constants.App_Language
import com.example.digikala.util.Constants.English_Lang
import com.example.digikala.util.LocaleUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DigikalaTheme {
                navController = rememberNavController()

                // language config------------------------------------------------------------------
                AppConfig()
                Log.e("3636", App_Language)
                val direction = if (App_Language == English_Lang) {
                    LayoutDirection.Ltr
                } else {
                    LayoutDirection.Rtl
                }
                LocaleUtils.setLocale(LocalContext.current, App_Language)
                //----------------------------------------------------------------------------------

                CompositionLocalProvider(LocalLayoutDirection provides direction) {
                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(
                                navController = navController,
                                onItemClick = {
                                    navController.navigate(it.route)
                                })
                        }
                    ) {
                        SetUpNavGraph(navController = navController)
                    }
                }
            }
        }
    }
}