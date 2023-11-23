package com.example.digikala.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.digikala.repository.ProfileRepository
import com.example.digikala.ui.screens.profile.ProfileScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: ProfileRepository) :
    ViewModel() {

    // shared view model
    var screenState by mutableStateOf(ProfileScreenState.LoginState)
    var inputPhoneState by mutableStateOf("")


}