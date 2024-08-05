package com.example.alarmapp

data class Alarm(
    val hour: Int,
    val minute: Int,
    val label: String,
    var isEnabled: Boolean
)
