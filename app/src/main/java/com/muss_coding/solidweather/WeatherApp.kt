package com.muss_coding.solidweather

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/*
 * This is the entry point for Hilt. We annotate the Application class.
 */
@HiltAndroidApp
class WeatherApp : Application()