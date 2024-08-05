package com.example.alarmapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var currentTime: TextView
    private lateinit var alarmAdapter: AlarmAdapter
    private lateinit var alarmStorage: AlarmStorage
    private val alarms: ArrayList<Alarm> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        currentTime = findViewById(R.id.currentTime)
        updateTime()

        alarmStorage = AlarmStorage(this)
        alarms.addAll(alarmStorage.loadAlarms())

        val setAlarmButton: Button = findViewById(R.id.setAlarmButton)
        setAlarmButton.setOnClickListener {
            val intent = Intent(this, SetAlarmActivity::class.java)
            startActivity(intent)
        }

        /*val alarmListView: ListView = findViewById(R.id.alarmListView)
        alarmAdapter = AlarmAdapter(this, alarms)
        alarmListView.adapter = alarmAdapter*/

        val recyclerView: androidx.recyclerview.widget.RecyclerView = findViewById(R.id.alarmRecyclerView)
        alarmAdapter = AlarmAdapter(this, alarms)
        recyclerView.adapter = alarmAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        //loadAlarms()
    }

    override fun onResume() {
        super.onResume()
        // Refresh alarms in case new ones were set
        alarms.clear()
        alarms.addAll(alarmStorage.loadAlarms())
        alarmAdapter.notifyDataSetChanged()
    }

    private fun updateTime() {
        val handler = Handler(Looper.getMainLooper())
        val runnable = object : Runnable {
            override fun run() {
                currentTime.text = SimpleDateFormat("hh:mm:ss a", Locale.getDefault()).format(Date())
                handler.postDelayed(this, 1000)
            }
        }
        handler.post(runnable)
    }

    /*private fun loadAlarms() {
        // Load alarms from storage or database (for this example, we'll keep it simple)
        alarms.add(Alarm(8, 30, "Wake Up", true))
        alarmAdapter.notifyDataSetChanged()
    }*/
}