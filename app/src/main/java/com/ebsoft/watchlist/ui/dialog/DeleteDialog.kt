package com.ebsoft.watchlist.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog

import com.ebsoft.watchlist.R


class DeleteDialog : DialogFragment() {

    private var mListener: DeleteDialogListener? = null

    interface DeleteDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment)
    }

    fun setListener(listener: DeleteDialogListener) {
        this.mListener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity!!)
        builder.setMessage(getString(R.string.delete_dialog_text))
                .setPositiveButton(getString(R.string.delete_dialog_positive)) { _, _ ->
                    if (mListener != null) {
                        mListener!!.onDialogPositiveClick(this)
                    }
                }
                .setNegativeButton(getString(R.string.delete_dialog_negative)) { _, _ -> dismiss() }
        return builder.create()
    }
}
