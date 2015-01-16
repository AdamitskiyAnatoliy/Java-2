package com.anatoliyadamitskiy.a_adamitskiy_fundamentals;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
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
import java.util.ArrayList;


public class MainActivity extends Activity implements RedditInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyTask myTask = new MyTask();
        myTask.setContext(this);
        SettingsFragment settingsFrag = new SettingsFragment();
        settingsFrag.setContext(this);
        FragmentManager mgr = getFragmentManager();
        NetworkAndStorage netStore = new NetworkAndStorage(this);
        MasterFragment masterFrag = new MasterFragment();
        DetailFragment detailFrag = new DetailFragment();

        if (netStore.checkNetwork()) {
                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                        mgr.beginTransaction().replace(R.id.listContainer, masterFrag, MasterFragment.TAG).commit();
                        mgr.beginTransaction().add(R.id.detailContainer, detailFrag, DetailFragment.TAG).commit();
                        if (mgr.findFragmentByTag(DetailFragment.TAG) == null) {

                        } else {
                            mgr.beginTransaction().remove(mgr.findFragmentByTag(DetailFragment.TAG)).commit();
                        }

                    } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        mgr.beginTransaction().add(R.id.detailContainer, detailFrag, DetailFragment.TAG).commit();
                    }

        } else {

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("No Network");
            alert.setMessage("Please Enable Network Connection");
            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alert.show();

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                mgr.beginTransaction().replace(R.id.listContainer, masterFrag, MasterFragment.TAG).commit();
                mgr.beginTransaction().add(R.id.detailContainer, detailFrag, DetailFragment.TAG).commit();
                if (mgr.findFragmentByTag(DetailFragment.TAG) == null) {

                } else {
                    mgr.beginTransaction().remove(mgr.findFragmentByTag(DetailFragment.TAG)).commit();
                }

            } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                mgr.beginTransaction().add(R.id.detailContainer, detailFrag, DetailFragment.TAG).commit();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            MasterFragment masterFrag = new MasterFragment();
            DetailFragment detailFrag = new DetailFragment();
            getFragmentManager().beginTransaction().replace(R.id.listContainer, masterFrag, MasterFragment.TAG).commit();
            getFragmentManager().beginTransaction().add(R.id.detailContainer, detailFrag, DetailFragment.TAG).commit();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            SettingsFragment settingsFrag = new SettingsFragment();
            DetailFragment detailFrag = (DetailFragment)getFragmentManager().findFragmentByTag(DetailFragment.TAG);
            getFragmentManager().beginTransaction().replace(R.id.listContainer, settingsFrag, SettingsFragment.TAG).commit();
            getFragmentManager().beginTransaction().remove(detailFrag).commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void changeTitle(String _title) {
        TextView title = (TextView) findViewById(R.id.articleTitle);
        title.setText(_title);
    }


    @Override
    public void clearCache() {
        Toast.makeText(this, "Cache has been cleared.", Toast.LENGTH_LONG).show();

        File cache = getCacheDir();
        File dir = new File(cache.getParent());
        if (dir.exists()) {
            String[] children = dir.list();
            for (String s : children) {
                if (!s.equals("lib")) {
                    deleteDir(new File(dir, s));
                }
            }
        }
    }

    public boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        return dir.delete();
    }
}
