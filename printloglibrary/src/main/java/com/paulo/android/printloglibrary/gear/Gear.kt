package com.paulo.android.printloglibrary.gear

import com.paulo.android.printloglibrary.constants.PrintLogConstants.BASE_3
import com.paulo.android.printloglibrary.constants.PrintLogConstants.BASE_4
import com.paulo.android.printloglibrary.constants.PrintLogConstants.BASE_END
import com.paulo.android.printloglibrary.constants.PrintLogConstants.BASE_START
import com.paulo.android.printloglibrary.constants.PrintLogConstants.BLANK_SPACE
import com.paulo.android.printloglibrary.constants.PrintLogConstants.BREAKLINE
import com.paulo.android.printloglibrary.constants.PrintLogConstants.EMPTY
import com.paulo.android.printloglibrary.constants.PrintLogConstants.END
import com.paulo.android.printloglibrary.constants.PrintLogConstants.HASHTAG
import com.paulo.android.printloglibrary.constants.PrintLogConstants.INIT
import com.paulo.android.printloglibrary.constants.PrintLogConstants.NULL
import com.paulo.android.printloglibrary.extensions.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class Gear(private val maxLengthBlock: Int, private var subLevelCounter: Int, private val timeStart: Calendar?) {

    internal fun processBlock(text: String, option: Int): Any {
        var newBase = ""
        var block = ""
        val maxLength = maxLengthBlock
        var blockBase = ""
        when (option) {
            0 -> blockBase = buildHeaderAndFooterBlock(INIT, text, subLevelCounter)
            1 -> {
                blockBase = buildHeaderAndFooterBlock(END, text, subLevelCounter)
                blockBase = buildElapsedTime(blockBase, processTime())
            }
        }
        newBase = buildNewBase(blockBase, maxLength)
        val bases: Map<String, String> = processBase(newBase)
        block = HASHTAG + bases[BASE_START] + blockBase + bases[BASE_END] + HASHTAG
        return block
    }

    internal fun processLineBlock(option: Int): Any {
        var base = ""
        var newBase = ""
        when (option) {
            1 -> base = BASE_3
            2 -> base = BASE_4
        }
        for (i in 0..maxLengthBlock) {
            newBase += if (i == 0 || i == maxLengthBlock)
                HASHTAG
            else base
        }
        return newBase
    }

    internal fun processBlankLine(): String {
        return HASHTAG + BREAKLINE
    }

    private fun processBase(base: String): Map<String, String> {
        val baseLength = base.length
        var result = kotlin.math.ceil((baseLength / 2).toDouble()).toInt()
        if (result == 0) result++
        val startBase = base.substring(0, result - 1)
        val endBase = base.substring(result, baseLength)
        val bases: MutableMap<String, String> = HashMap()
        bases[BASE_START] = startBase
        bases[BASE_END] = endBase
        return bases
    }

    internal fun processFinalText(text: String): String {
        val limit = maxLengthBlock
        val textLength = text.length
        return if (textLength <= limit) text else {
            val subTexts = text.split("(?<=\\G.{$limit})".toRegex()).toTypedArray()
            var newText = ""
            val subTextsLength = subTexts.size
            for (i in 0 until subTextsLength) {
                newText += if (i < subTextsLength - 1) {
                    """
     ${subTexts[i]}

     """.trimIndent()
                } else HASHTAG + BLANK_SPACE + subTexts[i]
            }
            newText
        }
    }

    internal fun processValue(value: Any?): Any? {
        val maxLength = maxLengthBlock - 5
        var thisValue = value

        if (value == null)
            thisValue = NULL
        else {
            if (value.javaClass == String::class.java) {
                if ((value as String).isEmpty()) thisValue = EMPTY
            } else if (value.javaClass == Array<String>::class.java) {
                val list = value as Array<*>
                if (list.isEmpty()) thisValue = EMPTY else {
                    var builder = buildHeaderStructureList(maxLength)
                    for ((index, v) in value.withIndex()) {
                        builder = buildLists(v, builder, index)
                    }
                    builder = buildFooterStructureList(maxLength, builder)
                    thisValue = builder.toString()
                }
            } else if (value.javaClass == Array<Long>::class.java) {
                val list = value as Array<*>
                if (list.isEmpty()) thisValue = EMPTY else {
                    var builder = buildHeaderStructureList(maxLength)
                    for ((index, v) in value.withIndex()) {
                        builder = buildLists(v, builder, index)
                    }
                    builder = buildFooterStructureList(maxLength, builder)
                    thisValue = builder.toString()
                }
            } else if (value.javaClass == ArrayList::class.java) {
                if ((value as ArrayList<*>).isEmpty()) thisValue = EMPTY else {
                    var builder = buildHeaderStructureList(maxLength)
                    for ((index, v) in value.withIndex()) {
                        builder = buildLists(v, builder, index)
                    }
                    builder = buildFooterStructureList(maxLength, builder)
                    thisValue = builder.toString()
                }
            } else if (value.javaClass == HashMap::class.java) {
                if ((value as Map<*, *>).isEmpty()) thisValue = EMPTY else {
                    var builder = buildHeaderStructureList(maxLength)
                    for ((_key, _value) in value as HashMap<*, *>) {
                        val newVal = "$_key = $_value"
                        builder = buildLists(newVal, builder, -1)
                    }
                    builder = buildFooterStructureList(maxLength, builder)
                    thisValue = builder.toString()
                }
            }
        }
        return thisValue
    }

    private fun processTime(): String {
        val timeFinal = Calendar.getInstance()
        return if (timeStart != null) {
            val diff = timeFinal.time.time - timeStart!!.time.time
//            val diffDays = (diff / (24 * 60 * 60 * 1000)).toInt()
            val diffHour = (diff / (60 * 60 * 1000)).toInt()
            val diffMin = (diff / (60 * 1000)).toInt()
            val diffSec = (diff / 1000).toInt()
            var hours = ""
            var mins = ""
            var secs = ""
            if (diffHour in 0..9) hours += "0$diffHour" else hours += diffHour
            if (diffMin in 0..9) mins += "0$diffMin" else mins += diffMin
            if (diffSec in 0..9) secs += "0$diffSec" else secs += diffSec
            buildTimeBlock(hours, mins, secs)
        } else ""
    }
}