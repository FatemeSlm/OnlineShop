package com.example.digikala.di

import com.example.digikala.data.remote.CategoryApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CategoryApiInterfaceModule {

    @Provides
    @Singleton
    fun provideCategoryApiInterface(retrofit: Retrofit): CategoryApiInterface =
        retrofit.create(CategoryApiInterface::class.java)
}