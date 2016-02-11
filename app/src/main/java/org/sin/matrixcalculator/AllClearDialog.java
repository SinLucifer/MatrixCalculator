package org.sin.matrixcalculator;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.app.DialogFragment;
import android.app.AlertDialog;

/**
 * Created by Sin on 2016/2/11.
 */
public class AllClearDialog extends DialogFragment {
    private DialogInterface.OnClickListener onClickListener;

    public static AllClearDialog newInstance(DialogInterface.OnClickListener listener) {
        AllClearDialog fragment = new AllClearDialog(listener);
        return fragment;
    }

    public AllClearDialog(DialogInterface.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle("确认清除数据")
                .setPositiveButton(android.R.string.ok,onClickListener)
                .setNegativeButton(android.R.string.cancel, null)
                .create();
    }
}
