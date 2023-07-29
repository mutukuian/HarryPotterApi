package com.example.retro.core.di

import com.example.retro.core.common.Constants.BASE_URL
import com.example.retro.data.remote.network.Api
import com.example.retro.data.repository.CharactersListRepositoryImpl
import com.example.retro.domain.repository.CharactersListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesApi(): Api {
        // Create an instance of HttpLoggingInterceptor
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY) // Set the logging level

        // Create an instance of OkHttpClient and add the logging interceptor to it
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient) // Set the OkHttpClient instance with the interceptor to the Retrofit builder
            .build()
            .create(Api::class.java)
    }

    @Provides
    @Singleton
    fun providesRepositoryImplementation(api:Api):CharactersListRepository{
        return CharactersListRepositoryImpl(api)
    }
}
