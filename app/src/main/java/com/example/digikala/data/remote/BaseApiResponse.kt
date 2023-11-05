package com.example.digikala.data.remote

import com.example.digikala.data.model.ResponseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class BaseApiResponse {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<ResponseResult<T>>): NetworkResult<T> =
        withContext(Dispatchers.IO) {
            try {
                val response = apiCall()
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        return@withContext NetworkResult.Success(it.message, it.data)
                    }
                }
                return@withContext error("code : ${response.code()}, message : ${response.message()}")

            } catch (exp: Exception) {
                return@withContext error(exp.message ?: exp.toString())
            }
        }

    private fun <T> error(errorMessage: String): NetworkResult<T> =
        NetworkResult.Error(" Api call failed : $errorMessage")
}