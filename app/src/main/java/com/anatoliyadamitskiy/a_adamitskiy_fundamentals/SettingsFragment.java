package com.anatoliyadamitskiy.a_adamitskiy_fundamentals;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends PreferenceFragment {

    public static final String TAG = "SettingsFragment.TAG";

    Context mContext;
    RedditInterface redditInterface;

    public void setContext(Context _context) {
        mContext = _context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            redditInterface = (RedditInterface) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement RedditInterface.");
        }
    }

    @Override
    public void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);

        addPreferencesFromResource(R.xml.settings);
        Preference pref = findPreference("PREF_SWITCH");

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        PreferenceManager.getDefaultSharedPreferences(getActivity());

    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {

        redditInterface.clearCache();

        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }
}