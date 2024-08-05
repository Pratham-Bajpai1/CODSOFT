package com.example.alarmapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SetAlarmActivity : AppCompatActivity() {
    private lateinit var timePicker: TimePicker
    private lateinit var setAlarmButton: Button
    private lateinit var chooseToneButton: Button
    private var alarmTone: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_alarm)

        timePicker = findViewById(R.id.timePicker)
        setAlarmButton = findViewById(R.id.setAlarmButton)
        chooseToneButton = findViewById(R.id.chooseToneButton)

        setAlarmButton.setOnClickListener {
            val hour = timePicker.hour
            val minute = timePicker.minute
            AlarmUtils.setAlarm(this, hour, minute, alarmTone)

            // Save the new alarm
            val alarms = AlarmStorage(this).loadAlarms().toMutableList()
            alarms.add(Alarm(hour, minute, "Alarm", true))
            AlarmStorage(this).saveAlarms(alarms)

            Toast.makeText(this, "Alarm set for $hour:$minute", Toast.LENGTH_SHORT).show()
            finish() // Go back to the main screen after setting the alarm
        }

        chooseToneButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            alarmTone = data?.data
            Toast.makeText(this, "Alarm tone set", Toast.LENGTH_SHORT).show()
        }
    }
}