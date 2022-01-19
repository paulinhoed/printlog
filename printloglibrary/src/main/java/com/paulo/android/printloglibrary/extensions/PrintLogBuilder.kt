package com.paulo.android.printloglibrary.extensions

import android.annotation.SuppressLint
import com.paulo.android.printloglibrary.constants.PrintLogConstants.BALL
import com.paulo.android.printloglibrary.constants.PrintLogConstants.BASE_1
import com.paulo.android.printloglibrary.constants.PrintLogConstants.BASE_EQUAL_SYMBOL
import com.paulo.android.printloglibrary.constants.PrintLogConstants.BASE_HIFEN_SYMBOL
import com.paulo.android.printloglibrary.constants.PrintLogConstants.BLANK_SPACE
import com.paulo.android.printloglibrary.constants.PrintLogConstants.BREAK_LINE
import com.paulo.android.printloglibrary.constants.PrintLogConstants.CLOSE_KEYS
import com.paulo.android.printloglibrary.constants.PrintLogConstants.CLOSE_PARENTHESIS
import com.paulo.android.printloglibrary.constants.PrintLogConstants.DOUBLE_DOTS
import com.paulo.android.printloglibrary.constants.PrintLogConstants.ELAPSED_TIME
import com.paulo.android.printloglibrary.constants.PrintLogConstants.FULL_DATA_FORMAT
import com.paulo.android.printloglibrary.constants.PrintLogConstants.HASHTAG
import com.paulo.android.printloglibrary.constants.PrintLogConstants.HOURS
import com.paulo.android.printloglibrary.constants.PrintLogConstants.KEY
import com.paulo.android.printloglibrary.constants.PrintLogConstants.LINE
import com.paulo.android.printloglibrary.constants.PrintLogConstants.MAIN_BASE
import com.paulo.android.printloglibrary.constants.PrintLogConstants.MILI_SECONDS
import com.paulo.android.printloglibrary.constants.PrintLogConstants.MINUTES
import com.paulo.android.printloglibrary.constants.PrintLogConstants.OPEN_KEYS
import com.paulo.android.printloglibrary.constants.PrintLogConstants.OPEN_PARENTHESIS
import com.paulo.android.printloglibrary.constants.PrintLogConstants.PLUS
import com.paulo.android.printloglibrary.constants.PrintLogConstants.POINTER
import com.paulo.android.printloglibrary.constants.PrintLogConstants.SECONDS
import com.paulo.android.printloglibrary.constants.PrintLogConstants.SESSION_BLOCK
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties

fun getTitleFormatted(title: String, maxLength: Int): String {
    val limit = 44 //Default
    return if(title.length > limit) {
        val substringMaxLength = maxLength - limit
        title.substring(0, substringMaxLength)
    } else title
}

fun getLine(): Int {
    return Throwable().stackTrace[4].lineNumber
}

fun normalizeMaxLength(maxLength: Int?): Int {
    return if (maxLength != null) {
        if (maxLength < 100)
            100
        else
            maxLength.toInt()
    }
    else 100
}

@SuppressLint("SimpleDateFormat")
fun getTime(): String {
    val dateFormat: DateFormat = SimpleDateFormat(FULL_DATA_FORMAT)
    val timeStart = Calendar.getInstance()
    return dateFormat.format(timeStart.time)
}

fun buildLogText(time: String, line: Int, title: String, message: Any?): String {
    val builder = StringBuilder()
//    builder.append(HASHTAG)
//    builder.append(BLANK_SPACE)
//    builder.append(LOG)
//    builder.append(BLANK_SPACE)
    builder.append(OPEN_KEYS)
    builder.append(time)
    builder.append(CLOSE_KEYS)
    builder.append(BASE_HIFEN_SYMBOL)
    builder.append(OPEN_KEYS)
    builder.append(LINE)
    builder.append(DOUBLE_DOTS)
    builder.append(line)
    builder.append(CLOSE_KEYS)
    builder.append(BLANK_SPACE)
    builder.append(BASE_1)
    builder.append(BLANK_SPACE)
    builder.append(title)
    builder.append(BLANK_SPACE)
    builder.append(POINTER)
    builder.append(BLANK_SPACE)
    builder.append(message)
    return builder.toString()
}

fun buildHeaderAndFooterBlock(type: String, text: String, subLevelCounter: Int): String {
    val counter = when {
        subLevelCounter < 10 -> "00$subLevelCounter"
        subLevelCounter in 10..99 -> "0$subLevelCounter"
        else -> subLevelCounter.toString()
    }
    return OPEN_PARENTHESIS + counter +
            CLOSE_PARENTHESIS + type +
            OPEN_KEYS + text + CLOSE_KEYS
}

