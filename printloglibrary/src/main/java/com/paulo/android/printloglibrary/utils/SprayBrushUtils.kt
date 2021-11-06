package com.paulo.android.printloglibrary.utils

import com.paulo.android.printloglibrary.constants.PrintLogConstants.FINISH_CHAPTER
import com.paulo.android.printloglibrary.constants.PrintLogConstants.INIT_CHAPTER
import com.paulo.android.printloglibrary.core.MainProcess

class SprayBrushUtils(
    private val gear: MainProcess?
    ) {

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

    fun beginChapter(text: String?) {
        val chapterText = INIT_CHAPTER + text
        println(gear?.processLineBlock(2))
        println(gear?.processSessionBlock(chapterText))
        println(gear?.processLineBlock(2))
    }

    fun endChapter(text: String?) {
        val chapterText = text + FINISH_CHAPTER
        print(gear?.processBlankLine())
        println(gear?.processLineBlock(2))
        println(gear?.processSessionBlock(chapterText))
        println(gear?.processLineBlock(2))
        print(gear?.processBlankLine())
    }

    fun divider() {
        println(gear?.processLineBlock(1))
    }

    fun separator() {
        println(gear?.processLineBlock(2))
    }

    fun session() {
        print(gear?.processBlankLine())
        println(gear?.processSessionBlock())
        print(gear?.processBlankLine())
    }

    fun blankSpace() {
        print(gear?.processBlankLine())
    }
}