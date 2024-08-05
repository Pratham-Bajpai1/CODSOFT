package com.example.alarmapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.util.Log

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val alarmStorage = AlarmStorage(context)
        val alarms = alarmStorage.loadAlarms()

        /*val alarmHour = intent.getIntExtra("hour", -1)
        val alarmMinute = intent.getIntExtra("minute", -1)

        // Check if the alarm is enabled
        val isAlarmEnabled = alarms.any { it.hour == alarmHour && it.minute == alarmMinute && it.isEnabled }

        if (isAlarmEnabled) {
            val alarmToneUri = intent.getStringExtra("alarmTone")?.let { Uri.parse(it) }
            val alarmIntent = Intent(context, AlarmActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                putExtra("alarmTone", alarmToneUri.toString())
            }
            context.startActivity(alarmIntent)
        } else {
            Log.d("AlarmReceiver", "Alarm not enabled")
        }*/
        val alarmToneString = intent.getStringExtra("alarmTone")
        /*val hour = intent.getIntExtra("hour", 0)
        val minute = intent.getIntExtra("minute", 0)

        // Handle alarm tone
        val alarmTone = alarmToneString?.let { Uri.parse(it) }
        val ringtone: Ringtone = if (alarmTone != null) {
            RingtoneManager.getRingtone(context, alarmTone)
        } else {
            RingtoneManager.getRingtone(context, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM))
        }

        ringtone.play()*/
        val alarmIntent = Intent(context, AlarmActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            putExtra("ringtone", alarmToneString)
        }

        context.startActivity(alarmIntent)
    }
}