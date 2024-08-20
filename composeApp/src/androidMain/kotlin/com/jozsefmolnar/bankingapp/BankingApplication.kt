package com.jozsefmolnar.bankingapp

import android.app.Application
import android.content.Context
import com.jozsefmolnar.bankingapp.di.initKoin


class BankingApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

        initKoin()
    }

    companion object {
        lateinit var appContext: Context
            private set
    }
}
