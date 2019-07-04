package com.example.myapplication

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.myapplication.http.HttpUtil
import com.example.myapplication.model.PrefCode
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.httpGet
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_layout.*
import java.util.stream.Collectors

class MainActivity : AppCompatActivity() {

    private fun showDialog(title: String, msg: String) {
        val dialog = NoticeDialogFragment()
        dialog.title = title
        dialog.msg = msg
        dialog.show(supportFragmentManager, "MainActivity")
    }

    private fun clickListener(model: View, title: String, msg: ()->String) {
        model.setOnClickListener{
            showDialog(title, msg.invoke())
        }
    }

    private fun setupFuel() {
        FuelManager.instance.basePath = "http://lycee-project.net"
        FuelManager.instance.baseHeaders = mapOf("UserAgent" to "LyceeAppSample")
    }

    private fun loadPrefCodeList() {
        val uri = "/prefectures.json"
        println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★")
        val result = HttpUtil().post(
            "http://lycee-project.net/prefectures.json",
            "", "UTF-8", null
        )
        println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★")
        val resultJson = result.body

        resultJson?.let {
            setupPrefCodeList(it)
        }
    }

    private fun setupPrefCodeList(prefCodeJson: String) {
        val adapter = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item)
        var prefCodeList: PrefCode? = null

        if (prefCodeJson.isNotBlank()) {
            prefCodeList = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
                .adapter(PrefCode::class.java)
                .fromJson(prefCodeJson)
        }
        if (null == prefCodeList?.prefCode) {
            adapter.add("test1")
            adapter.add("test2")
            adapter.add("test3")
            adapter.add("test4")
        }else{
            prefCodeList.prefCode?.forEach {
                adapter.add(it.name)
            }
        }
        spinner_prefecture.adapter = adapter
    }

    inner class MyAsyncTask: AsyncTask<Void, Void, String?>() {
        override fun doInBackground(vararg params: Void?): String? {
            val response = "/prefecture.json".httpGet().response()
            return String(response.second.data)



            /*
            val response = HttpUtil().post("http://lycee-project.net/prefectures.json",
                "", "UTF-8", null)
            return response.body
            */
        }

        override fun onPostExecute(result: String?) {
            result?.let {
                println("★★★★★★★★★★★★result: $result ★★★★★★★★★★★★★★★★★★★★★★★★")
                setupPrefCodeList(it)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupFuel()
        MyAsyncTask().execute()

        // button event [Plain Text]
        clickListener(imageButton_editText,
            "Plain Text",
            editText_inputText.text::toString
        )

        // button event [Password]
        clickListener(imageButton_password,
            "Password",
            editText_password.text::toString
        )

        // button event [Date]
        clickListener(imageButton_date,
            "Date",
            editText_date.text::toString
        )

        // button event [Radio Buttons]
        clickListener(imageButton_radio,
            "Radio Buttons",
            fun (): String {
                return listOf(radioBtn_japanese, radioBtn_french, radioBtn_chinese)
                    .map {
                        String.format("%s : %s\n", it.text, it.isChecked)
                    }
                    .stream()
                    .collect(Collectors.joining())
            }
        )

        // button event [Spinner]
        clickListener(imageButton_prefecture,
            "Spinner",
            fun(): String {
                return spinner_prefecture.selectedItem.toString()
            }
        )

        val _this = this
        spinner_prefecture.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(_this, "Selected Item : " + spinner_prefecture.selectedItem.toString(), Toast.LENGTH_SHORT).show()
            }

        }

        // button event [Seek Bar]
        clickListener(imageButton_seek,
            "Seek Bar",
            fun(): String {
                return String.format("volume: %d", seekBar_volume.progress)
            }
        )

        // button event [Toggle Button]
        clickListener(imageButton_toggle,
            "Toggle Button",
            fun(): String {
                return String.format("state: %s", tglBtn_state.isChecked)
            }
        )

        // button event [Switch]
        clickListener(imageButton_switch,
            "Toggle Button",
            fun(): String {
                return String.format("state: %s", switch_state.isChecked)
            }
        )

        // button event [Multiline Text]
        clickListener(imageButton_multiline,
            "Multiline Text",
            editText_multiline.text::toString
        )

        // button event [CHECK]
        clickListener(pushBtn_check,
            "CHECK",
            fun(): String {
                return "pushed [CHECK] Button!"
            }
        )

    }
}
