package net.erickelly.huskyhunters.services;

import net.erickelly.huskyhunters.ClueListActivity.ResponseReceiver;

import org.json.JSONArray;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.util.Log;

/**
 * Downloads an array of all clues
 * @author eric
 */
public class DownloadService extends IntentService {
	
	public static final String PARAM_OUT_MESSAGE = "success";
	
	private static String TAG = "DownloadService";
	private static String PREFS_NAME = "Settings";
	private static String URL_KEY = "url";
	private static String GROUP_HASH = "grouphash";
	private static String DEFAULT_HASH = "49536405";
	private static String DEF_URL = "http://huskyhunter.roderic.us";
	private static String DOWNLOAD_INTERVAL = "download_interval";
	private static int DOWNLOAD_DEFAULT = 15;	
	
	/**
	 * Constructor
	 * @param name
	 */
	public DownloadService() {
		super(TAG);
	}
	
	/**
	 * Returns a url to fetch all clues for a specific team
	 * @return url
	 */
	protected String getCluesUrl() {
		SharedPreferences sp = getSharedPreferences(PREFS_NAME, 0);
		return sp.getString(URL_KEY, DEF_URL) + "/api/teams/" + 
			sp.getString(GROUP_HASH, DEFAULT_HASH) + "/clues";
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		boolean result = false;
		JSONArray clues = (new ClueDownloadHandler()).requestClues(getCluesUrl());
		if (clues != null) {
			
			// TODO: Do database stuff here
			
			result = true; 
		}
		
		// Let the application know that we're done downloading
		Intent broadcastIntent = new Intent();
		broadcastIntent.setAction(ResponseReceiver.ACTION_RESP);
		broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
		broadcastIntent.putExtra(PARAM_OUT_MESSAGE, result);
		sendBroadcast(broadcastIntent);
	    
	    // Will be triggered manually
	    // scheduleNextUpdate();
	}

	/**
	 * Starts the downloading again after X minutes (set in system preferences)
	 */
	@Deprecated
	protected void scheduleNextUpdate() {
		Intent intent = new Intent(this, this.getClass());
	    PendingIntent pendingIntent =
	        PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

	    // The update frequency should often be user configurable.
	    SharedPreferences sp = getSharedPreferences(PREFS_NAME, 0);
	    int download_interval = sp.getInt(DOWNLOAD_INTERVAL, DOWNLOAD_DEFAULT);
	    
	    long currentTimeMillis = System.currentTimeMillis();
	    long nextUpdateTimeMillis = currentTimeMillis + download_interval * DateUtils.MINUTE_IN_MILLIS;
	    Time nextUpdateTime = new Time();
	    nextUpdateTime.set(nextUpdateTimeMillis);

	    if (nextUpdateTime.hour < 8 || nextUpdateTime.hour >= 18) {
	    	nextUpdateTime.hour = 8;
	    	nextUpdateTime.minute = 0;
	    	nextUpdateTime.second = 0;
	    	nextUpdateTimeMillis = nextUpdateTime.toMillis(false) + DateUtils.DAY_IN_MILLIS;
	    }
	    
	    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
	    alarmManager.set(AlarmManager.RTC, nextUpdateTimeMillis, pendingIntent);
  	}	
}
