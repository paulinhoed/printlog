package com.paulo.android.printloglibrary

import com.paulo.android.printloglibrary.core.MainProcess
import com.paulo.android.printloglibrary.extensions.normalizeMaxLength
import com.paulo.android.printloglibrary.factory.PrintLogFactory
import com.paulo.android.printloglibrary.factory.PrintLogFactoryImpl
import com.paulo.android.printloglibrary.model.PrintLogConfigModel
import com.paulo.android.printloglibrary.utils.PrinterUtils
import com.paulo.android.printloglibrary.utils.PrinterUtilsImpl
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
class PrintLog: PrintLogFactory {
    private var timeStart: Long = 0
    private var maxLengthBlock: Int = 0
    private lateinit var configModel: PrintLogConfigModel

    private lateinit var gear: MainProcess
    private lateinit var printerUtils: PrinterUtils
    private lateinit var printLogFactory: PrintLogFactory

    companion object {
        var index: Int = 0
    }

    operator fun invoke(
        toPrint: Boolean,
        title: String,
        lengthBlock: Int = 100
    ) {
        index++
        timeStart = Calendar.getInstance().timeInMillis
        maxLengthBlock = normalizeMaxLength(lengthBlock)
        configModel = PrintLogConfigModel(toPrint, title, index, maxLengthBlock, timeStart)

        setup(toPrint, title)
    }

    private fun setup(toPrint: Boolean, title: String) {
        configModel = PrintLogConfigModel(toPrint, title, index, maxLengthBlock, timeStart)
        gear = MainProcess(configModel)
        printerUtils = PrinterUtilsImpl(configModel, gear)
        printLogFactory = PrintLogFactoryImpl(printerUtils)
    }

    override fun breakLine() {
        printLogFactory.breakLine()
    }

    override fun divider() {
        printLogFactory.divider()
    }

    override fun footer() {
        printLogFactory.footer()
    }

    override fun beginChapter(index: Int, text: String) {
        printLogFactory.beginChapter(index, text)
    }

    override fun endChapter(index: Int) {
        printLogFactory.endChapter(index)
    }

    override fun header() {
        printLogFactory.header()
    }

    override fun separator() {
        printLogFactory.separator()
    }

    override fun session() {
        printLogFactory.session()
    }

    override fun log(value: Any?) {
        printLogFactory.log(value)
    }

    override fun log(message: String) {
        printLogFactory.log(message)
    }

    override fun log(message: String, value: Any?) {
        printLogFactory.log(message, value)
    }

    override fun help() {
        printLogFactory.help()
    }

    override fun sample1() {
        printLogFactory.sample1()
    }

}
