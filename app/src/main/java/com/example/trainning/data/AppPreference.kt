package com.example.trainning.data

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import java.lang.Exception

open class AppPreference
@SuppressLint("CommitPrefEdits")
constructor(context: Context?, preferenceName: String = "dataPreferences") {
    private var mPreference: SharedPreferences? = null
    private var mEditor: SharedPreferences.Editor? = null

    init {
        mPreference = context?.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
        mEditor = mPreference?.edit()
    }

    fun putString(key: String, value: String){
        try {
            mEditor?.putString(key, value)
            mEditor?.commit()
        }catch (ex: Exception) { ex.printStackTrace() }
    }

    fun getString(key: String, defaultValue: String = "") : String?{
        return mPreference?.getString(key, defaultValue)
    }

    fun clearCache(){
        try {
            mEditor?.clear()?.commit()
        }catch (ex: Exception){
            ex.printStackTrace()
        }
    }
}