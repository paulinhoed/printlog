package com.paulo.android.printloglibrary.model

data class GenericObjectModel(
    val primitive: Any?,
    val list: List<Any?>,
    val map: HashMap<String,Any>
)