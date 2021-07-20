package com.paulo.android.printloglibrary

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.ceil


/**
 * A classe PrintLog foi criada para ser uma alternativa ao modo DEBUG, além de
 * criar um padrão de logs mais organizado e que mostre a line de onde foi
 * chamada a ação. Pode-se instanciar em vários pontos, facilitando a separação
 * das mensagens de acordo com o assunto dos métodos do código. Para desabilitar
 * a visualização basta passar o parâmetro print para 'false'.
 *
 * Exemplo de código: PrintLog p = new PrintLog(true,"Teste do PrintLog");
 * p.test();
 *
 * Resultado:
 * ===================================================================================================
 * #///////////////////////////// INIT [Teste do PrintLog] ///////////////////////////////#
 * ===================================================================================================
 * # LOG [85]-Teste do PrintLog => Somente uma mensagem # LOG [86]-Teste do PrintLog => Mensagem com valor: valor aqui
 * ---------------------------------------------------------------------------------------------------
 * # LOG [88]-Teste do PrintLog => Somente outra mensagem depois das separações e antes de fechar o b
 * loco
 * ===================================================================================================
 *
 * ===================================================================================================
 * #/////////////////////////////// END [Teste do PrintLog] ////////////////////////////////#
 * ===================================================================================================
 *
 *
 */
class PrintLogOLD {
    private var print: Boolean
    private var title: String
    private var maxLengthBlock: Int
    private var timeStart: Calendar? = null

    companion object {
        var subLevelCounter: Int = 0
    }

    init {
        subLevelCounter++
    }

    constructor(print: Boolean, title: String) {
        this.print = print
        this.title = title
        this.maxLengthBlock = 100
    }

    constructor(print: Boolean, title: String, maxLengthBlock: Int) {
        this.print = print
        this.title = title
        this.maxLengthBlock = maxLengthBlock
    }

    fun start() {
        log()
    }

    fun end() {
        log(99)
    }

    fun divider() {
        log(1)
    }

    private fun log(option: Int = -1) {
        if (print) {
            when (option) {
                -1 -> {
                    println(processLineBlock(1))
                    println(processBlock(title, 0))
                    println(processLineBlock(1))
                }
                0 -> println(processLineBlock(2))
                1 -> println("""
    ${processLineBlock(2)}

    """.trimIndent())
                99 -> {
                    brk()
                    println(processLineBlock(1))
                    println(processBlock(title, 1))
                    println("""
    ${processLineBlock(1)}


    """.trimIndent())
                }
                else -> {
                }
            }
        }
    }

    fun log(msg: String) {
        if (print) {
            val text = """# LOG [${time()}][line ${line()}]
# $title => $msg"""
            println(processText(text))
        }
    }

    fun log(value: Any?) {
        val thisValue: Any?
        if (print) {
            thisValue = processValue(value)
            val text = """# LOG [${time()}][line ${line()}]
# $title => $thisValue"""
            println(processText(text))
        }
    }

    fun log(msg: String, value: Any?) {
        val thisValue: Any?
        if (print) {
            thisValue = processValue(value)
            val text = """# LOG [${time()}][line ${line()}]
# $title => $msg: $thisValue"""
            println(processText(text))
        }
    }

    private fun tt(): String {
        val timeFinal = Calendar.getInstance()
        return if (timeStart != null) {
            val diff = timeFinal.time.time - timeStart!!.time.time
            val diffDays = (diff / (24 * 60 * 60 * 1000)).toInt()
            val diffHour = (diff / (60 * 60 * 1000)).toInt()
            val diffMin = (diff / (60 * 1000)).toInt()
            val diffSec = (diff / 1000).toInt()
            var hours = ""
            var mins = ""
            var secs = ""
            if (diffHour in 0..9) hours += "0$diffHour" else hours += diffHour
            if (diffMin in 0..9) mins += "0$diffMin" else mins += diffMin
            if (diffSec in 0..9) secs += "0$diffSec" else secs += diffSec
            "Time:[" + diffDays + " days & " + hours + "h:" + mins + "m:" + secs + "ms]"
        } else ""
    }

    @JvmOverloads
    fun brk(option: Int = 1) {
        if (print) {
            val BREAK = "\n"
            var NEW_BREAK = ""
            if (option <= 0)
                if (option > 1) {
                    for (i in 1 until option) NEW_BREAK += BREAK
                }
            println(NEW_BREAK)
        }
    }

    fun sample1() {
        log(-1)
        log("Somente uma mensagem")
        log("Mensagem com valor", "valor aqui")
        log(0)
        log("Somente outra mensagem depois das separações e antes de fechar o bloco")
        log(1)
        log(99)
    }

    /**
     *
     *
     */
    private fun line(): Int {
        return Throwable().stackTrace[2].lineNumber
    }

