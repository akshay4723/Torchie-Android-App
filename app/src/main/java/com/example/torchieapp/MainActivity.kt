package com.example.torchieapp

import android.content.Context
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private var cameraManager : CameraManager ?= null
    private var cameraId : String ?= null
    private var curfun = "off"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button:Button = findViewById(R.id.button)

        cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager?

        try{
            cameraId = cameraManager?.cameraIdList?.get(0)
        }
        catch(e:CameraAccessException){
            Log.d("Camera","${e.printStackTrace()}")
        }

        button.setOnClickListener {
            if(curfun == "off"){
                curfun = "on"
                button.text = "on"
                button.setBackgroundColor(resources.getColor(R.color.on))
                ontorchlight()
            }
            else if(curfun == "on"){
                curfun = "off"
                button.text = "off"
                button.setBackgroundColor(resources.getColor(R.color.off))
                offtorchlight()
            }
        }

    }

    private fun ontorchlight() {
        try {
            cameraManager?.setTorchMode(cameraId!!, true)
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
    }
    private fun offtorchlight() {
        try {
            cameraManager?.setTorchMode(cameraId!!, false)
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
    }
}