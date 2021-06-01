package com.example.hardwaresensors

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

    private lateinit var sensorEventListener: SensorEventListener
    private lateinit var sensorManager: SensorManager
    private lateinit var proximitySensor: Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        if(sensorManager==null){
//            Toast.makeText(this,"Could not get sensors",Toast.LENGTH_SHORT).show()
//            finish()
//        }else{
//            val sensors=sensorManager.getSensorList(Sensor.TYPE_ALL)
//            sensors.forEach {
//                Log.d("Sensors","""
//                    ${it.name}  ${it.stringType}  ${it.vendor}
//                """.trimIndent())
//            }
//        }

        sensorManager = getSystemService<SensorManager>()!!

        proximitySensor = sensorManager.getDefaultSensor(SensorManager.SENSOR_PROXIMITY)!!

        sensorEventListener=object :SensorEventListener{
            override fun onSensorChanged(event: SensorEvent?) {
                Log.d("Sensors","""
                    onSensorChanged:${event!!.values[0]}
                """.trimIndent())
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                //nothing
            }
        }
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(
                sensorEventListener,proximitySensor,1000
        )
    }

    override fun onPause() {
        sensorManager.unregisterListener(sensorEventListener)
        super.onPause()
    }
}