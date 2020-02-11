package com.example.trainning.data

import android.content.Context
import com.example.trainning.models.Child
import com.google.gson.Gson

class PreferenceUtils : AppPreference{
    private var gson: Gson? = null

    constructor(context: Context?, gson: Gson) : super(context){
        instance = this
        this.gson = gson
    }

    fun putListChild(mList: ArrayList<Child>){
        gson?.let {
            putString(DATA_CHILD, it.toJson(mList))
        }
    }

    fun getListChild(): MutableList<Child>?{
        val mList: Array<Child>? = gson?.fromJson(getString(DATA_CHILD), Array<Child>::class.java)
        return mList?.toMutableList()
    }

    companion object{
        lateinit var instance: PreferenceUtils
        const val DATA_CHILD = "DATA_CHILD"
    }
}