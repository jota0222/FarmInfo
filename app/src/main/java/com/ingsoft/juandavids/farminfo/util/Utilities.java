package com.ingsoft.juandavids.farminfo.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Creado por Juan David Hernández el 1/05/2017.
 */

public class Utilities {

    public static boolean hasConection(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = manager.getActiveNetworkInfo();
        boolean state;

        state = netInfo != null && netInfo.isAvailable() && netInfo.isConnected();

        return state;
    }

    public static void showAlert(Context context, String message) {
        Toast alert = Toast.makeText(context, message, Toast.LENGTH_LONG);
        alert.show();
    }

    public static File createFileFromInputStream(Context context, InputStream inputStream, String path) {

        try {
            File f = new File(path);
            OutputStream outputStream = new FileOutputStream(f);
            byte buffer[] = new byte[1024];
            int length;

            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            outputStream.close();
            inputStream.close();

            return f;
        } catch (IOException e) {
            showAlert(context, e.getMessage());
        }

        return null;
    }
}
