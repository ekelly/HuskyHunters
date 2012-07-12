package net.erickelly.huskyhunters;

import net.erickelly.huskyhunters.R;
import net.erickelly.huskyhunters.R.xml;
import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Settings extends PreferenceActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
	}	
}