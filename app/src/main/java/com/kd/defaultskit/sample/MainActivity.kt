package com.kd.defaultskit.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.kd.defaultskit.Defaultskit

class MainActivity : AppCompatActivity() {

    var s:String by Defaultskit.preference("111","11")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("MainActivity",s)
        s="222222222"
    }
}
