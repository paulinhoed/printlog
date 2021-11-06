package com.paulo.android.printloglibrary

import com.paulo.android.printloglibrary.core.MainProcess
import com.paulo.android.printloglibrary.di.printModules
import com.paulo.android.printloglibrary.extensions.normalizeMaxLength
import com.paulo.android.printloglibrary.factory.PrintLogFactory
import com.paulo.android.printloglibrary.factory.PrintLogFactoryImpl
import com.paulo.android.printloglibrary.model.PrintLogConfigModel
import com.paulo.android.printloglibrary.utils.PrinterUtils
import com.paulo.android.printloglibrary.utils.PrinterUtilsImpl
import org.koin.androidx.compose.inject
import org.koin.core.context.GlobalContext.startKoin
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
class PrintLogOld2(
    private val toPrint: Boolean,
    private val title: String,
    lengthBlock: Int = 100,
) : PrintLogFactory {
    private var timeStart: Long = 0
    private var maxLengthBlock: Int = lengthBlock
    private lateinit var configModel: PrintLogConfigModel

    private lateinit var gear: MainProcess


//    private val gear: MainProcess by inject(MainProcess::class.java)
//
    private var printerUtils: PrinterUtils
//    private val printerUtils: PrinterUtils by inject(PrinterUtils::class.java)
//
    private val printLogFactory: PrintLogFactory
//    private val printLogFactory: PrintLogFactory by inject(PrintLogFactory::class.java)
//
////    private var gear: MainProcess
//    private val gear: MainProcess by inject(MainProcess::class.java)

    companion object {
        var index: Int = 0
    }

    init {
        startKoin {
            printModules
        }
        setup(lengthBlock)
//        index++
//        this.maxLengthBlock = normalizeMaxLength(lengthBlock)
//        this.timeStart = Calendar.getInstance().timeInMillis

        configModel = PrintLogConfigModel(toPrint, title, index, maxLengthBlock, timeStart)

        gear = MainProcess(configModel)


//        printerUtils = PrinterUtils(configModel, gear)
        printerUtils = PrinterUtilsImpl(configModel, gear)

//        printLogFactory = PrintLogFactory(printerUtils)
        printLogFactory = PrintLogFactoryImpl(printerUtils)
    }

    private fun setup(lengthBlock: Int) {
        index++
        timeStart = Calendar.getInstance().timeInMillis
        maxLengthBlock = normalizeMaxLength(lengthBlock)
        configModel = PrintLogConfigModel(toPrint, title, index, maxLengthBlock, timeStart)

        //
//        printerUtils
//        printerUtils = PrinterUtils(configModel = configModel, gear = gear)
//        printLogFactory = PrintLogFactory(printerUtils)
//        printLogFactory = PrintLogFactory(printerUtils)
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

fun main() {
    startKoin {
        modules(printModules)
    }
}
