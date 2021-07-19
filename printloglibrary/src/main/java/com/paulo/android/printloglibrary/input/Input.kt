package com.paulo.android.printloglibrary.input

import com.paulo.android.printloglibrary.constants.PrintLogConstants.BOTH_MSG_VAL
import com.paulo.android.printloglibrary.constants.PrintLogConstants.DIVIDER
import com.paulo.android.printloglibrary.constants.PrintLogConstants.FOOTER
import com.paulo.android.printloglibrary.constants.PrintLogConstants.HEADER
import com.paulo.android.printloglibrary.constants.PrintLogConstants.JUMP_LINE
import com.paulo.android.printloglibrary.constants.PrintLogConstants.ONLY_MSG
import com.paulo.android.printloglibrary.constants.PrintLogConstants.ONLY_VAL
import com.paulo.android.printloglibrary.constants.PrintLogConstants.SEPARATOR
import com.paulo.android.printloglibrary.extensions.buildLogText
import com.paulo.android.printloglibrary.extensions.getLine
import com.paulo.android.printloglibrary.extensions.getTime
import com.paulo.android.printloglibrary.gear.Gear

class Input {
    private val print: Boolean
    private val title: String

    private var gear: Gear? = null

    constructor(print: Boolean, title: String, subLevelCounter: Int) {
        this.print = print
        this.title = title
        this.gear = Gear(100, subLevelCounter)
    }

    constructor(print: Boolean, title: String, subLevelCounter: Int, maxLengthBlock: Int) {
        this.print = print
        this.title = title
        this.gear = Gear(maxLengthBlock, subLevelCounter)
    }

    fun drawExtras(option: String) {
        if (print) {
            when (option) {
                HEADER -> printHeader()
                FOOTER -> printFooter()
                DIVIDER -> printDivider()
                SEPARATOR -> printSeparator()
                JUMP_LINE -> printBlankSpace()
            }
        }
    }

    fun printLogs(option: String, message: String, value: Any?) {
        if (print) {
            when (option) {
                ONLY_MSG -> printLog(message)
                ONLY_VAL -> printLog(value)
                BOTH_MSG_VAL -> printLog(message, value)
            }
        }
    }

    private fun printHeader() {
        println(gear?.processLineBlock(1))
        println(gear?.processBlock(title, 0))
        println(gear?.processLineBlock(1))
    }

    private fun printFooter() {
        printBlankSpace()
        println(gear?.processLineBlock(1))
        println(gear?.processBlock(title, 1))
        println(gear?.processLineBlock(1))
    }

    private fun printDivider() {
        println(gear?.processLineBlock(1))
    }

    private fun printSeparator() {
        println(gear?.processLineBlock(2))
    }

    private fun printBlankSpace() {
        print(gear?.processBlankLine())
    }

    private fun printLog(msg: String) {
        if (print) {
            val line = getLine()
            val time = getTime()
            val text = buildLogText(time, line, title, msg)
            println(gear?.processFinalText(text))
        }
    }

    private fun printLog(value: Any?) {
        val thisValue: Any?
        if (print) {
            val line = getLine()
            val time = getTime()
            thisValue = gear?.processValue(value)
            val text = buildLogText(time, line, title, thisValue)
            println(gear?.processFinalText(text))
        }
    }

    private fun printLog(msg: String, value: Any?) {
        val thisValue: Any?
        if (print) {
            val line = getLine()
            val time = getTime()
            thisValue = gear?.processValue(value)
            val msgWithValue = "$msg: $thisValue"
            val text = buildLogText(time, line, title, msgWithValue)
            println(gear?.processFinalText(text))
        }
    }

}