package com.example.digikala.ui.component

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikala.data.remote.NetworkResult
import com.example.digikala.util.Constants.App_Language
import com.example.digikala.util.Constants.User_Id
import com.example.digikala.util.Constants.User_Password
import com.example.digikala.util.Constants.User_Phone
import com.example.digikala.util.Constants.User_Token
import com.example.digikala.viewmodel.DataStoreViewModel
import com.example.digikala.viewmodel.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AppConfig(
    dataStore: DataStoreViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    getDataStoreVariables(dataStore)

    profileViewModel.refreshToken(User_Phone, User_Password)

    LaunchedEffect(Dispatchers.Main) {
        profileViewModel.loginResponse.collectLatest { loginResponse ->
            when (loginResponse) {
                is NetworkResult.Success -> {
                    loginResponse.data?.let { user ->
                        if (user.token.isNotEmpty()) {
                            dataStore.saveUserToken(user.token)
                            dataStore.saveUserId(user.id)
                            dataStore.saveUserPhone(user.phone)
                            dataStore.saveUserPassword(User_Password)

                            getDataStoreVariables(dataStore)
                            Log.e("3636", "refresh token")
                        }
                    }
                }

                else -> {}
            }
        }

    }
}

private fun getDataStoreVariables(dataStore: DataStoreViewModel) {
    App_Language = dataStore.getLanguage()
    dataStore.saveLanguage(App_Language)

    User_Phone = dataStore.getUserPhone().toString()
    User_Password = dataStore.getUserPassword().toString()
    User_Id = dataStore.getUserId().toString()
    User_Token = dataStore.getUserToken().toString()
}