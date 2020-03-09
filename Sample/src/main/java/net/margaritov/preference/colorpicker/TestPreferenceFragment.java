package net.margaritov.preference.colorpicker;

import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

public class TestPreferenceFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.settings);

        findPreference("color2").setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                preference.setSummary(ColorPickerPreference.convertToARGB(Integer.valueOf(String.valueOf(newValue))));
                return true;
            }

        });
        ((ColorPickerPreference) findPreference("color2")).setAlphaSliderEnabled(true);
    }
}