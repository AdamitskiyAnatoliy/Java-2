package com.anatoliyadamitskiy.a_adamitskiy_fundamentals;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Anatoliy on 1/15/15.
 */
public class NetworkAndStorage {

    Context mContext;

    public NetworkAndStorage (Context con) {
        mContext = con;
    }

    public Boolean checkNetwork() {
        ConnectivityManager manager = (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info != null) {

                if (info.isConnected()) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public void storeObject(String sub, String _title) throws IOException {

        FileOutputStream fos = mContext.openFileOutput(sub, Context.MODE_PRIVATE);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(_title);
        os.close();
        fos.close();

    }

    public String loadObject(String sub) throws IOException, ClassNotFoundException {

        FileInputStream fis = mContext.openFileInput(sub);
        ObjectInputStream is = new ObjectInputStream(fis);
        String titleString = (String) is.readObject();
        is.close();
        fis.close();
        return titleString;

    }

}