    private fun processValue(value: Any?): Any? {
        var thisValue = value
        val thisClass = value?.javaClass
        val thisClassName = value?.javaClass?.simpleName
//        log("||===========================||")
//        log("||thisClassName-> $thisClassName")
//        log("||===========================||")

        return thisValue

//        if (value == null)
//            thisValue = SYB.NULO.symbol
//        else {
//            if (value.javaClass == String::class.java) {
//                if ((value as String).isEmpty()) thisValue = SYB.EMPTY.symbol
//            } else if (value.javaClass == Array<String>::class.java) {
//                val list = value as Array<*>
//                if (list.isEmpty()) thisValue = SYB.EMPTY.symbol else {
//                    var newVal = ""
//                    for (v in list) {
//                        newVal += SYB.BREAKLINE.symbol + v + SYB.SEMICOLON.symbol
//                    }
//                    thisValue = newVal
//                }
//            } else if (value.javaClass == Array<Long>::class.java) {
//                val list = value as Array<*>
//                if (list.isEmpty()) thisValue = SYB.EMPTY.symbol else {
//                    var newVal = ""
//                    for (v in list) {
//                        newVal += SYB.BREAKLINE.symbol + v + SYB.SEMICOLON.symbol
//                    }
//                    thisValue = newVal
//                }
//            } else if (value.javaClass == ArrayList::class.java) {
//                if ((value as ArrayList<*>).isEmpty()) thisValue = SYB.EMPTY.symbol else {
//                    var newVal = ""
//                    for (v in value) {
//                        newVal += SYB.BREAKLINE.symbol + v + SYB.SEMICOLON.symbol
//                    }
//                    thisValue = newVal
//                }
//            } else if (value.javaClass == HashMap::class.java) {
//                if ((value as Map<*, *>).isEmpty()) thisValue = SYB.EMPTY.symbol else {
//                    var newVal = ""
//                    for ((key, _value) in value as HashMap<*, *>) {
//                        val brk = SYB.BREAKLINE.symbol
//                        val smc = SYB.SEMICOLON.symbol
//                        newVal += "$brk$key = $_value$smc"
//                    }
//                    thisValue = newVal
//                }
//            }
//        }
//        return thisValue
    }

    private fun processText(text: String): String {
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
                } else subTexts[i]
            }
            newText
        }
    }

    private fun processBase(base: String): Map<String, String> {
        val baseLength = base.length
        var result = ceil((baseLength / 2).toDouble()).toInt()
        if (result == 0) result++
        val startBase = base.substring(0, result - 1)
        val endBase = base.substring(result, baseLength)
        val bases: MutableMap<String, String> = HashMap()
        bases["start"] = startBase
        bases["end"] = endBase
        return bases
    }

    private fun processBlock(text: String, option: Int): String {
        val base = "|"
        var newBase = ""
        var block = ""
        val hashtag = "#"
        val maxLength = maxLengthBlock
        val limit = maxLengthBlock - 2
        var bases: Map<String, String> = HashMap()
        when (option) {
            0 -> {
                var startBlockBase = " ($subLevelCounter)INIT [$text] "
                var startBlockBaseLength = startBlockBase.length
                if (startBlockBaseLength >= maxLength) {
                    startBlockBase = startBlockBase.substring(0, limit)
                    startBlockBaseLength = startBlockBase.length
                }
                val diffStart = maxLength - startBlockBaseLength
                var i = 0
                while (i < diffStart) {
                    newBase += base
                    i++
                }
                bases = processBase(newBase)
                block = hashtag + bases["start"] + startBlockBase + bases["end"] + hashtag
            }
            1 -> {
                var endBlockBase = " ($subLevelCounter)END [" + text + "]-" + tt()
                var endBlockBaseLength = endBlockBase.length
                if (endBlockBaseLength >= maxLength) {
                    endBlockBase = endBlockBase.substring(0, limit)
                    endBlockBaseLength = endBlockBase.length
                }
                val diffEnd = maxLength - endBlockBaseLength
                var i = 0
                while (i < diffEnd) {
                    newBase += base
                    i++
                }
                bases = processBase(newBase)
                block = hashtag + bases["start"] + endBlockBase + bases["end"] + hashtag + "\n"
            }
        }
        return block
    }

    private fun processLineBlock(option: Int): String {
        var base = ""
        var newBase = ""
        when (option) {
            1 -> base = "="
            2 -> base = "-"
        }
        for (i in 0..maxLengthBlock) newBase += base
        return newBase
    }

    @SuppressLint("SimpleDateFormat")
    private fun time(): String {
        val dateFormat: DateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        val cal = Calendar.getInstance()
        if (timeStart == null) timeStart = cal
        return dateFormat.format(cal.time)
    }
}

//enum class SYB(val symbol: String) {
//    SEMICOLON(";"),
//    BREAKLINE("\n"),
//    NULO("[null]"),
//    EMPTY("[empty]")
//}
