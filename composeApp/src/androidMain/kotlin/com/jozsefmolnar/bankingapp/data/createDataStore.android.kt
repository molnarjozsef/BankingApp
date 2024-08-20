package com.jozsefmolnar.bankingapp.data

import com.jozsefmolnar.bankingapp.BankingApplication

actual fun producePath(): String {
    val appContext = BankingApplication.appContext
    return appContext.filesDir.resolve(DataStoreFileName).absolutePath
}
