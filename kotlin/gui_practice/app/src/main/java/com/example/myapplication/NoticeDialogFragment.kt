package com.example.myapplication

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment

class NoticeDialogFragment : DialogFragment() {

    var title: String = ""
    var msg: String = ""


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        return builder
            .setTitle(title)
            .setMessage(msg)
            .setPositiveButton("ok", null)
            .create()
    }

    override fun onPause() {
        super.onPause()

        dismiss()
    }

}
