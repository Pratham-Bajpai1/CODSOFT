package com.example.alarmapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AlarmAdapter(
    private val context: Context,
    private val alarms: MutableList<Alarm>
) : RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder>() {

    inner class AlarmViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val timeTextView: TextView = view.findViewById(R.id.alarm_time)
        val toggleSwitch: Switch = view.findViewById(R.id.alarm_toggle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.alarm_item, parent, false)
        return AlarmViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        val alarm = alarms[position]
        holder.timeTextView.text = String.format("%02d:%02d", alarm.hour, alarm.minute)
        holder.toggleSwitch.isChecked = alarm.isEnabled

        holder.toggleSwitch.setOnCheckedChangeListener { _: CompoundButton, isChecked: Boolean ->
            alarm.isEnabled = isChecked
            if (isChecked) {
                AlarmUtils.setAlarm(context, alarm.hour, alarm.minute, null)
            } else {
                AlarmUtils.cancelAlarm(context, alarm.hour, alarm.minute)
            }
            // Save updated alarm state
            val alarmStorage = AlarmStorage(context)
            alarmStorage.saveAlarms(alarms)
        }
    }

    override fun getItemCount(): Int {
        return alarms.size
    }
}