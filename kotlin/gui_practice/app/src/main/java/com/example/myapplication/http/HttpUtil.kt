package com.example.myapplication.http

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.stream.Collectors

class HttpUtil {

    var TIMEOUT_MILLIS = 0

    data class Response(
        val status: Int,
        var body: String?
    )


    fun get(url: String, encoding: String = "UTF-8", headers: Map<String, String>? = null) : Response {

        var conn: HttpURLConnection? = null

        try {
            conn = URL(url).openConnection() as HttpURLConnection
            conn.connectTimeout = TIMEOUT_MILLIS
            conn.readTimeout = TIMEOUT_MILLIS

            conn.requestMethod = "GET"
            conn.useCaches = false
            conn.doOutput = false
            conn.doInput = true

            headers?.forEach(conn::setRequestProperty)

            conn.connect()

            val responseCode = conn.responseCode
            var body: String? = null
            if (responseCode == HttpURLConnection.HTTP_OK) {
                conn.inputStream.use (fun(st) {
                    InputStreamReader(st, encoding).use (fun (isr) {
                        BufferedReader(isr).use {
                            body = it.lines()
                                .collect(Collectors.joining())
                        }
                    })
                })
            }

            return Response(responseCode, body)
        }finally {
            conn?.disconnect()
        }
    }

    fun post(
        url: String,
        data: String,
        encoding: String = "UTF-8",
        headers: Map<String, String>? = null

    ) : Response {

        var conn: HttpURLConnection? = null

        try {
            conn = URL(url).openConnection() as HttpURLConnection
            conn.connectTimeout = TIMEOUT_MILLIS
            conn.readTimeout = TIMEOUT_MILLIS

            conn.requestMethod = "POST"
            conn.useCaches = false
            conn.doOutput = true
            conn.doInput = true

            headers?.forEach(conn::setRequestProperty)

            conn.outputStream.use {
                PrintStream(it,true, encoding).use( fun(ps) {
                    ps.print(data)
                })
            }

            val responseCode = conn.responseCode
            var body: String? = null
            if (responseCode == HttpURLConnection.HTTP_OK) {
                conn.inputStream.use (fun(st) {
                    InputStreamReader(st, encoding).use (fun (isr) {
                        BufferedReader(isr).use {
                            body = it.lines()
                                .collect(Collectors.joining())
                        }
                    })
                })
            }

            return Response(responseCode, body)
        }finally {
            conn?.disconnect()
        }
    }



}
