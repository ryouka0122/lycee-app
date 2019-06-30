package com.example.myapplication.model

class PrefCode {

    data class PrefCodeInfo(val code: String?=null, val name: String="")

    var prefCode: List<PrefCodeInfo>? = null

    fun test() {
        prefCode?.forEach {
            println(it.code + ":" + it.name)
        }

    }
    fun toNameList(): List<String> {
        val list = ArrayList<String>()

        prefCode?.forEach {
            list.add(it.name)
        }

        return list
    }
}