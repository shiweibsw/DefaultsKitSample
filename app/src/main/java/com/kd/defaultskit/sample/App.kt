package com.kd.defaultskit.sample

import android.app.Application
import com.kd.defaultskit.Defaultskit

/**
 * Created by Knight_Davion on 2017/8/28.
 */
class App:Application() {
    override fun onCreate() {
        super.onCreate()
        Defaultskit.init(this)
    }
}