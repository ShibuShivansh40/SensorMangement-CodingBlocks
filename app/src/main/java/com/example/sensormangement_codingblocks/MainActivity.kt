package com.example.sensormangement_codingblocks

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import java.util.*
import kotlin.math.roundToInt
import kotlin.random.Random

class MainActivity : AppCompatActivity(), SensorEventListener {

    lateinit var sensorEventListener: SensorEventListener
    lateinit var sensorManager : SensorManager
    lateinit var proxSensor: Sensor
    lateinit var accelSensor: Sensor

    val colors = arrayOf(Color.BLACK, Color.GRAY, Color.MAGENTA, Color.DKGRAY, Color.CYAN, Color.BLUE, Color.RED, Color.GREEN, Color.TRANSPARENT, Color.WHITE )
    override fun onSensorChanged(event: SensorEvent?) {
//        Log.d(
//            "SENSOR_CHANGE", """
//                    onSensorChanged: ${event!!.values[0]}
//                """.trimIndent()
//        )
//        if(event!!.values[0] > 0){
//            val proxyIndicator = findViewById<FrameLayout>(R.id.proxyIndicator)
//            proxyIndicator.setBackgroundColor(colors[Random.nextInt(10)])
//            // Here it will change the background color of the FrameLayout for ProxyIndicator whenever tbere is a chhange in the Sensor Value
//        }

        //        Log.d("SENSOR_CHANGED_ACCEL" , """
//            ---
//            ax = ${event!!.values[0]}
//            ay = ${event!!.values[1]}
//            az = ${event!!.values[2]}
//            ---
//        """.trimIndent())

        val bgColor = Color.rgb(
            accel2Color(event!!.values[0]),
            accel2Color(event!!.values[1]),
            accel2Color(event!!.values[2])
        )
        val accelIndicator = findViewById<FrameLayout>(R.id.accelIndicator)
        accelIndicator.setBackgroundColor(bgColor)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
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
//        How to detect change in the Accelerometer Sensor?
        accelSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        }
    override fun onResume() {
        super.onResume()
//        proxSensor.also{
//            sensorManager.registerListener(this, proxSensor, 1000*1000)
//        }
        accelSensor.also{
            sensorManager.registerListener(this, accelSensor, 1000*1000)
        }
        //every one second
    }
    override fun onPause(){
        sensorManager.unregisterListener(this)
        super.onPause()
    }
    private fun accel2Color(accel : Float) :Int = (((accel + 12)/24)*255).roundToInt()
}
