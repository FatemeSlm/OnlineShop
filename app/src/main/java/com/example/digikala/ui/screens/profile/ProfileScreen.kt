package com.example.digikala.ui.screens.profile

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.digikala.viewmodel.DataStoreViewModel
import com.example.digikala.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController,
    dataStore: DataStoreViewModel = hiltViewModel(),
    viewModel: ProfileViewModel = hiltViewModel()
) {

    when (viewModel.screenState) {
        ProfileScreenState.LoginState -> {
            LoginScreen()
        }

        ProfileScreenState.ProfileState -> {
            Profile()
        }

        ProfileScreenState.RegisterState -> {
            RegisterScreen()
        }
    }
}

@Composable
fun Profile() {
    Text(text = "Profile")
}