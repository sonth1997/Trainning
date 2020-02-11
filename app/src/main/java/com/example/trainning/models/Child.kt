package com.example.trainning.models

import java.io.Serializable

data class Child(
    val id: Int,
    var name: String? = "",
    var time: String? = "",
    var gender: GENDER? = GENDER.MALE,
    var sick: SICK? = SICK.ADHD,
    var note: String? = ""
) : Serializable

enum class GENDER(val value: String){
    MALE("MALE"),
    FEMALE("FEMALE"),
    OTHER("OTHER")
}

enum class SICK(val values : String){
    ADHD("ADHD"),
    LD("LD"),
    HO("HO"),
    MR("MR"),
    HATHOI("HATHOI"),
    DAUHONG("DAUHONG")
}