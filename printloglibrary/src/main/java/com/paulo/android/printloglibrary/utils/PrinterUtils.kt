package com.paulo.android.printloglibrary.utils

import com.paulo.android.printloglibrary.constants.PrintLogConstants.BEGIN_CHAPTER
import com.paulo.android.printloglibrary.model.PrintLogConfigModel
import com.paulo.android.printloglibrary.constants.PrintLogConstants.BOTH_MSG_VAL
import com.paulo.android.printloglibrary.constants.PrintLogConstants.DIVIDER
import com.paulo.android.printloglibrary.constants.PrintLogConstants.END_CHAPTER
import com.paulo.android.printloglibrary.constants.PrintLogConstants.FOOTER
import com.paulo.android.printloglibrary.constants.PrintLogConstants.HEADER
import com.paulo.android.printloglibrary.constants.PrintLogConstants.JUMP_LINE
import com.paulo.android.printloglibrary.constants.PrintLogConstants.ONLY_MSG
import com.paulo.android.printloglibrary.constants.PrintLogConstants.ONLY_VAL
import com.paulo.android.printloglibrary.constants.PrintLogConstants.SEPARATOR
import com.paulo.android.printloglibrary.constants.PrintLogConstants.SESSION
import com.paulo.android.printloglibrary.core.MainProcess
import com.paulo.android.printloglibrary.extensions.buildLogText
import com.paulo.android.printloglibrary.extensions.getLine
import com.paulo.android.printloglibrary.extensions.getTime
import com.paulo.android.printloglibrary.extensions.getTitleFormatted

interface PrinterUtils {
    fun drawExtrasBordersAndStuff(option: String)
    fun writeLogs(option: String, message: String, value: Any?)
    fun setSubTitle(index: Int, subTitle: String)
    fun setSubTitleByIndex(index: Int)
}

class PrinterUtilsImpl(
    private val configModel: PrintLogConfigModel,
    private val gear: MainProcess
): PrinterUtils {
    private var sprayBrushUtils: SprayBrushUtils = SprayBrushUtils(gear)

    private val title: String = getTitleFormatted(configModel.title, configModel.blockLength)

    private var subTitle: String? = null

    var subTitleMap: HashMap<Int, String> = hashMapOf()
        private set

    override fun drawExtrasBordersAndStuff(option: String) {
        if (configModel.print) {
            when (option) {
                HEADER -> sprayBrushUtils.header(this.title)
                FOOTER -> sprayBrushUtils.footer(this.title)
                BEGIN_CHAPTER -> sprayBrushUtils.beginChapter(this.subTitle)
                END_CHAPTER -> sprayBrushUtils.endChapter(this.subTitle)
                DIVIDER -> sprayBrushUtils.divider()
                SEPARATOR -> sprayBrushUtils.separator()
                JUMP_LINE -> sprayBrushUtils.blankSpace()
                SESSION -> sprayBrushUtils.session()
            }
        }
    }

    override fun writeLogs(option: String, message: String, value: Any?) {
        if (configModel.print) {
            when (option) {
                ONLY_MSG -> log(message)
                ONLY_VAL -> log(value)
                BOTH_MSG_VAL -> log(message, value)
            }
        }
    }

    private fun log(msg: String) {
        if (configModel.print) {
            val line = getLine()
            val time = getTime()
            val text = buildLogText(time, line, title, msg)
            println(gear.processFinalText(text))
        }
    }

    private fun log(value: Any?) {
        val thisValue: Any?
        if (configModel.print) {
            val line = getLine()
            val time = getTime()
            thisValue = gear.getProcessValue(value)
            val text = buildLogText(time, line, title, thisValue)
            println(gear.processFinalText(text))
        }
    }

    private fun log(msg: String, value: Any?) {
        val thisValue: Any?
        if (configModel.print) {
            val line = getLine()
            val time = getTime()
            thisValue = gear.getProcessValue(value)
            val msgWithValue = "$msg: $thisValue"
            val text = buildLogText(time, line, title, msgWithValue)
            println(gear.processFinalText(text))
        }
    }

    override fun setSubTitle(index: Int, subTitle: String) {
        this.subTitle = subTitle
        this.subTitleMap[index] = subTitle
    }

    override fun setSubTitleByIndex(index: Int) {
        this.subTitle = this.subTitleMap.getValue(index)
    }

}