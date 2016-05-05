package com.myaccountant.myaccountant.helpers;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.parse.ParseUser;

/**
 * Created by user on 12/2/2015.
 */
public class Helper {
    private static ParseUser userForChat;


    private static AlertDialog.Builder builder;
    private static AlertDialog dialog;

    public static void showErrorDialog(String title, String message,Context context) {
        builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(android.R.string.ok, null);
        dialog = builder.create();
        dialog.show();
    }

    public static ParseUser getUserForChat() {
        return userForChat;
    }

    public static void setUserForChat(ParseUser userForChat) {
        Helper.userForChat = userForChat;
    }
}
