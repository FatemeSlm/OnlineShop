package com.example.digikala.di

import com.example.digikala.util.Constants.Api_Key
import com.example.digikala.util.Constants.App_Language
import com.example.digikala.util.Constants.Base_Url
import com.example.digikala.util.Constants.Timeout_In_Second
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    internal fun interceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(Timeout_In_Second, TimeUnit.SECONDS)
        .readTimeout(Timeout_In_Second, TimeUnit.SECONDS)
        .writeTimeout(Timeout_In_Second, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("x-api-key", Api_Key)
                .addHeader("lang", App_Language)
            chain.proceed(request.build())
        }
        .addInterceptor(interceptor())
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(Base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
}