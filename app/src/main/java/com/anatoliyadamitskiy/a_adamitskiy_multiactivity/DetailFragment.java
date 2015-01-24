package com.anatoliyadamitskiy.a_adamitskiy_multiactivity;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;

interface DetailInterface {
    public String[] setLabels();
    public void removeEmployee(String position);
}

public class DetailFragment extends Fragment {

    public static final String TAG = "DetailFragment.TAG";
    DetailInterface detailInterface;
    TextView nameView;
    TextView numberView;
    TextView positionView;
    Button removeButton;
    Button intentButton;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        detailInterface = (DetailInterface)getActivity();
        nameView = (TextView)getView().findViewById(R.id.nameLabel);
        numberView = (TextView)getView().findViewById(R.id.numberLabel);
        positionView = (TextView)getView().findViewById(R.id.positionLabel);
        removeButton = (Button)getView().findViewById(R.id.removeButton);
        intentButton = (Button)getView().findViewById(R.id.intentButton);

        nameView.setText(detailInterface.setLabels()[0]);
        numberView.setText(detailInterface.setLabels()[1]);
        positionView.setText(detailInterface.setLabels()[2]);

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailInterface.removeEmployee(detailInterface.setLabels()[3]);
            }
        });

        intentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, nameView.getText());
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "Share data with..."));
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

    }

}
