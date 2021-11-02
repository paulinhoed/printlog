package com.paulo.android.printloglibrary.utils

import com.paulo.android.printloglibrary.constants.PrintLogConstants.BOTH_MSG_VAL
import com.paulo.android.printloglibrary.constants.PrintLogConstants.DIVIDER
import com.paulo.android.printloglibrary.constants.PrintLogConstants.FOOTER
import com.paulo.android.printloglibrary.constants.PrintLogConstants.HEADER
import com.paulo.android.printloglibrary.constants.PrintLogConstants.JUMP_LINE
import com.paulo.android.printloglibrary.constants.PrintLogConstants.ONLY_MSG
import com.paulo.android.printloglibrary.constants.PrintLogConstants.ONLY_VAL
import com.paulo.android.printloglibrary.constants.PrintLogConstants.SEPARATOR
import com.paulo.android.printloglibrary.constants.PrintLogConstants.SESSION_INTERVAL
import com.paulo.android.printloglibrary.core.ProcessCore
import com.paulo.android.printloglibrary.extensions.*
import java.util.*

class Printer {
    private val print: Boolean
    private val title: String

    private var gear: ProcessCore? = null
    private var sprayBrush: SprayBrush? = null

    constructor(print: Boolean, title: String, subLevelCounter: Int, timeStart: Calendar?) {
        this.print = print
        this.title = getTitleFormatted(title, 100)
        this.gear = ProcessCore(100, subLevelCounter, timeStart)

        this.sprayBrush = SprayBrush(this.gear)
    }

    constructor(print: Boolean, title: String, subLevelCounter: Int, maxLengthBlock: Int, timeStart: Calendar?) {
        this.print = print
        this.title = getTitleFormatted(title, normalizeMaxLength(maxLengthBlock))
        this.gear = ProcessCore(normalizeMaxLength(maxLengthBlock), subLevelCounter, timeStart)

        this.sprayBrush = SprayBrush(this.gear)
    }

    fun drawExtrasBordersAndStuff(option: String) {
        if (print) {
            when (option) {
                HEADER -> sprayBrush?.header(this.title)
                FOOTER -> sprayBrush?.footer(this.title)
                DIVIDER -> sprayBrush?.divider()
                SEPARATOR -> sprayBrush?.separator()
                SESSION_INTERVAL -> sprayBrush?.session()
                JUMP_LINE -> sprayBrush?.blankSpace()
            }
        }
    }

    fun writeLogs(option: String, message: String, value: Any?) {
        if (print) {
            when (option) {
                ONLY_MSG -> log(message)
                ONLY_VAL -> log(value)
                BOTH_MSG_VAL -> log(message, value)
            }
        }
    }

    private fun log(msg: String) {
        if (print) {
            val line = getLine()
            val time = getTime()
            val text = buildLogText(time, line, title, msg)
            println(gear?.processFinalText(text))
        }
    }

    private fun log(value: Any?) {
        val thisValue: Any?
        if (print) {
            val line = getLine()
            val time = getTime()
            thisValue = gear?.processValue(value)
            val text = buildLogText(time, line, title, thisValue)
            println(gear?.processFinalText(text))
        }
    }

    private fun log(msg: String, value: Any?) {
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