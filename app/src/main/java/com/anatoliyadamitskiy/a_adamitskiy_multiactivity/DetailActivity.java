package com.anatoliyadamitskiy.a_adamitskiy_multiactivity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


public class DetailActivity extends ActionBarActivity implements DetailInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        DetailFragment detailFrag = new DetailFragment();
        getFragmentManager().beginTransaction().replace(R.id.detailLayout, detailFrag, FormFragment.TAG).commit();

    }

    @Override
    public String[] setLabels() {

        Intent data = getIntent();

        String[] array = {data.getStringExtra("name"), data.getStringExtra("number"),
                data.getStringExtra("position"), data.getStringExtra("itemPosition")};

        return array;
    }

    @Override
    public void removeEmployee(String position) {

        Intent intent = new Intent();
        intent.putExtra("itemtodelete", position);
        setResult(RESULT_OK, intent);
        finish();

    }

}
