package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