fun buildNewBase(blockBaseContent: String, maxLength: Int): String {
    var blockBase = blockBaseContent
    val limit = maxLength - 2
    val base = DOUBLE_DOTS
    var newBase = ""
    var startBlockBaseLength = blockBase.length
//    println("# --- buildNewBase --- #")
//    println("# blockBase:$blockBase")
//    println("# limit:$limit")
//    println("# startBlockBaseLength:$startBlockBaseLength")
//    println("# blockLength:$blockLength")
//    println("# --- ~.~ --- #")
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


fun buildNewLists(value: Any?, builder: StringBuilder, i: Int): StringBuilder{
    val index = when (i) {
        in 0..9 -> "00$i"
        in 10..99 -> "0$i"
        in 100..999 -> "$i"
        else -> ""
    }
    builder.append(HASHTAG)
    builder.append(BLANK_SPACE)
    builder.append(BLANK_SPACE)
    builder.append(PLUS)
    builder.append(BLANK_SPACE)
    builder.append(BLANK_SPACE)
    if(index.isNotEmpty()) {
        builder.append(OPEN_PARENTHESIS)
        builder.append(index)
        builder.append(CLOSE_PARENTHESIS)
        builder.append(BLANK_SPACE)
    }
    builder.append(value)
    builder.append(BLANK_SPACE)
    builder.append(BREAK_LINE)
    return builder
}

fun buildLists(value: Any?, builder: StringBuilder, i: Int): StringBuilder{
    val index = when (i) {
        in 0..9 -> "00$i"
        in 10..99 -> "0$i"
        in 100..999 -> "$i"
        else -> ""
    }
    builder.append(HASHTAG)
    builder.append(BLANK_SPACE)
    builder.append(BLANK_SPACE)
    builder.append(OPEN_KEYS)
    builder.append(BALL)
    builder.append(BLANK_SPACE)
//    if(index.isNotEmpty()) {
//        builder.append(INDEX)
//        builder.append(OPEN_KEYS)
//        builder.append(index)
//        builder.append(CLOSE_KEYS)
//        builder.append(CURSOR)
//        builder.append(BLANK_SPACE)
//        builder.append(value)
//    }
//    else {
        builder.append(KEY)
        builder.append(OPEN_KEYS)
        builder.append(value)
        builder.append(CLOSE_KEYS)
//    }
    builder.append(BLANK_SPACE)
    builder.append(BREAK_LINE)
    return builder
}

fun buildSessionBlock(text: String? = null): StringBuilder {
    val builder = StringBuilder()
    if (text == null) {
        builder.append(SESSION_BLOCK)
    }
    else {
        builder.append(OPEN_KEYS)
        builder.append(text)
        builder.append(CLOSE_KEYS)
    }
    return builder
}

fun buildSessionWrapper(builder: StringBuilder, blockLength: Int): StringBuilder {
    val thisBuilder = StringBuilder()
    var newBase = ""
    for (i in 0..blockLength-3) {
        newBase += if (i == 0)
            MAIN_BASE
        else if(i == blockLength-3)
            BASE_1
        else BASE_HIFEN_SYMBOL
    }
    thisBuilder.append(BREAK_LINE)
    thisBuilder.append(newBase)
    thisBuilder.append(BREAK_LINE)
    thisBuilder.append(builder)
    thisBuilder.append(newBase)
    return thisBuilder
}

fun buildSessionStructureList(maxLength: Int, builder: StringBuilder?): StringBuilder {
    val thisBuilder: StringBuilder
        if(builder == null){
            thisBuilder = StringBuilder()
            thisBuilder.append(BREAK_LINE)
        }
    else {
            thisBuilder = builder
        }
    thisBuilder.append(HASHTAG)
    thisBuilder.append(BLANK_SPACE)
    thisBuilder.append(BLANK_SPACE)
    thisBuilder.append(PLUS)
    for(i in 0..maxLength)
        thisBuilder.append(BASE_HIFEN_SYMBOL)
    thisBuilder.append(BASE_1)
    thisBuilder.append(BREAK_LINE)
    return thisBuilder
}

fun buildHeaderStructureList(maxLength: Int): StringBuilder {
    val builder = StringBuilder()
    builder.append(HASHTAG)
    builder.append(BLANK_SPACE)
    builder.append(BLANK_SPACE)
    builder.append(OPEN_KEYS)
    for(i in 0..maxLength)
        builder.append(BASE_EQUAL_SYMBOL)
    builder.append(BASE_1)
    builder.append(BREAK_LINE)
    return builder
}

fun buildFooterStructureList(maxLength: Int, builder: StringBuilder): StringBuilder {
    builder.append(HASHTAG)
    builder.append(BLANK_SPACE)
    builder.append(BLANK_SPACE)
    builder.append(OPEN_KEYS)
    for(i in 0..maxLength)
        builder.append(BASE_EQUAL_SYMBOL)
    builder.append(BASE_1)
    return builder
}

fun buildTimeBlock(hours: String,
                   mins: String,
                   secs: String,
                   milS: String): String {
    val builder = StringBuilder()
    builder.append(ELAPSED_TIME)
    builder.append(OPEN_KEYS)
    builder.append(hours)
    builder.append(HOURS)
    builder.append(mins)
    builder.append(MINUTES)
    builder.append(secs)
    builder.append(SECONDS)
    builder.append(milS)
    builder.append(MILI_SECONDS)
    builder.append(CLOSE_KEYS)
    return builder.toString()
}

fun buildElapsedTime(blockBase: String, elapsedTime: String): String {
    val builder = StringBuilder()
    builder.append(blockBase)
    builder.append(BASE_1)
    builder.append(BLANK_SPACE)
    builder.append(elapsedTime)
    return builder.toString()
}

fun getProperties(value: Any): Collection<KProperty1<Any, *>> {
    return value.javaClass.kotlin.memberProperties
}