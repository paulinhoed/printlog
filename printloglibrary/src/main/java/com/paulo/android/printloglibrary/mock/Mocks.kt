package com.paulo.android.printloglibrary.mock

import com.paulo.android.printloglibrary.model.GenericObjectModel
import com.paulo.android.printloglibrary.model.PrintLogConfigModel

class Mocks {
   private val s01 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
   private val s02 = "Proin molestie sollicitudin pretium."
   private val s03 = "Nullam ac augue dignissim, sagittis neque sed, vehicula sapien."
   private val s04 = "Praesent at suscipit sem."
   private val s05 =
        "Nunc sagittis, sem nec ultrices luctus, leo dolor condimentum nibh, in placerat libero tortor sit amet orci."
   private val s06 = "Maecenas sit amet suscipit nisl, a volutpat nibh. Aliquam sed sodales libero."
   private val s07 = "Mauris suscipit augue orci, nec fringilla sem euismod in."
   private val s08 = "Sed elementum eros mauris, et porttitor purus laoreet at."
   private val s09 = "Sed semper enim non viverra consectetur."
   private val s10 = "Phasellus a hendrerit ex, vitae ornare dui."
   private val s11 = "Vivamus viverra nulla in orci varius, eu ultrices odio imperdiet."
   private val s12 = "Proin elementum faucibus odio eu faucibus."
   private val s13 = "Sed sodales est vel nunc interdum, quis mollis ligula malesuada."
   private val s14 = "Fusce quam mauris, bibendum ut dapibus quis, sollicitudin ut sapien."
   private val s15 = "Mauris rhoncus nibh vitae iaculis facilisis."
   private val s16 = "Integer vel sem egestas, dictum magna quis, tempus augue."
   private val s17 = "Donec sed interdum elit."
   private val s18 = "Donec dapibus nisl suscipit mauris mattis, non laoreet nulla viverra."
    
    fun config() = PrintLogConfigModel(
        true,
        "ThisIsTheTitle",
        1,
        90,
        0L
    )

    fun config2() = GenericObjectModel(
        null,
        getList(),
        getMap()
    )
    
    fun getList(): ArrayList<String> {
        val list: ArrayList<String> = ArrayList()
        list.add(s01)
        list.add(s02)
        list.add(s03)
        list.add(s04)
        list.add(s05)
        list.add(s06)
        list.add(s07)
        list.add(s08)
        list.add(s09)
        list.add(s10)
        list.add(s11)
        list.add(s12)
        list.add(s13)
        list.add(s14)
        list.add(s15)
        list.add(s16)
        list.add(s17)
        list.add(s18)
        return list
    }

    fun getEmptyList(): ArrayList<String> {
        return ArrayList()
    }
    
    fun getMap(): HashMap<String, Any> {
        val map: HashMap<String, Any> = HashMap()
        map["s01"] = 10
        map["s02"] = getList()
        map["s03"] = getEmptyList()
        return map
    }
}