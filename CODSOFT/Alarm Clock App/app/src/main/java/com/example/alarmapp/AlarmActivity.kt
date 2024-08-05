package com.example.alarmapp

import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AlarmActivity : AppCompatActivity() {

    private lateinit var ringtone: Ringtone

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)


        val alarmToneString = intent.getStringExtra("ringtone")
        val hour = intent.getIntExtra("hour", 0)
        val minute = intent.getIntExtra("minute", 0)

        // Handle alarm tone
        val alarmTone = alarmToneString?.let { Uri.parse(it) }
        val ringtone: Ringtone = if (alarmTone != null) {
            RingtoneManager.getRingtone(this, alarmTone)
        } else {
            RingtoneManager.getRingtone(this, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM))
        }

        ringtone.play()

        val snoozeButton: Button = findViewById(R.id.snoozeButton)
        snoozeButton.setOnClickListener { snoozeAlarm() }

        val dismissButton: Button = findViewById(R.id.dismissButton)
        dismissButton.setOnClickListener { dismissAlarm() }
    }

    private fun snoozeAlarm() {
        ringtone.stop()
        Toast.makeText(this, "Alarm snoozed for 5 minutes", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun dismissAlarm() {
        ringtone.stop()
        Toast.makeText(this, "Alarm dismissed", Toast.LENGTH_SHORT).show()
        finish()
    }
}