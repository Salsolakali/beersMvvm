package com.example.beersmvvm.core

import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Application: MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
    }
}