package com.hongwei.exercise.motion

import android.content.Context
import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log

class MotionReader(private val context: Context) {
    companion object {
        private const val TAG = "aaaa"
    }

    fun test() {
        val manager = context.getSystemService(SENSOR_SERVICE) as SensorManager

        manager.getSensorList(Sensor.TYPE_ALL).forEach {
            Log.e(TAG, "getSensorList: ${it.name}")
        }

        val stepSensor = manager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        Log.e(TAG, "stepSensor: $stepSensor")

        stepSensor?.let {
            manager.registerListener(object : SensorEventListener {
                override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
                    Log.e(TAG, "onAccuracyChanged, sensor: $sensor, accuracy: $accuracy")
                }

                override fun onSensorChanged(event: SensorEvent) {
                    Log.e(TAG, "onSensorChanged, event: $event")

                    if (event.sensor.type == Sensor.TYPE_STEP_COUNTER) {
                        Log.e(TAG, "onSensorChanged: 当前步数：${event.values[0]}")
                    }
                }

            }, stepSensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }
}