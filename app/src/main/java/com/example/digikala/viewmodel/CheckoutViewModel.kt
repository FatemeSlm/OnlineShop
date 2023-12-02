package com.example.digikala.viewmodel

import androidx.lifecycle.ViewModel
import com.example.digikala.repository.CheckoutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(private val repository: CheckoutRepository) :
    ViewModel() {


}