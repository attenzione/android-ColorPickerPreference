package net.margaritov.preference.colorpicker;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

public class TestPreferenceFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.settings);

        final ColorPickerPreference color2 = findPreference("color2");
        color2.setOnPreferenceChangeListener((preference, newValue) -> {
            preference.setSummary(
                    ColorPickerPreference.convertToARGB(
                            Integer.parseInt(String.valueOf(newValue))));
            return true;
        });
        color2.setAlphaSliderEnabled(true);
    }
}