package com.paulo.android.printloglibrary.factory

import com.paulo.android.printloglibrary.Samples
import com.paulo.android.printloglibrary.constants.PrintLogConstants
import com.paulo.android.printloglibrary.utils.PrinterUtils

interface PrintLogFactory {
    /**
     * Structures
     */
    fun beginChapter(index: Int, text: String)
    fun endChapter(index: Int)
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

class PrintLogFactoryImpl(
    private val printerUtils: PrinterUtils
    ): PrintLogFactory {

    override fun breakLine() {
        printerUtils.drawExtrasBordersAndStuff(PrintLogConstants.JUMP_LINE)
    }

    override fun divider() {
        printerUtils.drawExtrasBordersAndStuff(PrintLogConstants.DIVIDER)
    }

    override fun footer() {
        printerUtils.drawExtrasBordersAndStuff(PrintLogConstants.FOOTER)
    }

    override fun beginChapter(index: Int, text: String) {
        printerUtils.setSubTitle(index, text)
        printerUtils.drawExtrasBordersAndStuff(PrintLogConstants.BEGIN_CHAPTER)
    }

    override fun endChapter(index: Int) {
        printerUtils.setSubTitleByIndex(index)
        printerUtils.drawExtrasBordersAndStuff(PrintLogConstants.END_CHAPTER)
    }

    override fun header() {
        printerUtils.drawExtrasBordersAndStuff(PrintLogConstants.HEADER)
    }

    override fun separator() {
        printerUtils.drawExtrasBordersAndStuff(PrintLogConstants.SEPARATOR)
    }

    override fun session() {
        printerUtils.drawExtrasBordersAndStuff(PrintLogConstants.SESSION)
    }

    override fun log(value: Any?) {
        printerUtils.writeLogs(PrintLogConstants.ONLY_VAL, PrintLogConstants.NOTHING, value)
    }

    override fun log(message: String) {
        printerUtils.writeLogs(PrintLogConstants.ONLY_MSG, message, null)
    }

    override fun log(message: String, value: Any?) {
        printerUtils.writeLogs(PrintLogConstants.BOTH_MSG_VAL, message, value)
    }

    override fun help() {
        Samples().help()
    }

    override fun sample1() {
        Samples().sample1()
    }

}