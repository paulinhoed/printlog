package com.paulo.android.printloglibrary.utils

import com.paulo.android.printloglibrary.core.ProcessCore

class SprayBrush(processCore: ProcessCore?) {

    private var gear: ProcessCore? = processCore

    fun header(text: String) {
        println(gear?.processLineBlock(1))
        println(gear?.processBlock(text, 0))
        println(gear?.processLineBlock(1))
    }

    fun footer(text: String) {
        print(gear?.processBlankLine())
        println(gear?.processLineBlock(1))
        println(gear?.processBlock(text, 1))
        println(gear?.processLineBlock(1))
        print(gear?.processBlankLine())
    }

    fun divider() {
        println(gear?.processLineBlock(1))
    }

    fun separator() {
        println(gear?.processLineBlock(2))
    }

    fun blankSpace() {
        print(gear?.processBlankLine())
    }
}