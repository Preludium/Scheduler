package pl.jansmi.scheduler.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DeletePromptDialog extends DialogFragment {

    public static void show(Context context, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirm delete");
        builder.setMessage("Are you sure you want to delete this record?");
        builder.setPositiveButton("Yes", listener);
        builder.setNegativeButton("No", null);
        builder.create().show();
    }

}
