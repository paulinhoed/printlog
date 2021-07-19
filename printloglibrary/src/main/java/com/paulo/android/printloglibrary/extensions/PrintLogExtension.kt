package com.paulo.android.printloglibrary.extensions

import android.annotation.SuppressLint
import com.paulo.android.printloglibrary.constants.PrintLogConstants.BALL
import com.paulo.android.printloglibrary.constants.PrintLogConstants.BASE
import com.paulo.android.printloglibrary.constants.PrintLogConstants.BASE_1
import com.paulo.android.printloglibrary.constants.PrintLogConstants.BASE_3
import com.paulo.android.printloglibrary.constants.PrintLogConstants.BASE_4
import com.paulo.android.printloglibrary.constants.PrintLogConstants.BLANK_SPACE
import com.paulo.android.printloglibrary.constants.PrintLogConstants.BREAKLINE
import com.paulo.android.printloglibrary.constants.PrintLogConstants.CLOSE_BRACKETS
import com.paulo.android.printloglibrary.constants.PrintLogConstants.CLOSE_KEYS
import com.paulo.android.printloglibrary.constants.PrintLogConstants.DOUBLE_DOTS
import com.paulo.android.printloglibrary.constants.PrintLogConstants.FULL_DATA_FORMAT
import com.paulo.android.printloglibrary.constants.PrintLogConstants.HASHTAG
import com.paulo.android.printloglibrary.constants.PrintLogConstants.INDEX
import com.paulo.android.printloglibrary.constants.PrintLogConstants.LINE
import com.paulo.android.printloglibrary.constants.PrintLogConstants.LOG
import com.paulo.android.printloglibrary.constants.PrintLogConstants.OPEN_BRACKETS
import com.paulo.android.printloglibrary.constants.PrintLogConstants.OPEN_KEYS
import com.paulo.android.printloglibrary.constants.PrintLogConstants.POINTER
import com.paulo.android.printloglibrary.constants.PrintLogConstants.SEMICOLON
import com.paulo.android.printloglibrary.constants.PrintLogConstants.UNDER
import java.lang.StringBuilder
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun getLine(): Int {
    return Throwable().stackTrace[4].lineNumber
}

@SuppressLint("SimpleDateFormat")
fun getTime(): String {
    val dateFormat: DateFormat = SimpleDateFormat(FULL_DATA_FORMAT)
    val timeStart = Calendar.getInstance()
    return dateFormat.format(timeStart.time)
}

fun buildLogText(time: String, line: Int, title: String, message: Any?): String {
    val builder = StringBuilder()
    builder.append(HASHTAG)
    builder.append(BLANK_SPACE)
    builder.append(LOG)
    builder.append(BLANK_SPACE)
    builder.append(OPEN_KEYS)
    builder.append(time)
    builder.append(CLOSE_KEYS)
    builder.append(BASE_4)
    builder.append(OPEN_KEYS)
    builder.append(LINE)
    builder.append(DOUBLE_DOTS)
    builder.append(line)
    builder.append(CLOSE_KEYS)
//    builder.append(BREAKLINE)
    builder.append(BLANK_SPACE)
    builder.append(BASE_1)
//    builder.append(HASHTAG)
    builder.append(BLANK_SPACE)
    builder.append(title)
    builder.append(BLANK_SPACE)
    builder.append(POINTER)
    builder.append(BLANK_SPACE)
    builder.append(message)
    return builder.toString()
}

fun buildHeaderAndFooterBlock(type: String, text: String, subLevelCounter: Int): String {
    var counter = ""
    counter = when {
        subLevelCounter < 10 -> "00$subLevelCounter"
        subLevelCounter in 10..99 -> "0$subLevelCounter"
        else -> subLevelCounter.toString()
    }
    return " ($counter)$type [$text] "
}

fun buildNewBase(blockBaseContent: String, maxLength: Int): String {
    var blockBase = blockBaseContent
    val limit = maxLength - 2
    val base = DOUBLE_DOTS
    var newBase = ""
    var startBlockBaseLength = blockBase.length
    if (startBlockBaseLength >= maxLength) {
        blockBase = blockBase.substring(0, limit)
        startBlockBaseLength = blockBase.length
    }
    val diffStart = maxLength - startBlockBaseLength
    var i = 0
    while (i < diffStart) {
        newBase += base
        i++
    }
    return newBase
}

fun buildLists(value: Any?, builder: StringBuilder, i: Int): StringBuilder{
    var index = ""
    index = when {
        i < 10 -> "00$i"
        i in 10..99 -> "0$i"
        else -> i.toString()
    }
    builder.append(HASHTAG)
    builder.append(BLANK_SPACE)
    builder.append(BLANK_SPACE)
    builder.append(OPEN_KEYS)
    builder.append(BALL)
    builder.append(UNDER)
    builder.append(INDEX)
    builder.append(OPEN_KEYS)
    builder.append(index)
    builder.append(CLOSE_KEYS)
    builder.append(BLANK_SPACE)
    builder.append(value)
    builder.append(BLANK_SPACE)
    builder.append(SEMICOLON)
    builder.append(BREAKLINE)
    return builder
}

fun buildHeaderStructureList(maxLength: Int): StringBuilder {
    val builder = StringBuilder()
    builder.append(BREAKLINE)
    builder.append(HASHTAG)
    builder.append(BLANK_SPACE)
    builder.append(BLANK_SPACE)
    builder.append(OPEN_KEYS)
    for(i in 0..maxLength)
        builder.append(BASE_3)
    builder.append(BASE_1)
    builder.append(BREAKLINE)
    return builder
}

fun buildFooterStructureList(maxLength: Int, builder: StringBuilder): StringBuilder {
    builder.append(HASHTAG)
    builder.append(BLANK_SPACE)
    builder.append(BLANK_SPACE)
    builder.append(OPEN_KEYS)
    for(i in 0..maxLength)
        builder.append(BASE_3)
    builder.append(BASE_1)
    return builder
}