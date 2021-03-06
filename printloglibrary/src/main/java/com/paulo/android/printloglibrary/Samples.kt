package com.paulo.android.printloglibrary

import org.koin.java.KoinJavaComponent.inject
import java.lang.StringBuilder

open class Samples {
    private var list: ArrayList<String> = ArrayList()
    private var map: HashMap<String, Any> = HashMap()

    private val printLogSample: PrintLog by inject(PrintLog::class.java)
    private val printLogHelp: PrintLog by inject(PrintLog::class.java)

    fun sample1() {
        printLogSample(true, "SAMPLE - p.help() blueprint")
        printLogSample.header()
        val builder = StringBuilder()
        builder.appendLine("# val p = PrintLog(true, 'HELP')")
        builder.appendLine("# p.header()")
        builder.appendLine("# p.separator()")
        builder.appendLine("# p.log(' {{::SETUP::}} ')")
        builder.appendLine("# p.separator()")
        builder.appendLine("# p.log('val p = PrintLog(print, title)')")
        builder.appendLine("# p.log('[print] -> print or not the entire block')")
        builder.appendLine("# p.log('[title] -> method or class name')")
        builder.appendLine("# p.separator()")
        builder.appendLine("# p.log('val p = PrintLog(print, title, maxLengthBlock)')")
        builder.appendLine("# p.log('[maxLengthBlock] -> block size. Default = 100')")
        builder.appendLine("# p.breakLine()")
        builder.appendLine("# p.divider()")
        builder.appendLine("# p.log(' {{::HEADER::}} ')")
        builder.appendLine("# p.separator()")
        builder.appendLine("# p.log('p.header() is used to create the header block')")
        builder.appendLine("# p.breakLine()")
        builder.appendLine("# p.divider()")
        builder.appendLine("# p.log(' {{::FOOTER::}} ')")
        builder.appendLine("# p.separator()")
        builder.appendLine("# p.log('p.footer() is used to create the footer block')")
        builder.appendLine("# p.breakLine()")
        builder.appendLine("# p.divider()")
        builder.appendLine("# p.log(' {{::LOGGERS::}} ')")
        builder.appendLine("# p.separator()")
        builder.appendLine("# p.log('There are 3 types of loggers:')")
        builder.appendLine("# p.log('p.log(String)')")
        builder.appendLine("# p.log('p.log(String,Any)')")
        builder.appendLine("# p.log('p.log(Any)')")
        builder.appendLine("# p.breakLine()")
        builder.appendLine("# p.divider()")
        builder.appendLine("# p.log(' {{::EXTRAS::}} ')")
        builder.appendLine("# p.separator()")
        builder.appendLine("# p.log('p.divider(), e.g.: #==========#')")
        builder.appendLine("# p.log('p.separator(), e.g.: #----------#')")
        builder.appendLine("# p.log('p.breakLine(), just break a new line')")
        builder.appendLine("# p.breakLine()")
        builder.appendLine("# p.divider()")
        builder.appendLine("# p.log(' {{::EXAMPLES::}} ')")
        builder.appendLine("# p.separator()")
        builder.appendLine("# p.log('NULL value:')")
        builder.appendLine("# p.log('p.log(value, null) preview:')")
        builder.appendLine("# p.log('value',null)")
        builder.appendLine("# p.breakLine()")
        builder.appendLine("# p.separator()")
        builder.appendLine("# p.log('EMPTY value:')")
        builder.appendLine("# p.log('p.log(value, '') preview:')")
        builder.appendLine("# p.log('value','')")
        builder.appendLine("# p.breakLine()")
        builder.appendLine("# p.separator()")
        builder.appendLine("# p.log('ArrayList<String> with 4 items:')")
        builder.appendLine("# p.log('p.log(list) preview:')")
        builder.appendLine("# p.breakLine()")
        builder.appendLine("# p.log('list',getPopulatedList())")
        builder.appendLine("# p.breakLine()")
        builder.appendLine("# p.separator()")
        builder.appendLine("# p.log('HashMap<String, Any> with 3 keyValues pairs:')")
        builder.appendLine("# p.log('p.log(map) preview:')")
        builder.appendLine("# p.breakLine()")
        builder.appendLine("# p.log('map',getPopulatedMap())")
        builder.appendLine("# p.footer()")
        println(builder.trim())
        printLogSample.footer()
    }

