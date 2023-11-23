package com.example.digikala.di

import com.example.digikala.data.remote.ProfileApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProfileApiInterfaceModule {

    @Provides
    @Singleton
    fun provideProfileApiService(retrofit: Retrofit): ProfileApiInterface =
        retrofit.create(ProfileApiInterface::class.java)
}