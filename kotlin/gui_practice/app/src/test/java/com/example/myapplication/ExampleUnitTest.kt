package com.example.myapplication

import com.example.myapplication.model.PrefCode
import com.github.kittinunf.fuel.httpGet
import com.google.gson.Gson
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_GSON_http_connection() {
        val uri = "http://lycee-project.net/prefectures.json"
        val result = uri.httpGet().response()

        println(String(result.second.data))

        val prefCode = Gson().fromJson(String(result.second.data), PrefCode::class.java)
        println()
        println("+ + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + +")
        prefCode.prefCode?.let {
            it.forEach(fun(it2) {
                println(it2.code + "::" + it2.name)
            })
        }
    }

    @Test
    fun test_Moshi_http_connection() {
        val uri = "http://lycee-project.net/prefectures.json"
        val result = uri.httpGet().response()
        val resultJson = String(result.second.data)

        println(resultJson)


        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val prefCode = moshi.adapter(PrefCode::class.java).fromJson(resultJson)

        println()
        println("+ + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + + +")
        prefCode?.prefCode?.let {
            it.forEach(fun(it2) {
                println(it2.code + "::" + it2.name)
            })
        }
    }



}
