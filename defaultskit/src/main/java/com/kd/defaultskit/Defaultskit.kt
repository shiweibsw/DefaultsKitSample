package com.kd.defaultskit

import android.content.Context
import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by Knight_Davion on 2017/8/28.
 */
object Defaultskit {

    val PREFS_NAME: String = "Defaultskit_Prefs"
    lateinit var  mContext:Context

    fun init(context: Context){
        mContext=context
    }

     fun <T> preference(name: String, default: T)=Preference(name, default)

    class Preference<T>(val name: String, val default: T) : ReadWriteProperty<Any?, T> {
        val prefs: SharedPreferences by lazy { mContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE) }

        override fun getValue(thisRef: Any?, property: KProperty<*>): T {
            return findPreference(name, default)
        }
        override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
            putPreference(name, value)
        }
        @Suppress("UNCHECKED_CAST")
        private fun findPreference(name: String, default: T): T = with(prefs) {
            val res: Any = when (default) {
                is Long -> getLong(name, default)
                is String -> getString(name, default)
                is Int -> getInt(name, default)
                is Boolean -> getBoolean(name, default)
                is Float -> getFloat(name, default)
                else -> throw IllegalArgumentException("This type can be saved into Preferences")
            }
            res as T
        }
        private fun putPreference(name: String, value: T) = with(prefs.edit()) {
            when (value) {
                is Long -> putLong(name, value)
                is String -> putString(name, value)
                is Int -> putInt(name, value)
                is Boolean -> putBoolean(name, value)
                is Float -> putFloat(name, value)
                else -> throw IllegalArgumentException("This type can't be saved into Preferences")
            }.apply()
        }
    }
}