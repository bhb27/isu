/*
 * Copyright (C) Felipe de Leon <fglfgl27@gmail.com>
 *
 * This file is part of iSu.
 *
 * iSu is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * iSu is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with iSu.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.bhb27.isu;

import android.os.Bundle;

import android.support.v7.preference.Preference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.PreferenceCategory;
import android.support.v7.preference.Preference.OnPreferenceChangeListener;
import android.support.v14.preference.PreferenceFragment;
import android.support.v14.preference.SwitchPreference;

import com.bhb27.isu.tools.Constants;
import com.bhb27.isu.tools.RootUtils;
import com.bhb27.isu.tools.Tools;

public class Settings extends PreferenceFragment implements
Preference.OnPreferenceChangeListener {

    private String suVersion;
    private boolean isCMSU;
    private Preference mSettingsView;
    private PreferenceCategory mSettingsPref, mSettingsSU, mSettingsNotifications, mSettingsSelinux, mSettingsDebug;
    private ListPreference mApplySuDelay;
    private SwitchPreference mSettingsForceEnglish;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        getPreferenceManager().setSharedPreferencesName(Constants.PREF_NAME);
        addPreferencesFromResource(R.xml.settings);

        mSettingsPref = (PreferenceCategory) findPreference("settings_pref");
        mSettingsView = (Preference) findPreference("settings_view");
        mSettingsForceEnglish = (SwitchPreference) findPreference("forceenglish");
        mSettingsForceEnglish.setOnPreferenceChangeListener(this);
        update();

        mSettingsSU = (PreferenceCategory) findPreference("su_settings_pref");
        mSettingsNotifications = (PreferenceCategory) findPreference("notifications_settings_pref");
        mSettingsSelinux = (PreferenceCategory) findPreference("selinux_settings_pref");
        mSettingsDebug = (PreferenceCategory) findPreference("anddebug_settings_pref");

        mApplySuDelay = (ListPreference) findPreference("apply_su_delay");

        CharSequence[] entries = new CharSequence[6];
        CharSequence[] entryValues = new CharSequence[6];
        for (int i = 0; i < 6; i++) {
            entries[i] = (String.format(getString(R.string.apply_su_delay_summary), ((i + 1) * 10)));
            entryValues[i] = String.valueOf((i + 1) * 10000);
        }
        mApplySuDelay.setEntries(entries);
        mApplySuDelay.setEntryValues(entryValues);

        suVersion = Tools.SuVersion(getActivity());
        isCMSU = Tools.SuVersionBool(suVersion);

        if (!isCMSU) {
            mSettingsSU.setEnabled(false);
            mSettingsNotifications.setEnabled(false);
            mSettingsSelinux.setEnabled(false);
            mSettingsDebug.setEnabled(false);
        } else
            mSettingsPref.removePreference(mSettingsView);
    }

    public void onResume() {
        super.onResume();
        update();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object objValue) {
        if (preference == mSettingsForceEnglish) {
            Tools.updateMain(getActivity(), (String.format(getString(R.string.reloading), getString(R.string.language))));
        }
        return true;
    }

    @SuppressWarnings("deprecation")
    public void update() {
        if (Tools.sysLocale().startsWith("en"))
            mSettingsForceEnglish.setEnabled(false);
    }
}
