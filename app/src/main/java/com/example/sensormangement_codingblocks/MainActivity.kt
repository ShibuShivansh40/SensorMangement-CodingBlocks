package com.example.sensormangement_codingblocks

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.getSystemService

class MainActivity : AppCompatActivity() {

    lateinit var sensorEventListener: SensorEventListener
    lateinit var sensorManager : SensorManager
    lateinit var proxSensor: Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Code Required to get all the Sensor List on the Device

        sensorManager = getSystemService<SensorManager>()!!
//        if(sensorManager == null){
//            Toast.makeText(this, "Couldn't get Sensors" , Toast.LENGTH_SHORT).show()
//            finish()
//        }
//        else{
//            val sensors = sensorManager.getSensorList(Sensor.TYPE_ALL)
//            sensors.forEach{
//                Log.d("SENSOR_LIST",
//                    """${it.name} | ${it.stringType} | ${it.vendor}""".trimIndent())
//            }
//        }

//        How to detect change in the Proximity Sensor?
        proxSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        sensorEventListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                Log.d(
                    "SENSOR_CHANGE", """
                    onSensorChanged: ${event!!.values[0]}
                """.trimIndent()
                )
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            }
        }
    }

    override fun onResume() {
        super.onResume()
        proxSensor.also{
            sensorManager.registerListener(sensorEventListener, proxSensor, 1000*1000)
        }
        //every one second
    }
    override fun onPause(){
        sensorManager.unregisterListener(sensorEventListener)
        super.onPause()
    }
}