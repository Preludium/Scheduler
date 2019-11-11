package pl.jansmi.scheduler.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.fragment.app.DialogFragment;

public class DeleteAccountPromptDialog extends DialogFragment {

    public static void show(Context context, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Confirm delete");
        builder.setMessage("Are you sure you want to delete your account?");
        builder.setPositiveButton("Yes", listener);
        builder.setNegativeButton("No", null);
        builder.create().show();
    }
}
