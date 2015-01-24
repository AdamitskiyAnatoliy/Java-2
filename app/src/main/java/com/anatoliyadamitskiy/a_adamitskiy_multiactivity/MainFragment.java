package com.anatoliyadamitskiy.a_adamitskiy_multiactivity;


import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

interface EmployeeInterface {
    public void openPage(String activity,String name, String number, String position, int itemPos);
    public ArrayList<Person> getArrayList();
}

public class MainFragment extends ListFragment {

    public static final String TAG = "MainFragment.TAG";
    ArrayList<Person> employees = new ArrayList();
    EmployeeInterface employeeInterface;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            employeeInterface = (EmployeeInterface) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement EmployeeInterface.");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        ArrayList<Person> array = employeeInterface.getArrayList();
        employees = array;

        MyAdapter myAdapter = new MyAdapter(getActivity(),employees);
        setListAdapter(myAdapter);
        Log.i("Number of items", employees.size() + "");
        myAdapter.notifyDataSetChanged();

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        employeeInterface.openPage("detail",employees.get(position).getName(),
                employees.get(position).getNumber(), employees.get(position).getPosition(), position);
    }

}
