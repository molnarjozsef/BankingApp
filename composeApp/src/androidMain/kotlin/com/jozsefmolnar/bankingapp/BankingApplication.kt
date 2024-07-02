package com.jozsefmolnar.bankingapp

import android.app.Application
import di.initKoin

class BankingApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }
}
