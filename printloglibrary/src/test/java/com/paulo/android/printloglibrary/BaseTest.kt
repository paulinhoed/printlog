package com.paulo.android.printloglibrary

import com.paulo.android.printloglibrary.di.printModules
import org.junit.After
import org.junit.Before
import org.koin.core.context.GlobalContext

open class BaseTest {

    @Before
    fun setUp() {
        GlobalContext.startKoin {
            modules(printModules)
        }
    }

    @After
    fun tearDown() {
        GlobalContext.stopKoin()
    }
}