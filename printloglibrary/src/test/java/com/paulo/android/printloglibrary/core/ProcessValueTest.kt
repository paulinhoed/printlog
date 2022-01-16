package com.paulo.android.printloglibrary.core
//
//import com.paulo.android.printloglibrary.constants.PrintLogConstants
//import com.paulo.android.printloglibrary.model.GenericObjectModel
//import com.paulo.android.printloglibrary.model.PrintLogConfigModel
//import org.junit.Assert
//import org.junit.Test
//
//class ProcessValueTest {
//
//    private val configModel: PrintLogConfigModel = PrintLogConfigModel(
//        true,
//        "ProcessValueTest",
//        0,
//        100,
//        0
//    )
//
//    private val v = ProcessValue(configModel)
//
//    @Test
//    fun `GIVEN String null MUST return NULL`() {
//        val value = null
//        val expected = PrintLogConstants.NULL
//
//        val response = v.execute(value)
//
//        Assert.assertEquals(expected, response)
//    }
//
//    @Test
//    fun `GIVEN String empty MUST return EMPTY`() {
//        val value = ""
//        val expected = PrintLogConstants.EMPTY
//
//        val response = v.execute(value)
//
//        Assert.assertEquals(expected, response)
//    }
//
//    @Test
//    fun `GIVEN emptyList MUST return EMPTY`() {
//        val emptyList = listOf<Any>()
//        val expected = PrintLogConstants.EMPTY
//
//        val response = v.execute(emptyList)
//
//        Assert.assertEquals(expected, response)
//    }
//
//    @Test
//    fun `GIVEN String xxx MUST return xxx`() {
//        val value = "xxx"
//        val expected = "xxx"
//
//        val response = v.execute(value)
//
//        Assert.assertEquals(expected, response)
//    }
//
//    @Test
//    fun `GIVEN Number as Int MUST return String`() {
//        val value: Number = 10
//        val expected: String = "10"
//
//        val response = v.execute(value)
//
//        Assert.assertEquals(expected, response)
//    }
//
//    @Test
//    fun `GIVEN Number as Float MUST return String`() {
//        val value: Number = 10.123F
//        val expected: String = "10.123"
//
//        val response = v.execute(value)
//
//        Assert.assertEquals(expected, response)
//    }
//
//    @Test
//    fun `GIVEN Number as Double MUST return String`() {
//        val value: Number = 10.1111111
//        val expected: String = "10.1111111"
//
//        val response = v.execute(value)
//
//        Assert.assertEquals(expected, response)
//    }
//
//    @Test
//    fun `GIVEN Int MUST return String`() {
//        val value: Int = 0
//        val expected: String = "0"
//
//        val response = v.execute(value)
//
//        Assert.assertEquals(expected, response)
//    }
//
//    @Test
//    fun `GIVEN Float MUST return String`() {
//        val value: Float = 0.1F
//        val expected: String = "0.1"
//
//        val response = v.execute(value)
//
//        Assert.assertEquals(expected, response)
//    }
//
//    @Test
//    fun `GIVEN Double MUST return String`() {
//        val value: Double = 0.11
//        val expected: String = "0.11"
//
//        val response = v.execute(value)
//
//        Assert.assertEquals(expected, response)
//    }
//
//    @Test
//    fun `GIVEN Number MUST return String`() {
//        val value: Number = 100
//        val expected: String = "100"
//
//        val response = v.execute(value)
//
//        Assert.assertEquals(expected, response)
//    }
//
//    @Test
//    fun `GIVEN Boolean as true MUST return String`() {
//        val value: Boolean = true
//        val expected: String = "true"
//
//        val response = v.execute(value)
//
//        Assert.assertEquals(expected, response)
//    }
//
//    @Test
//    fun `GIVEN Boolean as false MUST return String`() {
//        val value: Boolean = false
//        val expected: String = "false"
//
//        val response = v.execute(value)
//
//        Assert.assertEquals(expected, response)
//    }
//
//    @Test
//    fun `GIVEN List of primitives MUST return String`() {
//        val value = mutableListOf<Any>()
//        value.add("String")
//        value.add(1)
//        value.add(false)
//        val expected: String =
//            "#  [================================================================================================|\n" +
//                    "#  +  (000) String \n" +
//                    "#  +  (001) 1 \n" +
//                    "#  +  (002) false \n" +
//                    "#  [================================================================================================|"
//
//        val response = v.execute(value)
//
//        Assert.assertEquals(expected, response)
//    }
//
//    @Test
//    fun `GIVEN List of primitives and one empty list MUST return String`() {
//        val value = mutableListOf<Any>()
//        val emptyList = listOf<Any>()
//        value.add("String")
//        value.add(1)
//        value.add(false)
//        value.add(emptyList)
//        val expected: String =
//            "#  [================================================================================================|\n" +
//                    "#  +  (000) String \n" +
//                    "#  +  (001) 1 \n" +
//                    "#  +  (002) false \n" +
//                    "#  +  (003) [EMPTY] \n" +
//                    "#  [================================================================================================|"
//
//        val response = v.execute(value)
//
//        Assert.assertEquals(expected, response)
//    }
//
//    @Test
//    fun `GIVEN List with one empty list MUST return String`() {
//        val value = mutableListOf<Any>()
//        val emptyList = listOf<Any>()
//        value.add(emptyList)
//        val expected: String =
//            "#  [================================================================================================|\n" +
//                    "#  +  (000) [EMPTY] \n" +
//                    "#  [================================================================================================|"
//
//        val response = v.execute(value)
//
//        Assert.assertEquals(expected, response)
//    }
//
//    @Test
//    fun `GIVEN emptyHashMap MUST return EMPTY`() {
//        val emptyHash = hashMapOf<Any, Any?>()
//        val expected = PrintLogConstants.EMPTY
//
//        val response = v.execute(emptyHash)
//
//        Assert.assertEquals(expected, response)
//    }
//
//    @Test
//    fun `GIVEN hashMap with primitives MUST return String`() {
//        val hashMap = hashMapOf<Any, Any?>()
//        hashMap[1] = "um"
//        hashMap["2"] = false
//        hashMap[true] = 1
//        val expected: String =
//            "\n#  +------------------------------------------------------------------------------------------------|\n" +
//                "#  +  1 = um \n" +
//                "#  +  2 = false \n" +
//                "#  +  true = 1 \n" +
//                "#  +------------------------------------------------------------------------------------------------|\n"
//
//        val response = v.execute(hashMap)
//        println(response)
//
//        Assert.assertEquals(expected, response)
//    }
//
//    @Test
//    fun `GIVEN GenericObject MUST return String`() {
//        val list = mutableListOf<Any>()
//        list.add("String")
//        list.add(1)
//        list.add(false)
//        val hashMap = hashMapOf<String, Any>()
//        hashMap["1"] = "um"
//        hashMap["2"] = false
//        hashMap["3"] = 1
//        val value = GenericObjectModel(true, list, hashMap)
//        val expected: String =
//                "\n" +
//                "#  +------------------------------------------------------------------------------------------------|\n" +
//                "#  +  list = [String, 1, false] \n" +
//                "#  +  map = {1=um, 2=false, 3=1} \n" +
//                "#  +  primitive = true \n" +
//                "#  +------------------------------------------------------------------------------------------------|\n"
//
//        val response = v.execute(value)
//
//        Assert.assertEquals(expected, response)
//    }
//
//
//}