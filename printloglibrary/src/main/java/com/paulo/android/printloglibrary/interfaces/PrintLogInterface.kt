package com.paulo.android.printloglibrary.interfaces

interface PrintLogInterface {
    /**
     * Structures
     */
    fun header()
    fun footer()
    fun divider()
    fun separator()
    fun session()
    fun breakLine()

    /**
     * Loggers
     */
    fun log(value: Any?)
    fun log(message: String)
    fun log(message: String, value: Any?)

    /**
     * Samples & Helpers
     */
    fun help()
    fun sample1()
}