package com.example.digikala.util

import androidx.core.text.isDigitsOnly

object InputValidation {

    fun isValidPhoneNumber(input: String): Boolean {
        return input.isNotEmpty()
                && input.isNotBlank()
                && input.isDigitsOnly()
                && input.startsWith("09")
                && input.length == 11
    }

    fun isValidEmail(input: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(input).matches()
    }
}