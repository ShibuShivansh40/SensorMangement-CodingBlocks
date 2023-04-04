package com.example.sensormangement_codingblocks

import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.getSystemService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Code Required to get all the Sensor List on the Device
        val sensorManager : SensorManager? = getSystemService<SensorManager>()
        if(sensorManager == null){
            Toast.makeText(this, "Couldn't get Sensors" , Toast.LENGTH_SHORT).show()
            finish()
        }
        else{
            val sensors = sensorManager.getSensorList(Sensor.TYPE_ALL)
            sensors.forEach{
                Log.d("SENSOR_LIST",
                    """${it.name} | ${it.stringType} | ${it.vendor}""".trimIndent())
            }
        }
    }
}