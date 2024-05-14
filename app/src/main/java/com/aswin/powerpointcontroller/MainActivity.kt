package com.aswin.powerpointcontroller

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<ImageButton>(R.id.imageButton).setOnClickListener {
            sendCommand("next")
        }

        findViewById<ImageButton>(R.id.imageButton2).setOnClickListener {
            sendCommand("previous")
        }

        findViewById<TextView>(R.id.imageButton3).setOnClickListener {
            sendCommand("exit")
        }

        findViewById<TextView>(R.id.imageButton34).setOnClickListener {
            sendCommand("enter")
        }
    }

    private fun sendCommand(command: String) {
        val url = "http://192.168.143.130:5005/control_commands"
        val client = OkHttpClient()
        val mediaType = "application/json; charset=utf-8".toMediaType()
        val requestBody = "{\"command\": \"$command\"}".toRequestBody(mediaType)
        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()
        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                // Handle response if needed
                val responseBody = response.body?.string()
                println("Response: $responseBody")
            }
        })
    }
}
