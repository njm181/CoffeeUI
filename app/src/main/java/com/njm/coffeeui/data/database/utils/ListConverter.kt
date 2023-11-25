package com.njm.coffeeui.data.database.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.njm.coffeeui.data.database.entities.MenuEntity

class ListConverter {

    @TypeConverter
    fun fromListMenu(value: List<MenuEntity>): String {
        val gson = Gson()
       //val type = object : TypeToken<List<MenuEntity>>() {}.type
        //return gson.toJson(value, type)
        return gson.toJson(value)
    }

    @TypeConverter
    fun toListMenu(value: String): List<MenuEntity>{
        val gson = Gson()
        val type = object : TypeToken<List<MenuEntity>>() {}.type
        return gson.fromJson(value, type)
    }
}
