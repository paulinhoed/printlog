package com.paulo.android.printloglibrary.core
//
//import com.paulo.android.printloglibrary.constants.PrintLogConstants.BASE_EQUAL_SYMBOL
//import com.paulo.android.printloglibrary.constants.PrintLogConstants.BASE_HIFEN_SYMBOL
//import com.paulo.android.printloglibrary.constants.PrintLogConstants.BASE_END
//import com.paulo.android.printloglibrary.constants.PrintLogConstants.BASE_START
//import com.paulo.android.printloglibrary.constants.PrintLogConstants.BLANK_SPACE
//import com.paulo.android.printloglibrary.constants.PrintLogConstants.BREAK_LINE
//import com.paulo.android.printloglibrary.constants.PrintLogConstants.EMPTY
//import com.paulo.android.printloglibrary.constants.PrintLogConstants.END
//import com.paulo.android.printloglibrary.constants.PrintLogConstants.HASHTAG
//import com.paulo.android.printloglibrary.constants.PrintLogConstants.INIT
//import com.paulo.android.printloglibrary.constants.PrintLogConstants.NULL
//import com.paulo.android.printloglibrary.constants.PrintLogConstants.SESSION_BLOCK
//import com.paulo.android.printloglibrary.extensions.*
//import com.paulo.android.printloglibrary.model.PrintLogConfig
//import java.util.*
//import kotlin.collections.ArrayList
//import kotlin.collections.HashMap
//import kotlin.reflect.full.memberProperties
//
//class MainProcessOld(
//    private val config: PrintLogConfig
//) {
//
//    private val processValue = ProcessValue(config)
//
//    internal fun processBlock(text: String, option: Int): Any {
//        val maxLength = processLimit(text, config.blockLength)
//        var blockBase = ""
//        when (option) {
//            0 -> blockBase = buildHeaderAndFooterBlock(INIT, processText(text), config.index)
//            1 -> {
//                blockBase = buildHeaderAndFooterBlock(END, processText(text), config.index)
//                blockBase = buildElapsedTime(blockBase, processTime())
//            }
//        }
//        val newBase = buildNewBase(blockBase, maxLength)
//        val bases: Map<String, String> = processBase(newBase)
//        return HASHTAG + bases[BASE_START] + blockBase + bases[BASE_END] + HASHTAG
//    }
//
//    internal fun processSessionBlock(text: String? = null): Any {
//        val maxLength: Int = when (text) {
//            null -> processLimit(SESSION_BLOCK, config.blockLength)
//            else -> processLimit(text, config.blockLength)
//        }
//        val blockBase: String = when (text) {
//            null -> buildSessionBlock(null).toString()
//            else -> buildSessionBlock(text).toString()
//        }
//        val newBase = buildNewBase(blockBase, maxLength)
//        val bases: Map<String, String> = processBase(newBase)
//        return HASHTAG + bases[BASE_START] + blockBase + bases[BASE_END] + HASHTAG
//    }
//
//    internal fun processLineBlock(option: Int): Any {
//        var base = ""
//        var newBase = ""
//        when (option) {
//            1 -> base = BASE_EQUAL_SYMBOL
//            2 -> base = BASE_HIFEN_SYMBOL
//            3 -> base = SESSION_BLOCK
//        }
//        for (i in 0..config.blockLength) {
//            newBase += if (i == 0 || i == config.blockLength)
//                HASHTAG
//            else base
//        }
//        return newBase
//    }
//
//    internal fun processBlankLine(): String {
//        return HASHTAG + BREAK_LINE
//    }
//
//    private fun processBase(base: String): Map<String, String> {
//        val baseLength = base.length
//        var result = kotlin.math.ceil((baseLength / 2).toDouble()).toInt()
//        if (result == 0) result++
//        val startBase = base.substring(0, result - 1)
//        val endBase = base.substring(result, baseLength)
//        val bases: MutableMap<String, String> = HashMap()
//        bases[BASE_START] = startBase
//        bases[BASE_END] = endBase
//        return bases
//    }
//
//    private fun processText(text: String): String {
//        /**
//         * Not implemented yet!!!
//         */
//        return text
//    }
//
//    internal fun processFinalText(text: String): String {
//        val limit = config.blockLength
//        val textLength = text.length
//        val logPrefix = HASHTAG + BLANK_SPACE
//        return if (textLength <= limit) logPrefix + text
//        else {
//            val subTexts = text.split("""(?<=\G.{$limit})""".toRegex()).toTypedArray()
//            var newText = ""
//            val subTextsLength = subTexts.size
//            for (i in 0 until subTextsLength) {
//                newText += if (i < subTextsLength - 1) {
//                    """
//     ${subTexts[i]}
//
//     """.trimIndent()
//                } else logPrefix + subTexts[i]
//            }
//            return newText
//        }
//    }
//
//    fun getProcessValue(value: Any?): String {
//        return processValue.execute(value)
//    }
//
//    fun processValueByTheBuilder(value: Any?): Any {
////        val objectName = value?.javaClass?.simpleName
////        val objects + BREAK_LINE
////        var builder = StringBuilder()
////        builder.append(BREAK_LINE)
////        builder.append(BREAK_LINE)
////        BREAK_LINE
//        return processValue.execute(value)
//    }
//
////    internal fun ProcessValue.execute(value: Any?): Any {
////
////        val maxLength = config.blockLength - 5
////
////        val thisValue: Any = when (value) {
////            null -> NULL
////            else -> {
////                when (value.javaClass) {
////
////                    String::class.java -> {
////                        if ((value as String).isEmpty()) EMPTY
////                        else {
////                            var builder: StringBuilder
////                            builder = buildHeaderStructureList(maxLength)
////                            for ((index, v) in value.withIndex()) {
////                                builder = buildLists(v, builder, index)
////                            }
////                            builder = buildFooterStructureList(maxLength, builder)
////                            return builder.toString()
////                        }
////                    }
////
////                    Array<Any?>::class.java -> {
////                        val list = value as Array<*>
////                        if (list.isEmpty()) EMPTY
////                        else {
////                            var builder = StringBuilder()
////                            builder.append(BREAK_LINE)
////                            builder = buildHeaderStructureList(maxLength)
////                            for ((index, v) in value.withIndex()) {
////                                builder = buildLists(v, builder, index)
////                            }
////                            builder = buildFooterStructureList(maxLength, builder)
////                            return builder.toString()
////                        }
////                    }
////
////                    ArrayList::class.java -> {
////                        if ((value as ArrayList<*>).isEmpty()) EMPTY
////                        else {
////                            var builder = StringBuilder()
////                            builder.append(BREAK_LINE)
////                            builder = buildSessionStructureList(maxLength, null)
////                            for ((index, v) in value.withIndex()) {
////                                builder = buildNewLists(v, builder, index)
////                            }
////                            builder = buildSessionStructureList(maxLength, builder)
////                            return builder.toString()
////                        }
////                    }
////
////                    HashMap::class.java -> {
////                        if ((value as Map<*, *>).isEmpty()) EMPTY
////                        else {
////                            var builder = StringBuilder()
////                            builder.append(BREAK_LINE)
////                            builder = buildSessionStructureList(maxLength, null)
////                            for ((_key, _value) in value as HashMap<*, *>) {
////                                val vv = processValueByTheBuilder(_value)
////                                val newVal = "$_key = $vv"
////                                builder = buildNewLists(newVal, builder, -1)
////                            }
////                            builder = buildSessionStructureList(maxLength, builder)
////                            return builder.toString()
////                        }
////                    }
////
////                    Integer::class.java -> return value
////
////                    Float::class.java -> return value
////
////                    Double::class.java -> return value
////
////                    Number::class.java -> return value
//////                    List<Any?>::class.java -> {
//////
//////                    } nao funfa
//////                    Array<Any?>::class.java -> {
//////                        val list = value as Array<*>
//////                        if (list.isEmpty()) EMPTY
//////                        else {
//////                            var builder = buildHeaderStructureList(maxLength)
//////                            for ((index, v) in value.withIndex()) {
//////                                builder = buildLists(v, builder, index)
//////                            }
//////                            builder = buildFooterStructureList(maxLength, builder)
//////                            return builder.toString()
//////                        }
//////                    } noa funfa
////                    else -> {
////                        val isPrimitive = value.javaClass.isPrimitive
////                        println("primitive:$isPrimitive")
////                        if (isPrimitive) return value
////                        else {
////                            val memberProperties = javaClass.kotlin.memberProperties
////                            println("memberProperties:$memberProperties")
////                            println("+++++++==================================")
////                            for (prop in memberProperties) {
//////                    println("${prop.name} = ${prop.get(value)}")
////                                println("+++++++----------------------------------")
////                                println("PropName: ${prop.name}")
////                                println("PropTypeParameters: ${prop.returnType}")
////                                val nameClass = prop.returnType.toString().split(".")
////                                println("nameClass: ${nameClass[nameClass.lastIndex]}")
////                                //
////                                val objectName = value.javaClass.simpleName
////                                val splittedParts = (value.toString()).split("(")[1]
////                                val parameters = (splittedParts.split(")")[0]).split(", ")
////
////                                val thisValue =
////                                    processGenericObject(objectName, parameters, maxLength)
////                                //
////                                println(thisValue)
////                                println("+++++++----------------------------------")
////                            }
////
////
////
////                            return value
//////                    val res = processValue2(prop.get(value))
//////                    println(res)
//////                    if (value.javaClass == Array<String>::class.java) {
//////                        val list = value as Array<*>
//////                        if (list.isEmpty()) valueCopy = EMPTY else {
//////                            var builder = buildHeaderStructureList(maxLength)
//////                            for ((index, v) in value.withIndex()) {
//////                                builder = buildLists(v, builder, index)
//////                            }
//////                            builder = buildFooterStructureList(maxLength, builder)
//////                            valueCopy = builder.toString()
//////                        }
////////                    }
//////                            println("+++++++----------------------------------")
//////
//////                            return value
////                        }
////                    }
////                }
////            }
////        }
////        return thisValue
////    }
//
//    internal fun processValue2(value: Any?): Any? {
//        val maxLength = config.blockLength - 5
//        var thisValue = value
//
//        if (value == null)
//            thisValue = NULL
//        else {
//            if (value.javaClass == String::class.java) {
//                if ((value as String).isEmpty()) thisValue = EMPTY
//            } else if (value.javaClass == Array<String>::class.java) {
//                val list = value as Array<*>
//                if (list.isEmpty()) thisValue = EMPTY else {
//                    var builder = buildHeaderStructureList(maxLength)
//                    for ((index, v) in value.withIndex()) {
//                        builder = buildLists(v, builder, index)
//                    }
//                    builder = buildFooterStructureList(maxLength, builder)
//                    thisValue = builder.toString()
//                }
//            } else if (value.javaClass == Array<Long>::class.java) {
//                val list = value as Array<*>
//                if (list.isEmpty()) thisValue = EMPTY else {
//                    var builder = buildHeaderStructureList(maxLength)
//                    for ((index, v) in value.withIndex()) {
//                        builder = buildLists(v, builder, index)
//                    }
//                    builder = buildFooterStructureList(maxLength, builder)
//                    thisValue = builder.toString()
//                }
//            } else if (value.javaClass == ArrayList::class.java) {
//                if ((value as ArrayList<*>).isEmpty()) thisValue = EMPTY else {
//                    var builder = buildHeaderStructureList(maxLength)
//                    for ((index, v) in value.withIndex()) {
//                        builder = buildLists(v, builder, index)
//                    }
//                    builder = buildFooterStructureList(maxLength, builder)
//                    thisValue = builder.toString()
//                }
//            } else if (value.javaClass == HashMap::class.java) {
//                if ((value as Map<*, *>).isEmpty()) thisValue = EMPTY else {
//                    var builder = buildHeaderStructureList(maxLength)
//                    for ((_key, _value) in value as HashMap<*, *>) {
//                        val newVal = "$_key = $_value"
//                        builder = buildLists(newVal, builder, -1)
//                    }
//                    builder = buildFooterStructureList(maxLength, builder)
//                    thisValue = builder.toString()
//                }
//            } else {
////                val objectName = value.javaClass.simpleName
////                val splittedParts = (value.toString()).split("(")[1]
////                val parameters = (splittedParts.split(")")[0]).split(", ")
////
////                thisValue = processGenericObject(objectName, parameters, maxLength)
//            }
//        }
//        return thisValue
//    }
//
//    private fun processGenericObject(
//        objectName: String,
//        parameters: List<String>,
//        maxLength: Int
//    ): Any? {
//        var builder = buildHeaderStructureList(maxLength)
//        for ((i, pair) in parameters.withIndex()) {
//            val parameter = pair.split("=")[0]
//            val value = pair.split("=")[1]
//            //
////            processValue.execute(value)
//
////
//            val thisVal = processValue.execute(value)
////            println("#processGenericObject::thisVal: $thisVal")
//
//            //
//            val base = objectName + "\n"  + HASHTAG
//            val newVal = "$base$parameter = $value"
//            builder = buildNewLists(newVal, builder, i)
//        }
//        builder = buildFooterStructureList(maxLength, builder)
//        return builder.toString()
//    }
//
//    private fun processTime(): String {
//        val timeFinal = Calendar.getInstance()
//        return run {
//            val diff = timeFinal.time.time - config.timeStart
//            val diffHour = (diff / (60 * 60 * 1000)).toInt()
//            val diffMin = (diff / (60 * 1000)).toInt()
//            val diffSec = (diff / 1000).toInt()
//            val diffMil = (diffSec / 1000).toInt()
//            var hours = ""
//            var mins = ""
//            var secs = ""
//            var milS = ""
//            if (diffHour in 0..9) hours += "0$diffHour" else hours += diffHour
//            if (diffMin in 0..9) mins += "0$diffMin" else mins += diffMin
//            if (diffSec in 0..9) secs += "0$diffSec" else secs += diffSec
//            if (diffMil in 0..9) milS += "0$diffMil" else milS += diffMil
//            buildTimeBlock(hours, mins, secs, milS)
//        }
//    }
//
//    private fun processLimit(text: String, maxLimit: Int): Int {
//        val textSize = text.length
//        return if (maxLimit < textSize)
//            textSize
////        else if (){
////            maxLimit
////        }
//        else {
//            maxLimit
//        }
//    }
//}