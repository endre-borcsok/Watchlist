package com.ebsoft.watchlist.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.ebsoft.watchlist.R;


public class DeleteDialog extends DialogFragment {

    public interface DeleteDialogListener {
        void onDialogPositiveClick(DialogFragment dialog);
    }

    private DeleteDialogListener mListener;

    public void setListener(DeleteDialogListener listener) {
        this.mListener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getString(R.string.delete_dialog_text))
                .setPositiveButton(getString(R.string.delete_dialog_positive), (dialog, id) -> {
                    if (mListener != null) {
                        mListener.onDialogPositiveClick(this);
                    }
                })
                .setNegativeButton(getString(R.string.delete_dialog_negative), (dialog, id) -> {
                    dismiss();
                });
        return builder.create();
    }
}
