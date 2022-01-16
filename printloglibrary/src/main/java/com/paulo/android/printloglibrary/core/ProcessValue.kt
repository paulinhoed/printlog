package com.paulo.android.printloglibrary.core

import com.paulo.android.printloglibrary.constants.PrintLogConstants.BREAK_LINE
import com.paulo.android.printloglibrary.constants.PrintLogConstants.EMPTY
import com.paulo.android.printloglibrary.constants.PrintLogConstants.NULL
import com.paulo.android.printloglibrary.constants.PrintLogConstants.MAIN_BASE
import com.paulo.android.printloglibrary.enums.ValueTypes
import com.paulo.android.printloglibrary.enums.ValueTypes.*
import com.paulo.android.printloglibrary.extensions.*
import com.paulo.android.printloglibrary.model.PrintLogConfigModel

class ProcessValue(
    private val configModel: PrintLogConfigModel
) {

    private var finalValue: String = NULL

    private var valueType = UNKNOWN

    private var mutableValueMap = Pair<Any?, ValueTypes>(null, UNKNOWN)

    fun execute(value: Any?): String {
        mutableValueMap.first.let { value }
        selectRawType(value)
        return getFinalValueFormatted()
    }

    private fun internalExecute(valueMap: Pair<Any?, ValueTypes>): String {
        val value = valueMap.first
        selectRawType(value)
        return getFinalValueFormatted()
    }


    /**
     * Return final value
     */
    private fun getFinalValueFormatted(): String {
        return finalValue
    }

    /**
     * Select Type
     */
    private fun selectRawType(value: Any?) {
        val valueMap = defineAndGetMutableValueMap(value)

        when (value) {
            null -> NULL
            else -> {
                filterMap(valueMap)
            }
        }
    }

    private fun defineAndGetMutableValueMap(value: Any?): Pair<Any?, ValueTypes> {
        return Pair(value, valueType)
    }

    /**
     * Filters
     */
    private fun filterMap(valueMap: Pair<Any?, ValueTypes>) {
        mutableValueMap = valueMap
        filterPrimitiveTypeByMap(mutableValueMap)
        filterCollectionWithGenericTypeByMap(mutableValueMap)
        filterGenericTypeByMap(mutableValueMap)
    }

    private fun filterPrimitiveTypeByMap(primitiveValueMap: Pair<Any?, ValueTypes>) {
        val value = primitiveValueMap.first
        val type = primitiveValueMap.second.name
        if (type == UNKNOWN.name || type == GENERIC_TYPE.name) {
            when (val simpleName = value?.javaClass?.simpleName) {
                "Integer", "Float", "Double", "Boolean" -> {
                    mutableValueMap = primitiveValueMap.copy(second = PRIMITIVE)
                    launchValueByMap(mutableValueMap, simpleName)
                }
                "String" -> {
                    mutableValueMap = primitiveValueMap.copy(second = PRIMITIVE)
                    launchStringValueByMap(mutableValueMap)
                }
            }
        }
        return
    }

    private fun filterCollectionWithGenericTypeByMap(collWGTValueMap: Pair<Any?, ValueTypes>) {
        val value = collWGTValueMap.first
        val type = collWGTValueMap.second.name
        if (type == UNKNOWN.name || type == GENERIC_TYPE.name) {
            val maxLength = configModel.blockLength - 5
            when (value?.javaClass?.simpleName) {
                "EmptyList" -> {
                    mutableValueMap = collWGTValueMap.copy(second = COLLECTION_WITH_GENERIC_TYPE)
                    finalValue = EMPTY
                }
                "Array" -> {
                    if ((value as Array<*>).isEmpty()) {
                        mutableValueMap = collWGTValueMap.copy(second = COLLECTION_WITH_GENERIC_TYPE)
                        finalValue = EMPTY
                    } else {
                        mutableValueMap = collWGTValueMap.copy(second = COLLECTION_WITH_GENERIC_TYPE)
                        createGenericListCollection(mutableValueMap, maxLength)
                    }
                }
                "ArrayList" -> {
                    if ((value as ArrayList<*>).isEmpty()) {
                        mutableValueMap = collWGTValueMap.copy(second = COLLECTION_WITH_GENERIC_TYPE)
                        finalValue = EMPTY
                    } else {
                        mutableValueMap = collWGTValueMap.copy(second = COLLECTION_WITH_GENERIC_TYPE)
                        createGenericListCollection(mutableValueMap, maxLength)
                    }
                }
                "List" -> {
                    if ((value as ArrayList<*>).isEmpty()) {
                        mutableValueMap = collWGTValueMap.copy(second = COLLECTION_WITH_GENERIC_TYPE)
                        finalValue = EMPTY
                    } else {
                        mutableValueMap = collWGTValueMap.copy(second = COLLECTION_WITH_GENERIC_TYPE)
                        createGenericListCollection(mutableValueMap, maxLength)
                    }
                }
                "HashMap" -> {
                    if ((value as Map<*, *>).isEmpty()) {
                        mutableValueMap = collWGTValueMap.copy(second = COLLECTION_WITH_GENERIC_TYPE)
                        finalValue = EMPTY
                    } else {
                        mutableValueMap = collWGTValueMap.copy(second = COLLECTION_WITH_GENERIC_TYPE)
                        createGenericMapCollection(mutableValueMap, maxLength)
                    }
                }
            }
        }
        return
    }

    private fun filterGenericTypeByMap(genericValueMap: Pair<Any?, ValueTypes>) {
        val value = genericValueMap.first
        val type = genericValueMap.second.name
        if (type == UNKNOWN.name) {
            val maxLength = configModel.blockLength - 5
            val memberProperties = value?.let { getProperties(it) }
            var builder = StringBuilder()
//            builder.append(BREAK_LINE)
            builder.append(MAIN_BASE)
            builder = buildSessionStructureList(maxLength, null)
            when (memberProperties) {
                null -> {
                    mutableValueMap = genericValueMap.copy(second = GENERIC_TYPE)
                    builder.append(NULL)
                    builder = buildSessionStructureList(maxLength, builder)
                    finalValue = builder.toString()
                }
                else -> {
                    for (prop in memberProperties) {
                        val name = prop.name
                        val v = prop.get(value)
                        mutableValueMap = genericValueMap.copy(first = v, second = GENERIC_TYPE)
                        val vv = v?.let { internalExecute(mutableValueMap) }
                        val newVal = "$name = $vv"
                        builder = buildNewLists(newVal, builder, -1)
                    }
                    builder = buildSessionStructureList(maxLength, builder)
                    finalValue = builder.toString()
                }


            }
        }
        return
    }

    /**
     * List Collection Builder
     */
    private fun createGenericListCollection(valueMap: Pair<Any?, ValueTypes>, maxLength: Int) {
        val value = valueMap.first
        val type = valueMap.second.name
        var builder = StringBuilder()
        if (type != COLLECTION_WITH_GENERIC_TYPE.name){
            builder.append(BREAK_LINE)
            builder = buildSessionStructureList(maxLength, null)
//            builder = buildHeaderStructureList(maxLength)
        }
        else builder.append(MAIN_BASE)
        for ((index, v) in (value as List<*>).withIndex()) {
            val internalValueMap: Pair<Any?, ValueTypes> = Pair(v, UNKNOWN)
            val vv = v?.let { internalExecute(internalValueMap) }
            builder = buildNewLists(vv, builder, index)
        }
        if (type != COLLECTION_WITH_GENERIC_TYPE.name)
            builder = buildSessionStructureList(maxLength, builder)
//            builder = buildFooterStructureList(maxLength, builder)
        else builder.append(MAIN_BASE)
        finalValue = builder.toString()
    }

    private fun createGenericMapCollection(valueMap: Pair<Any?, ValueTypes>, maxLength: Int) {
        val value = valueMap.first
        val type = valueMap.second.name
        var builder = StringBuilder()
        if (type != COLLECTION_WITH_GENERIC_TYPE.name) {
            builder.append(BREAK_LINE)
            builder = buildSessionStructureList(maxLength, null)
        }
        else builder.append(MAIN_BASE)
        for ((_key, v) in value as HashMap<*, *>) {
            val internalValueMap: Pair<Any?, ValueTypes> = Pair(v, UNKNOWN)
            val vv = v?.let { internalExecute(internalValueMap) }
            val newVal = "$_key = $vv"
            builder = buildNewLists(newVal, builder, -1)
        }
        if (type != COLLECTION_WITH_GENERIC_TYPE.name)
            builder = buildSessionStructureList(maxLength, builder)
        else builder.append(MAIN_BASE)
        finalValue = builder.toString()
    }

    /**
     * Launchers
     */
    private fun launchValueByMap(valueMap: Pair<Any?, ValueTypes>, primitiveType: String) {
        val value = valueMap.first
        when (primitiveType) {
            "Integer" -> {
                finalValue = (value as Int).toString()
            }
            "Float" -> {
                finalValue = (value as Float).toString()
            }
            "Double" -> {
                finalValue = (value as Double).toString()
            }
            "Boolean" -> {
                finalValue = (value as Boolean).toString()
            }
            "String" -> {
                finalValue = value.toString()
            }
        }
    }

    private fun launchStringValueByMap(valueMap: Pair<Any?, ValueTypes>) {
        val value = valueMap.first
        finalValue = if (value.toString().isEmpty())
            EMPTY
        else value.toString()
    }

}