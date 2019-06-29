package com.example.myapplication

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment

class NoticeDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        return builder.setMessage("message")
            .setTitle("title")
            .setPositiveButton("ok", null)
            .create()
    }

    override fun onPause() {
        super.onPause()

        dismiss()
    }

}
