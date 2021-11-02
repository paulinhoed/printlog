package com.paulo.android.printloglibrary

import com.paulo.android.printloglibrary.constants.PrintLogConstants.BOTH_MSG_VAL
import com.paulo.android.printloglibrary.constants.PrintLogConstants.DIVIDER
import com.paulo.android.printloglibrary.constants.PrintLogConstants.FOOTER
import com.paulo.android.printloglibrary.constants.PrintLogConstants.HEADER
import com.paulo.android.printloglibrary.constants.PrintLogConstants.JUMP_LINE
import com.paulo.android.printloglibrary.constants.PrintLogConstants.NOTHING
import com.paulo.android.printloglibrary.constants.PrintLogConstants.ONLY_MSG
import com.paulo.android.printloglibrary.constants.PrintLogConstants.ONLY_VAL
import com.paulo.android.printloglibrary.constants.PrintLogConstants.SEPARATOR
import com.paulo.android.printloglibrary.extensions.getTime
import com.paulo.android.printloglibrary.input.Input
import com.paulo.android.printloglibrary.interfaces.PrintLogInterface
import org.koin.androidx.compose.inject
import java.util.*


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
 * # LOG [85]-Teste do PrintLog => Somente uma mensagem
 * # LOG [86]-Teste do PrintLog => Mensagem com valor: valor aqui
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
class PrintLog: PrintLogInterface {
    private var print: Boolean
    private var title: String
    private var maxLengthBlock: Int
    private var timeStart: Calendar? = null

    private var input: Input

    companion object {
        var subLevelCounter: Int = 0
    }

    init {
        subLevelCounter++
        timeStart = Calendar.getInstance()
    }

    constructor(print: Boolean, title: String) {
        this.print = print
        this.title = title
        this.maxLengthBlock = 100
        input = Input(print, title, subLevelCounter, timeStart)
    }

    constructor(print: Boolean, title: String, maxLengthBlock: Int) {
        this.print = print
        this.title = title
        this.maxLengthBlock = maxLengthBlock
        input = Input(print, title, subLevelCounter, maxLengthBlock, timeStart)
    }

    override fun header() {
        input.drawExtras(HEADER)
    }

    override fun footer() {
        input.drawExtras(FOOTER)
    }

    override fun divider() {
        input.drawExtras(DIVIDER)
    }

    override fun separator() {
        input.drawExtras(SEPARATOR)
    }

    override fun breakLine() {
        input.drawExtras(JUMP_LINE)
    }

    override fun log(value: Any?) {
        input.printLogs(ONLY_VAL, NOTHING, value)
    }

    override fun log(message: String) {
        input.printLogs(ONLY_MSG, message, null)
    }

    override fun log(message: String, value: Any?) {
        input.printLogs(BOTH_MSG_VAL, message, value)
    }

    override fun help() {
        Samples().help()
    }

    override fun sample1() {
        Samples().sample1()
    }

}
