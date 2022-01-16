package com.paulo.android.printloglibrary

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.paulo.android.printloglibrary.di.printModules
import org.koin.core.context.startKoin

class PrintLogActivity: AppCompatActivity() {

    private val tag = this.localClassName

    override fun onStart() {
        super.onStart()
        Log.i(tag, "onStart::startKoin")
        startKoin() {
            printModules
        }
    }
}