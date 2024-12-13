package com.sukajee.library

import android.app.Application
import com.sukajee.library.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class LibraryApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@LibraryApplication)
        }
    }
}