package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_layout.*
import java.util.stream.Collectors

class MainActivity : AppCompatActivity() {


    fun showDialog(title: String, msg: String) {
        val dialog = NoticeDialogFragment()
        dialog.title = title
        dialog.msg = msg
        dialog.show(supportFragmentManager, "MainActivity")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // button event [Plain Text]
        imageButton_editText
            .setOnClickListener{
                showDialog("Pain Text",
                    editText_inputText.text.toString())
            }

        // button event [Password]
        imageButton_password
            .setOnClickListener{
                showDialog("Password",
                    editText_password.text.toString())
            }

        // button event [Date]
        imageButton_date
            .setOnClickListener{
                showDialog("Date",
                    editText_date.text.toString())
            }

        // button event [Radio Buttons]
        imageButton_radio
            .setOnClickListener{
                showDialog("Radio Buttons",
                    listOf(radioBtn_japanese, radioBtn_french, radioBtn_chinese)
                        .map {
                            String.format("%s : %s\n", it.text, it.isChecked)
                        }
                        .stream()
                        .collect(Collectors.joining())
                )
            }

        // button event [Seek Bar]
        imageButton_seek
            .setOnClickListener{
                showDialog("Seek Bar",
                    String.format("volume: %d", seekBar_volume.progress)
                )
            }

        // button event [Toggle Button]
        imageButton_toggle
            .setOnClickListener{
                showDialog("Toggle Button",
                    String.format("state: %s", tglBtn_state.isChecked)
                )
            }

        // button event [Switch]
        imageButton_switch
            .setOnClickListener{
                showDialog("Switch",
                    String.format("state: %s", switch_state.isChecked)
                )
            }

        // button event [Multiline Text]
        imageButton_multiline
            .setOnClickListener{
                showDialog("Multiline Text",
                    editText_multiline.text.toString()
                )
            }

        // button event [CHECK]
        pushBtn_check
            .setOnClickListener{
                showDialog("CHECK",
                    "pushed [CHECK] Button!"
                )
            }

    }
}
