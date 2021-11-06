package com.paulo.android.printloglibrary.di

import com.paulo.android.printloglibrary.PrintLog
import com.paulo.android.printloglibrary.core.MainProcess
import com.paulo.android.printloglibrary.core.ProcessValue
import com.paulo.android.printloglibrary.factory.PrintLogFactory
import com.paulo.android.printloglibrary.factory.PrintLogFactoryImpl
import com.paulo.android.printloglibrary.utils.PrinterUtils
import com.paulo.android.printloglibrary.utils.PrinterUtilsImpl
import org.koin.dsl.module

val printModules = module {
    factory { PrintLog() }
//    single { MainProcess(get()) }
//
//    single<PrinterUtils> {
//        PrinterUtilsImpl(
//            configModel = get(),
//            gear = get())
//    }
//
//    single<PrintLogFactory> {
//        PrintLogFactoryImpl(
//            printerUtils = get())
//    }
//
//
//    factory { ProcessValue(get()) }

}