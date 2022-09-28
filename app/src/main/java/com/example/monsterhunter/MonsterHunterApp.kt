package com.example.monsterhunter

import android.app.Application
import com.example.monsterhunter.di.networkModule
import com.example.monsterhunter.di.repositoriesModule
import com.example.monsterhunter.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MonsterHunterApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MonsterHunterApp)
            modules(
                listOf(
                    repositoriesModule,
                    viewModelsModule,
                    networkModule
                )
            )
        }

    }
}