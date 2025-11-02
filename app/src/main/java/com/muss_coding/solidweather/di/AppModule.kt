package com.muss_coding.solidweather.di

import com.muss_coding.solidweather.data.remote.WeatherApi
import com.muss_coding.solidweather.data.repository.WeatherRepositoryImpl
import com.muss_coding.solidweather.domain.repository.WeatherRepository
import com.muss_coding.solidweather.domain.use_case.GetWeatherUseCase
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/*
 * DI Layer: Hilt AppModule
 * This Hilt module tells Dagger Hilt how to create and provide
 * instances of our dependencies (like Retrofit, the API, and the Repository).
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        // FIX: Build a basic Moshi instance.
        // The codegen adapters will be picked up automatically.
        return Moshi.Builder().build()
    }
    @Provides
    @Singleton
    fun provideWeatherApi(moshi: Moshi): WeatherApi {
        return Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/")
            // FIX: Pass in our custom Moshi instance
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(api: WeatherApi): WeatherRepository {
        // Here we bind the implementation (WeatherRepositoryImpl)
        // to the abstraction (WeatherRepository). This is DIP in action.
        return WeatherRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideWeatherGetWeatherUseCase(repository: WeatherRepository): GetWeatherUseCase {
        // Here we bind the implementation (WeatherRepositoryImpl)
        // to the abstraction (WeatherRepository). This is DIP in action.
        return GetWeatherUseCase(repository = repository)
    }
}
