package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_layout.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pushBtn_check
            .setOnClickListener{
                val dialog = NoticeDialogFragment()
                dialog.show(supportFragmentManager, "NoticeDialogFragment")
            }

    }
}
