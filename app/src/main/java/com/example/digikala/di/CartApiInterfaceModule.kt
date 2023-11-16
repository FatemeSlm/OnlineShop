package com.example.digikala.di

import com.example.digikala.data.remote.CartApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CartApiInterfaceModule {

    @Provides
    @Singleton
    fun provideCartApiInterface(retrofit: Retrofit): CartApiInterface =
        retrofit.create(CartApiInterface::class.java)
}