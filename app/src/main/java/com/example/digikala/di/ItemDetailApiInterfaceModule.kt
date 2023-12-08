package com.example.digikala.di

import com.example.digikala.data.remote.ItemDetailApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ItemDetailApiInterfaceModule {

    @Provides
    @Singleton
    fun provideItemDetailApiService(retrofit: Retrofit): ItemDetailApiInterface =
        retrofit.create(ItemDetailApiInterface::class.java)
}