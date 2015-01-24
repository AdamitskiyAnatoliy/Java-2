package com.anatoliyadamitskiy.a_adamitskiy_multiactivity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;


public class FormActivity extends ActionBarActivity implements FormInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        FormFragment formFrag = new FormFragment();
        getFragmentManager().beginTransaction().replace(R.id.formLayout, formFrag, FormFragment.TAG).commit();

    }

    public void addEmployee(String name, String number, String position) {

        Intent intent = new Intent();
        intent.putExtra("name", name);
        intent.putExtra("number", number);
        intent.putExtra("position", position);
        setResult(RESULT_OK, intent);
        finish();

    }

}
