package com.paulo.android.printloglibrary
//
//import com.paulo.android.printloglibrary.mock.Mocks
//import com.paulo.android.printloglibrary.model.GenericObjectModel
//import org.junit.Test
//import org.koin.java.KoinJavaComponent
//import kotlin.collections.HashMap
//
//class PrintLogUnitTest: BaseTest() {
////    @Test
////    fun `GIVEN this_test MUST be_this_thing WHEN something_is_called`() {
////
////        mapLog.put("s01",10)
////        mapLog.put("s02",s02)
////        mapLog.put("s03",0.1f)
////
////        pLog(true, "Main Test")
////        pLog.header()
////        pLog.log("test only message")
////        pLog.separator()
////        pLog.log(null)
////        pLog.log("message with value",10)
////        pLog.log("message with empty value","")
////        pLog.divider()
////        pLog.log("message with another value",0.001f)
////        pLog.separator()
////        pLog.log("the listeeeee",list)
////        pLog.breakLine()
////        pLog.log("the mappppppp",map)
////        pLog.footer()
////    }
//
////    @Test
////    fun `Sample1_test`() {
////        pLog(true, "CHANGE_TITLE")
////        pLog.help()
////    }
//
//
//    private val pLog: PrintLog by KoinJavaComponent.inject(PrintLog::class.java)
//
//    @Test
//    fun `GIVEN this_test MUST be_this_thing WHEN something_is_called`() {
//        for(i in -3.. 200) {
//            pLog(true, "INDEX[$i]")
//            for(j in -3.. 200) {
//                pLog.session()
//            }
//            pLog.header()
//            pLog.log("GIVEN this_test MUST be_this_thing WHEN something_is_called")
//            pLog.footer()
//        }
//
//    }
//    @Test
//    fun `GIVEN this_test MUST be_this_thing WHEN something_is_called2`() {
//        pLog(true, "SESSION")
////        pLog.header()
////        pLog.beginChapter(1,"chapter")
////        pLog.beginChapter(2,"chapter 2")
////        pLog.beginChapter(3,"chapter 3")
//////        pLog.breakLine()
////        pLog.session()
//////        pLog.breakLine()
////        pLog.endChapter(2)
////        pLog.endChapter(3)
////        pLog.endChapter(1)
//////        Thread.sleep(3_000)
//////        pLog.log("Delay of 3 seconds")
//////
//////        pLog.footer()
////        pLog.log("PrintLogMock", Mocks().config())
////        pLog.session()
//        pLog.log(Mocks().config2())
//    }
//    @Test
//    fun `GIVEN this_test MUST be_this_thing WHEN something_is_called3`() {
//        pLog(true, "SESSION")
////        pLog.log("PrintLogMock", Mocks().config())
//        val emptyList: List<Any> = Mocks().getEmptyList()
//        val list: List<Any> = Mocks().getList()
//        val map: HashMap<String, Any> = Mocks().getMap()
//        val generic = GenericObjectModel(null, list, map)
//        pLog.log("List", list)
//        pLog.separator()
//        pLog.log("Map", map)
//        pLog.separator()
//        pLog.log("Empty List", emptyList)
//        pLog.separator()
//        pLog.log("Generic", generic)
//    }
//    @Test
//    fun `GIVEN this_test_2 MUST be_this_thing WHEN something_is_called3`() {
//        pLog(true, "SESSION")
////        pLog.log("PrintLogMock", Mocks().config())
//        val emptyList: List<Any> = Mocks().getEmptyList()
//        val list: List<Any> = Mocks().getList()
//        val map: HashMap<String, Any> = Mocks().getMap()
//        val generic = GenericObjectModel(null, list, map)
//        pLog.log("List", list)
//        pLog.separator()
//    }
//}