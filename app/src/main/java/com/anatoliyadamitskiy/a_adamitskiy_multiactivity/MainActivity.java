package com.anatoliyadamitskiy.a_adamitskiy_multiactivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements EmployeeInterface {

    public static final int FORM_REQUESTCODE = 1;
    public static final int DETAIL_REQUESTCODE = 2;
    ArrayList<Person> employees = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            MainFragment mainFrag = new MainFragment();
            getFragmentManager().beginTransaction().replace(R.id.mainLayout, mainFrag, MainFragment.TAG).commit();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add) {

            Intent formActivity = new Intent(this, FormActivity.class);
            startActivityForResult(formActivity, FORM_REQUESTCODE);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void openPage(String activity, String name, String number, String position, int itemPos) {

        String itemPosition = itemPos + "";

        if (activity == "detail") {
            Intent detailActivity = new Intent(this, DetailActivity.class);
            detailActivity.putExtra("name",name);
            detailActivity.putExtra("number",number);
            detailActivity.putExtra("position",position);
            detailActivity.putExtra("itemPosition", itemPosition);
            startActivityForResult(detailActivity, DETAIL_REQUESTCODE);
        }
    }

    @Override
    public ArrayList<Person> getArrayList() {

        try {
            FileInputStream fin = openFileInput("Employees");
            ObjectInputStream oin = new ObjectInputStream(fin);
            employees = (ArrayList<Person>)oin.readObject();
            oin.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return employees;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FORM_REQUESTCODE && resultCode == RESULT_OK) {

            String name = data.getStringExtra("name");
            String number = data.getStringExtra("number");
            String position = data.getStringExtra("position");
            employees.add(new Person(name,number, position));

        } else if (requestCode == DETAIL_REQUESTCODE && resultCode == RESULT_OK) {

            employees.remove(Integer.parseInt(data.getStringExtra("itemtodelete")));
            Log.i("Number of new items", employees + "");

        }

        try {
            FileOutputStream fos = openFileOutput("Employees", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(employees);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
