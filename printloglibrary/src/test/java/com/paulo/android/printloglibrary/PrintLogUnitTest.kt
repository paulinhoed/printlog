package com.paulo.android.printloglibrary

import org.junit.Test

class PrintLogUnitTest {
    val s01 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
    val s02 = "Proin molestie sollicitudin pretium."
    val s03 = "Nullam ac augue dignissim, sagittis neque sed, vehicula sapien."
    val s04 = "Praesent at suscipit sem."
    val s05 =
        "Nunc sagittis, sem nec ultrices luctus, leo dolor condimentum nibh, in placerat libero tortor sit amet orci."
    val s06 = "Maecenas sit amet suscipit nisl, a volutpat nibh. Aliquam sed sodales libero."
    val s07 = "Mauris suscipit augue orci, nec fringilla sem euismod in."
    val s08 = "Sed elementum eros mauris, et porttitor purus laoreet at."
    val s09 = "Sed semper enim non viverra consectetur."
    val s10 = "Phasellus a hendrerit ex, vitae ornare dui."
    val s11 = "Vivamus viverra nulla in orci varius, eu ultrices odio imperdiet."
    val s12 = "Proin elementum faucibus odio eu faucibus."
    val s13 = "Sed sodales est vel nunc interdum, quis mollis ligula malesuada."
    val s14 = "Fusce quam mauris, bibendum ut dapibus quis, sollicitudin ut sapien."
    val s15 = "Mauris rhoncus nibh vitae iaculis facilisis."
    val s16 = "Integer vel sem egestas, dictum magna quis, tempus augue."
    val s17 = "Donec sed interdum elit."
    val s18 = "Donec dapibus nisl suscipit mauris mattis, non laoreet nulla viverra."

    var list: ArrayList<String> = ArrayList()
    var map: HashMap<String, Any> = HashMap()
//    @Test
//    fun `GIVEN this_test MUST be_this_thing WHEN something_is_called`() {
//        list.add(s01)
//        list.add(s02)
//        list.add(s03)
//        list.add(s04)
//        list.add(s05)
//        list.add(s06)
//        list.add(s07)
//        list.add(s08)
//        list.add(s09)
//        list.add(s10)
//        list.add(s11)
//        list.add(s12)
//        list.add(s13)
//        list.add(s14)
//        list.add(s15)
//        list.add(s16)
//        list.add(s17)
//        list.add(s18)
//
//        map.put("s01",10)
//        map.put("s02",s02)
//        map.put("s03",0.1f)
//
//        val p = PrintLog(true, "Main Test")
//        p.header()
//        p.log("test only message")
//        p.separator()
//        p.log(null)
//        p.log("message with value",10)
//        p.log("message with empty value","")
//        p.divider()
//        p.log("message with another value",0.001f)
//        p.separator()
//        p.log("the listeeeee",list)
//        p.breakLine()
//        p.log("the mappppppp",map)
//        p.footer()
//    }

//    @Test
//    fun `Sample1_test`() {
//        val p = PrintLog(true, "CHANGE_TITLE")
//        p.help()
//    }

    @Test
    fun `GIVEN this_test MUST be_this_thing WHEN something_is_called`() {
        for (i in -3..3) {
            val p = PrintLog(true, "Test i[$i]")
            p.header()
            p.log("GIVEN this_test MUST be_this_thing WHEN something_is_called")
            p.footer()
        }
        val pp = PrintLog(true, "Test Session", 99)
        pp.session()

        
    }


}