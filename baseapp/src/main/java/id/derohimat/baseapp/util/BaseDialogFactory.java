package id.derohimat.baseapp.util;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;

public class BaseDialogFactory {

    public static Dialog createSimpleOkErrorDialog(Context context, String title, String message) {
        return createOkErrorDialog(context, title, message, null);
    }

    public static Dialog createOkErrorDialog(Context context, String title, String message, Dialog.OnClickListener onClickListener) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setNeutralButton("Ok", onClickListener);
        return alertDialog.create();
    }

    public static ProgressDialog createProgressDialog(Context context, String message) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        return progressDialog;
    }

    public static ProgressDialog createProgressDialog(Context context,
                                                      @StringRes int messageResoruce) {
        return createProgressDialog(context, context.getString(messageResoruce));
    }

}
