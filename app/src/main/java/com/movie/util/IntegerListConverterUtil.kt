package com.movie.util

import androidx.room.TypeConverter
import java.util.*
import kotlin.collections.ArrayList

class IntegerListConverterUtil {

    @TypeConverter
    fun fromString(value: String?): List<Int>? {
        if (value == null) {
            return Collections.emptyList()
        }
        val parts = value.split(",").toTypedArray()
        val integers: MutableList<Int> = ArrayList(parts.size)
        for (part in parts) {
            integers.add(part.toInt())
        }
        return integers
    }

    @TypeConverter
    fun toString(integers: List<Int>?): String {
        val builder = StringBuilder()
        if (integers != null) {
            for (integer in integers) {
                builder.append(integer).append(",")
            }
            if (builder.isNotEmpty()) {
                builder.deleteCharAt(builder.length - 1)
            }
        }
        return builder.toString()
    }
}