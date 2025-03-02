package com.tmdb.sampleapp.presentation.application

import android.app.Application
import com.tmdb.sampleapp.data.localsource.database.AppDatabaseProvider
import com.tmdb.sampleapp.di.dataModules
import com.tmdb.sampleapp.di.domainModules
import com.tmdb.sampleapp.di.presentationModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabaseProvider.initDatabase(applicationContext)

        startKoin {
            androidLogger()
            androidContext(this@AppApplication)
            modules(listOf(dataModules, domainModules, presentationModules))
        }
    }
}