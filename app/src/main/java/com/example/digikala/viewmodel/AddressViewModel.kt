package com.example.digikala.viewmodel

import androidx.lifecycle.ViewModel
import com.example.digikala.repository.AddressRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(private val repository: AddressRepository) :
    ViewModel() {


}