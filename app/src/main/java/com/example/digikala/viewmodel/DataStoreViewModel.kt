package com.example.digikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digikala.data.datastore.DataStoreRepository
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
        const val User_Token_Key = "User_Token_Key"
        const val User_Id_Key = "User_Id_Key"
        const val User_Phone_Key = "User_Phone_Key"
        const val User_Password_Key = "User_Password_Key"
    }

    fun saveLanguage(value: String) {
        viewModelScope.launch {
            repository.putString(Language_Key, value)
        }
    }

    fun getLanguage(): String = runBlocking {
        repository.getString(Language_Key) ?: Persian_Lang
    }

    fun saveUserToken(value: String) {
        viewModelScope.launch {
            repository.putString(User_Token_Key, value)
        }
    }

    fun getUserToken(): String? = runBlocking {
        repository.getString(User_Token_Key)
    }

    fun saveUserId(value: String) {
        viewModelScope.launch {
            repository.putString(User_Id_Key, value)
        }
    }

    fun getUserId(): String? = runBlocking {
        repository.getString(User_Id_Key)
    }

    fun saveUserPhone(value: String) {
        viewModelScope.launch {
            repository.putString(User_Phone_Key, value)
        }
    }

    fun getUserPhone(): String? = runBlocking {
        repository.getString(User_Phone_Key)
    }

    fun saveUserPassword(value: String) {
        viewModelScope.launch {
            repository.putString(User_Password_Key, value)
        }
    }

    fun getUserPassword(): String? = runBlocking {
        repository.getString(User_Password_Key)
    }
}