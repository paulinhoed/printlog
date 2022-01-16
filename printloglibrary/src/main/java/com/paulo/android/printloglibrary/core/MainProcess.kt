package com.paulo.android.printloglibrary.core

import com.paulo.android.printloglibrary.constants.PrintLogConstants.BASE_EQUAL_SYMBOL
import com.paulo.android.printloglibrary.constants.PrintLogConstants.BASE_HIFEN_SYMBOL
import com.paulo.android.printloglibrary.constants.PrintLogConstants.BASE_END
import com.paulo.android.printloglibrary.constants.PrintLogConstants.BASE_START
import com.paulo.android.printloglibrary.constants.PrintLogConstants.BLANK_SPACE
import com.paulo.android.printloglibrary.constants.PrintLogConstants.BREAK_LINE
import com.paulo.android.printloglibrary.constants.PrintLogConstants.END
import com.paulo.android.printloglibrary.constants.PrintLogConstants.HASHTAG
import com.paulo.android.printloglibrary.constants.PrintLogConstants.INIT
import com.paulo.android.printloglibrary.constants.PrintLogConstants.SESSION_BLOCK
import com.paulo.android.printloglibrary.extensions.*
import com.paulo.android.printloglibrary.model.PrintLogConfigModel
import java.util.*
import kotlin.collections.HashMap

class MainProcess(
    private val configModel: PrintLogConfigModel
) {

    private val processValue = ProcessValue(configModel)

    fun getProcessValue(value: Any?): String {
        return processValue.execute(value)
    }
    
    internal fun processBlock(text: String, option: Int): Any {
        val maxLength = processLimit(text, configModel.blockLength)
        var blockBase = ""
        when (option) {
            0 -> blockBase = buildHeaderAndFooterBlock(INIT, processText(text), configModel.index)
            1 -> {
                blockBase = buildHeaderAndFooterBlock(END, processText(text), configModel.index)
                blockBase = buildElapsedTime(blockBase, processTime())
            }
        }
        val newBase = buildNewBase(blockBase, maxLength)
        val bases: Map<String, String> = processBase(newBase)
        return HASHTAG + bases[BASE_START] + blockBase + bases[BASE_END] + HASHTAG
    }

    internal fun processSessionBlock(text: String? = null): Any {
        val maxLength: Int = when (text) {
            null -> processLimit(SESSION_BLOCK, configModel.blockLength)
            else -> processLimit(text, configModel.blockLength)
        }
        val blockBase: String = when (text) {
            null -> buildSessionBlock(null).toString()
            else -> buildSessionBlock(text).toString()
        }
        val newBase = buildNewBase(blockBase, maxLength)
        val bases: Map<String, String> = processBase(newBase)
        return HASHTAG + bases[BASE_START] + blockBase + bases[BASE_END] + HASHTAG
    }

    internal fun processLineBlock(option: Int): Any {
        var base = ""
        var newBase = ""
        when (option) {
            1 -> base = BASE_EQUAL_SYMBOL
            2 -> base = BASE_HIFEN_SYMBOL
            3 -> base = SESSION_BLOCK
        }
        for (i in 0..configModel.blockLength) {
            newBase += if (i == 0 || i == configModel.blockLength)
                HASHTAG
            else base
        }
        return newBase
    }

    internal fun processBlankLine(): String {
        return HASHTAG + BREAK_LINE
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

    private fun processText(text: String): String {
        /**
         * Not implemented yet!!!
         */
        return text
    }

    internal fun processFinalText(text: String): String {
        val limit = configModel.blockLength
        val textLength = text.length
        val logPrefix = HASHTAG + BLANK_SPACE
        return if (textLength <= limit) logPrefix + text
        else {
            val subTexts = text.split("""(?<=\G.{$limit})""".toRegex()).toTypedArray()
            var newText = ""
            val subTextsLength = subTexts.size
            for (i in 0 until subTextsLength) {
                newText += if (i < subTextsLength - 1) {
                    """
     ${subTexts[i]}

     """.trimIndent()
                } else logPrefix + subTexts[i]
            }
            return newText
        }
    }

    private fun processTime(): String {
        val timeFinal = Calendar.getInstance()
        return run {
            val diff = timeFinal.time.time - configModel.timeStart
            val diffHour = (diff / (60 * 60 * 1000)).toInt()
            val diffMin = (diff / (60 * 1000)).toInt()
            val diffSec = (diff / 1000).toInt()
            val diffMil = (diffSec / 1000).toInt()
            var hours = ""
            var mins = ""
            var secs = ""
            var milS = ""
            if (diffHour in 0..9) hours += "0$diffHour" else hours += diffHour
            if (diffMin in 0..9) mins += "0$diffMin" else mins += diffMin
            if (diffSec in 0..9) secs += "0$diffSec" else secs += diffSec
            if (diffMil in 0..9) milS += "0$diffMil" else milS += diffMil
            buildTimeBlock(hours, mins, secs, milS)
        }
    }

    private fun processLimit(text: String, maxLimit: Int): Int {
        val textSize = text.length
        return if (maxLimit < textSize)
            textSize
//        else if (){
//            maxLimit
//        }
        else {
            maxLimit
        }
    }
}