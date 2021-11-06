package com.paulo.android.printloglibrary.model

data class PrintLogConfigModel(
    val print: Boolean,
    val title: String,
    val index: Int,
    val blockLength: Int,
    val timeStart: Long)