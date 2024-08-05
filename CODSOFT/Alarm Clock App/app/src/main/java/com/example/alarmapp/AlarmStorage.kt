package com.example.alarmapp

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AlarmStorage(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("alarms", Context.MODE_PRIVATE)
    private val gson = Gson()
    private val alarmsKey = "alarms_list"

    fun saveAlarms(alarms: List<Alarm>) {
        val json = gson.toJson(alarms)
        prefs.edit().putString(alarmsKey, json).apply()
    }

    fun loadAlarms(): List<Alarm> {
        val json = prefs.getString(alarmsKey, null)
        return if (json != null) {
            val type = object : TypeToken<List<Alarm>>() {}.type
            gson.fromJson(json, type)
        } else {
            emptyList()
        }
    }
}