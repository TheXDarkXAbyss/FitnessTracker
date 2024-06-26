package com.example.fitnesstracker.data.converters

import androidx.room.TypeConverter
import java.util.Date

class RoomConverters {

    @TypeConverter // Marks a method as a type converter.
    fun convertDateToLong(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun convertLongToDate(timeLong: Long): Date {
        return Date(timeLong)
    }

}