package com.paulo.android.printloglibrary.interfaces

interface PrintLogInterface {
    /**
     * Structures
     */
    fun header()
    fun footer()
    fun divider()
    fun separator()
    fun breakLine()

    /**
     * Loggers
     */
    fun log(value: Any?)
    fun log(msg: String)
    fun log(msg: String, value: Any?)

    /**
     * Samples & Helpers
     */
    fun help()
    fun sample1()
}