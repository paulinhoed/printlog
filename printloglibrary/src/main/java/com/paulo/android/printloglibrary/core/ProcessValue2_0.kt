package com.paulo.android.printloglibrary.core
//
////import com.paulo.android.printloglibrary.enums.FilterStatus
//import com.paulo.android.printloglibrary.constants.PrintLogConstants.BREAK_LINE
//import com.paulo.android.printloglibrary.constants.PrintLogConstants.EMPTY
//import com.paulo.android.printloglibrary.constants.PrintLogConstants.NULL
//import com.paulo.android.printloglibrary.enums.ValueTypes
//import com.paulo.android.printloglibrary.extensions.*
//import com.paulo.android.printloglibrary.model.PrintLogConfig
//
//class ProcessValue2_0(
//    private val config: PrintLogConfig
//) {
//
//    private var formattedValueType: String = NULL
//
//    private var valueType = ValueTypes.UNKNOWN.name
//
////    private var status = FilterStatus.DEFAULT
//
//    fun execute(value: Any?): String {
//        selectRawType(value)
//        selectType(value)
//        return getValueTypeFormatted()
//    }
//
//    private fun getValueTypeFormatted(): String {
//        return formattedValueType
//    }
//
//    private fun selectType(value: Any?) {
//        when (value) {
//            null -> NULL
//            else -> {
//                filters(value)
//            }
//        }
//    }
//
//    private fun selectRawType(value: Any?) {
//        var valueMap: Pair<Any?, String> = Pair(value, valueType)
//
//        when (value) {
//            null -> NULL
//            else -> {
//                filterMap(valueMap)
//                filters(value)
//            }
//        }
//    }
//
//    private fun filterMap(map: Pair<Any?, String>) {
//        filterPrimitiveTypeByMap(map)
////        filterCollectionWithGenericTypeByMap(map)
////        filterCollectionWithDefinedType(value)
////        filterGenericTypeByMap(map)
//    }
//
//    private fun filters(value: Any) {
//
////        when (status) {
////            FilterStatus.DEFAULT -> {
////                filterPrimitiveType(value)
////                status = FilterStatus.PRIMITIVE_PROCESS_SUCCEED
////            }
////            FilterStatus.PRIMITIVE_PROCESS_SUCCEED -> {
////                filterCollectionWithGenericType(value)
////                status = FilterStatus.COLLECTION_WITH_GENERIC_TYPE_PROCESS_SUCCEED
////            }
////            FilterStatus.COLLECTION_WITH_GENERIC_TYPE_PROCESS_SUCCEED -> {
////                filterCollectionWithDefinedType(value)
////                status = FilterStatus.COLLECTION_WITH_DEFINED_TYPE_PROCESS_SUCCEED
////            }
////            FilterStatus.COLLECTION_WITH_DEFINED_TYPE_PROCESS_SUCCEED -> {
////                filterCollectionWithGenericType(value)
////                status = FilterStatus.GENERIC_TYPE_PROCESS_SUCCEED
////            }
////            else -> FilterStatus.DEFAULT
////        }
//        filterPrimitiveType(value)
//        filterCollectionWithGenericType(value)
////        filterCollectionWithDefinedType(value)
//        filterGenericType(value)
//    }
//
//    private fun filterPrimitiveTypeByMap(map: Pair<Any?, String>) {
//        val value = map.first
//        val type = map.second
//        if (type == ValueTypes.UNKNOWN.name) {
//            when (val simpleName = value?.javaClass?.simpleName) {
//                "Integer", "Float", "Double", "Boolean" -> {
////                    map.second = ValueTypes.PRIMITIVE.name
////                    launchValueByMap(map, simpleName)
//                }
//                "String" -> {
////                    map.second = ValueTypes.PRIMITIVE.name
////                    launchStringValueByMap(map)
//                }
//            }
//        }
//        return
//    }
//
//    private fun filterPrimitiveType(value: Any) {
//        if (valueType == ValueTypes.UNKNOWN.name) {
////            val type = value.javaClass.declaredClasses.
////            val type = value.javaClass.typeName
////            val type = value.javaClass.componentType.canonicalName
////            val type2 = value.javaClass.componentType.typeName
////            val type4 = value.javaClass.isPrimitive
//            when (val simpleName = value.javaClass.simpleName) {
//                "Integer" -> {
//                    launchValue(value, simpleName)
//                    valueType = ValueTypes.PRIMITIVE.name
//                }
//                "Float" -> {
//                    launchValue(value, simpleName)
//                    valueType = ValueTypes.PRIMITIVE.name
//                }
//                "Double" -> {
//                    launchValue(value, simpleName)
//                    valueType = ValueTypes.PRIMITIVE.name
//                }
//                "Boolean" -> {
//                    launchValue(value, simpleName)
//                    valueType = ValueTypes.PRIMITIVE.name
//                }
//                "String" -> {
//                    launchStringValue(value)
//                    valueType = ValueTypes.PRIMITIVE.name
//                }
//            }
//        }
//        return
//    }
//
//    private fun filterCollectionWithGenericType(value: Any) {
//        if (valueType == ValueTypes.UNKNOWN.name) {
//            val maxLength = config.blockLength - 5
//            when (val simpleName = value.javaClass.simpleName) {
//                "Array" -> {
//                    if ((value as Array<*>).isEmpty()) EMPTY
//                    else createGenericListCollection(value, maxLength)
//                }
//                "ArrayList" -> {
//                    if ((value as ArrayList<*>).isEmpty()) EMPTY
//                    else createGenericListCollection(value, maxLength)
//                }
//                "List" -> {
//                    if ((value as ArrayList<*>).isEmpty()) EMPTY
//                    else createGenericListCollection(value, maxLength)
//                }
//                "HashMap" -> {
//                    if ((value as Map<*, *>).isEmpty()) EMPTY
//                    else createGenericMapCollection(value, maxLength)
//                }
//                else -> println(value.javaClass.simpleName)
//            }
//            valueType = ValueTypes.COLLECTION_WITH_GENERIC_TYPE.name
//        }
//        return
//    }
//
////    private fun filterCollectionWithDefinedType(value: Any) {
////        if(valueType. == ValueTypes.UNKNOWN) {
////            val maxLength = config.blockLength - 5
////            when (value) {
////                Array<Any?>::class.java -> {
////                    if ((value as Array<*>).isEmpty()) EMPTY
////                    else {
////
//////                        createGenericListCollection(value, maxLength)
////                    }
////                }
////                ArrayList::class.java -> {
//////                    if ((value as ArrayList<*>).isEmpty()) EMPTY
//////                    else createGenericListCollection(value, maxLength)
////                }
////                List::class.java -> {
//////                    if ((value as ArrayList<*>).isEmpty()) EMPTY
//////                    else createGenericListCollection(value, maxLength)
////                }
////                HashMap::class.java -> {
//////                    if ((value as Map<*, *>).isEmpty()) EMPTY
//////                    else createGenericMapCollection(value, maxLength)
////                }
////            }
////            valueType = ValueTypes.COLLECTION_WITH_DEFINED_TYPE
////        }
////        return
////    }
//
//    private fun filterGenericType(value: Any) {
//        if (valueType == ValueTypes.UNKNOWN.name) {
//            //
//            valueType = ValueTypes.GENERIC_TYPE.name
//        }
//    }
//
//    private fun createGenericListCollection(value: Any, maxLength: Int) {
//        var builder = StringBuilder()
//        builder.append(BREAK_LINE)
//        builder = buildHeaderStructureList(maxLength)
//        for ((index, v) in (value as List<*>).withIndex()) {
//            val vv = v?.let { execute(it) }
////            val valueProcessed = fineTuneProccess(value)
//            builder = buildNewLists(vv, builder, index)
//        }
//        builder = buildFooterStructureList(maxLength, builder)
//        formattedValueType = builder.toString()
//    }
//
//    private fun createGenericMapCollection(value: Map<*, *>, maxLength: Int) {
//        var builder = StringBuilder()
//        builder.append(BREAK_LINE)
//        builder = buildSessionStructureList(maxLength, null)
//        for ((_key, _value) in value as HashMap<*, *>) {
//            val vv = _value
//            val newVal = "$_key = $vv"
//            builder = buildNewLists(newVal, builder, -1)
//        }
//        builder = buildSessionStructureList(maxLength, builder)
//        formattedValueType = builder.toString()
//    }
//
//    /**
//     *
//     */
//    private fun launchValue(value: Any, primitiveType: String) {
//        when (primitiveType) {
//            "Integer" -> {
//                formattedValueType = (value as Int).toString()
//            }
//            "Float" -> {
//                formattedValueType = (value as Float).toString()
//            }
//            "Double" -> {
//                formattedValueType = (value as Double).toString()
//            }
//            "Boolean" -> {
//                formattedValueType = (value as Boolean).toString()
//            }
////            "String"
//        }
////        formattedValueType = value.toString()
//    }
//
//    private fun launchStringValue(value: Any) {
//        formattedValueType = if (value.toString().isEmpty())
//            EMPTY
//        else value.toString()
//    }
//
//
//    private fun fineTuneProccess(value: List<*>): Any {
//        val p = ProcessValue2_0(config)
//        return p.execute(value)
//    }
//
//}
//
///*
//
//
//
//    }
//
//    internal fun processValue(value: Any?): Any {
//
//        val maxLength = config.blockLength - 5
//
//        val thisValue: Any = when (value) {
//            null -> NULL
//            else -> {
//                when (value.javaClass) {
//
//                    String::class.java -> {
//                        if ((value as String).isEmpty()) EMPTY
//                        else {
//                            var builder: StringBuilder
//                            builder = buildHeaderStructureList(maxLength)
//                            for ((index, v) in value.withIndex()) {
//                                builder = buildLists(v, builder, index)
//                            }
//                            builder = buildFooterStructureList(maxLength, builder)
//                            return builder.toString()
//                        }
//                    }
//
//                    Array<Any?>::class.java -> {
//                        val list = value as Array<*>
//                        if (list.isEmpty()) EMPTY
//                        else {
//                            var builder = StringBuilder()
//                            builder.append(BREAK_LINE)
//                            builder = buildHeaderStructureList(maxLength)
//                            for ((index, v) in value.withIndex()) {
//                                builder = buildLists(v, builder, index)
//                            }
//                            builder = buildFooterStructureList(maxLength, builder)
//                            return builder.toString()
//                        }
//                    }
//
//                    ArrayList::class.java -> {
//                        if ((value as ArrayList<*>).isEmpty()) EMPTY
//                        else {
//                            var builder = StringBuilder()
//                            builder.append(BREAK_LINE)
//                            builder = buildSessionStructureList(maxLength, null)
//                            for ((index, v) in value.withIndex()) {
//                                builder = buildNewLists(v, builder, index)
//                            }
//                            builder = buildSessionStructureList(maxLength, builder)
//                            return builder.toString()
//                        }
//                    }
//
//                    HashMap::class.java -> {
//                        if ((value as Map<*, *>).isEmpty()) EMPTY
//                        else {
//                            var builder = StringBuilder()
//                            builder.append(BREAK_LINE)
//                            builder = buildSessionStructureList(maxLength, null)
//                            for ((_key, _value) in value as HashMap<*, *>) {
//                                val vv = processValueByTheBuilder(_value)
//                                val newVal = "$_key = $vv"
//                                builder = buildNewLists(newVal, builder, -1)
//                            }
//                            builder = buildSessionStructureList(maxLength, builder)
//                            return builder.toString()
//                        }
//                    }
//
//                    Integer::class.java -> return value
//
//                    Float::class.java -> return value
//
//                    Double::class.java -> return value
//
//                    Number::class.java -> return value
////                    List<Any?>::class.java -> {
////
////                    } nao funfa
////                    Array<Any?>::class.java -> {
////                        val list = value as Array<*>
////                        if (list.isEmpty()) EMPTY
////                        else {
////                            var builder = buildHeaderStructureList(maxLength)
////                            for ((index, v) in value.withIndex()) {
////                                builder = buildLists(v, builder, index)
////                            }
////                            builder = buildFooterStructureList(maxLength, builder)
////                            return builder.toString()
////                        }
////                    } noa funfa
//                    else -> {
//                        val isPrimitive = value.javaClass.isPrimitive
//                        println("primitive:$isPrimitive")
//                        if (isPrimitive) return value
//                        else {
//                            val memberProperties = javaClass.kotlin.memberProperties
//                            println("memberProperties:$memberProperties")
//                            println("+++++++==================================")
//                            for (prop in memberProperties) {
////                    println("${prop.name} = ${prop.get(value)}")
//                                println("+++++++----------------------------------")
//                                println("PropName: ${prop.name}")
//                                println("PropTypeParameters: ${prop.returnType}")
//                                val nameClass = prop.returnType.toString().split(".")
//                                println("nameClass: ${nameClass[nameClass.lastIndex]}")
//                                //
//                                val objectName = value.javaClass.simpleName
//                                val splittedParts = (value.toString()).split("(")[1]
//                                val parameters = (splittedParts.split(")")[0]).split(", ")
//
//                                val thisValue =
//                                    processGenericObject(objectName, parameters, maxLength)
//                                //
//                                println(thisValue)
//                                println("+++++++----------------------------------")
//                            }
//
//
//
//                            return value
////                    val res = processValue2(prop.get(value))
////                    println(res)
////                    if (value.javaClass == Array<String>::class.java) {
////                        val list = value as Array<*>
////                        if (list.isEmpty()) valueCopy = EMPTY else {
////                            var builder = buildHeaderStructureList(maxLength)
////                            for ((index, v) in value.withIndex()) {
////                                builder = buildLists(v, builder, index)
////                            }
////                            builder = buildFooterStructureList(maxLength, builder)
////                            valueCopy = builder.toString()
////                        }
//////                    }
////                            println("+++++++----------------------------------")
////
////                            return value
//                        }
//                    }
//                }
//            }
//        }
//        return thisValue
//    }
//
// */