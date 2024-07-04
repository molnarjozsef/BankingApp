package com.jozsefmolnar.bankingapp

import BankingApp
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentActivity

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BankingApp()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    BankingApp()
}
