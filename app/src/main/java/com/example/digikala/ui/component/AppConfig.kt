package com.example.digikala.ui.component

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.digikala.util.Constants.App_Language
import com.example.digikala.viewmodel.DataStoreViewModel

@Composable
fun AppConfig(
    dataStore: DataStoreViewModel = hiltViewModel()
) {
    getDataStoreVariables(dataStore)
}

private fun getDataStoreVariables(dataStore: DataStoreViewModel) {
    App_Language = dataStore.getLanguage()
    dataStore.saveLanguage(App_Language)
}