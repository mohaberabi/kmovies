package com.mohaberabi.kmovies.core.data.source.local.database.convertor

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import androidx.room.TypeConverters


@ProvidedTypeConverter
class MediaTypeCovnertors {


    @TypeConverter
    fun fromListToString(value: List<String>): String = value.joinToString { "," }


    @TypeConverter
    fun fromStringToSet(value: String): Set<String> = value.splitToList().toSet()


    @TypeConverter
    fun fromSetToString(value: Set<String>): String = value.joinToString { "," }


    @TypeConverter
    fun fromStringToList(value: String): List<String> = value.splitToList()
}


private fun String.splitToList(sep: String = ","): List<String> = split(sep)