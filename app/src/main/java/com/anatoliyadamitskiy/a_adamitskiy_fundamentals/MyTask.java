package com.anatoliyadamitskiy.a_adamitskiy_fundamentals;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Anatoliy on 1/12/15.
 */
public class MyTask extends AsyncTask<String,Void,String> {

    Context mContext;

    public void setContext(Context _context) {
        mContext = _context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected String doInBackground(String... params) {

        try {

            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.connect();
            InputStream is = connection.getInputStream();
            String data = IOUtils.toString(is);
            is.close();
            JSONObject mainObject = new JSONObject(data);
            JSONObject dataObject = mainObject.getJSONObject("data");
            JSONArray childArray = dataObject.getJSONArray("children");
            JSONObject firstObject = childArray.getJSONObject(0);
            JSONObject data1 = firstObject.getJSONObject("data");
            String title = data1.getString("title");
            return title;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "Error";
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

    }

    @Override
    protected void onCancelled() {
        super.onCancelled();

    }
}
