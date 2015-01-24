package com.anatoliyadamitskiy.a_adamitskiy_multiactivity;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

interface FormInterface {
    public void addEmployee(String name, String number, String position);
}

public class FormFragment extends Fragment {

    public static final String TAG = "FormFragment.TAG";
    private static final String NAME = "Name";

    EditText nameField;
    EditText numberField;
    EditText positionField;
    Button submitButton;
    FormInterface formInterface;

    public FormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_form, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        formInterface = (FormInterface)getActivity();
        nameField = (EditText)getView().findViewById(R.id.nameField);
        numberField = (EditText)getView().findViewById(R.id.numberField);
        positionField = (EditText)getView().findViewById(R.id.positionField);
        submitButton = (Button)getView().findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formInterface.addEmployee(nameField.getText().toString(), numberField.getText().toString(),
                        positionField.getText().toString());
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Intent returnIntent = new Intent();
        returnIntent.putExtra(NAME, nameField.getText().toString());

    }

}
