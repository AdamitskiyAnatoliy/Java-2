package com.anatoliyadamitskiy.a_adamitskiy_fundamentals;


import android.app.Activity;
import android.app.ListFragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 */

interface RedditInterface {

    public void changeTitle(String _title);
    public void clearCache();

}

public class MasterFragment extends Fragment {

    public static final String TAG = "MasterFragment.TAG";

    RedditInterface actionCallback;
    EditText searchBar;
    Button searchButton;

    public MasterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            actionCallback = (RedditInterface) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement RedditInterface.");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        return inflater.inflate(R.layout.fragment_master, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NetworkAndStorage checkNetwork = new NetworkAndStorage(getActivity());
        searchBar = (EditText) getView().findViewById(R.id.searchBar);
        searchButton = (Button) getView().findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkNetwork.checkNetwork()) {

                    try {
                        String output = new MyTask().execute("http://api.reddit.com/r/" + searchBar.getText().toString()).get();
                        actionCallback.changeTitle(output);

                        NetworkAndStorage netStore = new NetworkAndStorage(getActivity());
                        netStore.storeObject(searchBar.getText().toString(), output);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {

                    NetworkAndStorage netStore = new NetworkAndStorage(getActivity());
                    try {
                        actionCallback.changeTitle(netStore.loadObject(searchBar.getText().toString()));
                    } catch (IOException e) {
                        Toast.makeText(getActivity(), "Article Not Found", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        Toast.makeText(getActivity(), "Article Not Found", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }

                }

            }
        });
    }

}
