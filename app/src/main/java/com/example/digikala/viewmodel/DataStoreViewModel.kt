package com.example.digikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.datastore.DataStoreRepository
import com.example.digikala.util.Constants.English_Lang
import com.example.digikala.util.Constants.Persian_Lang
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(private val repository: DataStoreRepository) :
    ViewModel() {

    companion object {
        const val Language_Key = "Language_Key"
    }

    fun saveLanguage(value: String) {
        viewModelScope.launch {
            repository.putString(Language_Key, value)
        }
    }

     fun getLanguage(): String = runBlocking {
         repository.getString(Language_Key) ?: English_Lang
     }
}