    fun help() {
        printLogHelp(true, "SAMPLE - p.help() blueprint")
        printLogHelp.header()
        printLogHelp.breakLine()
        println("# This sample explains how to use the PrintLog structure.")
        println("# How to assemble the header and footer blocks and how to use the loggers.")
        println("# If you want to see how the help structure was assembled, call p.sample1()")
        printLogHelp.breakLine()
        printLogHelp.separator()
        printLogHelp.log(" {{::SETUP::}} ")
        printLogHelp.separator()
        printLogHelp.log("val p = PrintLog(print, title)")
        printLogHelp.log("[print] -> print or not the entire block")
        printLogHelp.log("[title] -> method or class name")
        printLogHelp.separator()
        printLogHelp.log("val p = PrintLog(print, title, maxLengthBlock)")
        printLogHelp.log("[maxLengthBlock] -> block size. Default = 100")
        printLogHelp.breakLine()
        printLogHelp.divider()
        printLogHelp.log(" {{::HEADER::}} ")
        printLogHelp.separator()
        printLogHelp.log("p.header() is used to create the header block")
        printLogHelp.breakLine()
        printLogHelp.divider()
        printLogHelp.log(" {{::FOOTER::}} ")
        printLogHelp.separator()
        printLogHelp.log("p.footer() is used to create the footer block")
        printLogHelp.breakLine()
        printLogHelp.divider()
        printLogHelp.log(" {{::LOGGERS::}} ")
        printLogHelp.separator()
        printLogHelp.log("There are 3 types of loggers:")
        printLogHelp.log("p.log(String)")
        printLogHelp.log("p.log(String,Any)")
        printLogHelp.log("p.log(Any)")
        printLogHelp.breakLine()
        printLogHelp.divider()
        printLogHelp.log(" {{::EXTRAS::}} ")
        printLogHelp.separator()
        printLogHelp.log("p.divider(), e.g.: #==========#")
        printLogHelp.log("p.separator(), e.g.: #----------#")
        printLogHelp.log("p.breakLine(), just break a new line")
        printLogHelp.breakLine()
        printLogHelp.divider()
        printLogHelp.log(" {{::EXAMPLES::}} ")
        printLogHelp.separator()
        printLogHelp.log("NULL value:")
        printLogHelp.log("p.log(value, null) preview:")
        printLogHelp.log("value",null)
        printLogHelp.breakLine()
        printLogHelp.separator()
        printLogHelp.log("EMPTY value:")
        printLogHelp.log("p.log(value, '') preview:")
        printLogHelp.log("value","")
        printLogHelp.breakLine()
        printLogHelp.separator()
        printLogHelp.log("ArrayList<String> with 4 items:")
        printLogHelp.log("p.log(list) preview:")
        printLogHelp.breakLine()
        printLogHelp.log("list",getPopulatedList())
        printLogHelp.breakLine()
        printLogHelp.separator()
        printLogHelp.log("HashMap<String, Any> with 3 keyValues pairs:")
        printLogHelp.log("p.log(map) preview:")
        printLogHelp.breakLine()
        printLogHelp.log("map",getPopulatedMap())
        printLogHelp.footer()
    }

    private fun getPopulatedList(): ArrayList<String> {
        list.add("First item of the list")
        list.add("Second item of the list")
        list.add("Third item of the list")
        list.add("etc")
        return list
    }

    private fun getPopulatedMap(): HashMap<String, Any> {
        map["key_1"] = 10
        map["key_2"] = "s02"
        map["key_3"] = 0.1f
        return map
    }
}