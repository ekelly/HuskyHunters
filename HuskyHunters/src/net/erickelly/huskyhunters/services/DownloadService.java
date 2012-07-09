package net.erickelly.huskyhunters.services;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

import net.erickelly.huskyhunters.data.Clue;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class DownloadService extends Service {
	
	private static String PREFS_NAME = "Settings";
	private static String URL_KEY = "url";
	private static String DEF_URL = "http://huskyhunter.roderic.us";
	private static String TAG = "DownloadService";
	
	class DownloadCluesTask extends AsyncTask<String, Integer, Void>{
		
		private String getCluesUrl(String grouphash) {
			String grouphash1 = "49536405";
			return getSharedPreferences(PREFS_NAME, 0)
				.getString(URL_KEY, DEF_URL) + "/api/teams/" + grouphash1 + "/clues";
		}
		
		/**
		 * Get clues from the server
		 * @param groupHash The hash indicating which group's clues to download
		 * @return JSON representation of the data in the form of a string
		 * @author eric
		 */
		private String requestClues(String groupHash) {
			StringBuilder builder = new StringBuilder();
			
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(getCluesUrl(groupHash));
			
			try {
				HttpResponse response = client.execute(request);

				StatusLine statusLine = response.getStatusLine();
				int statusCode = statusLine.getStatusCode();
				
				if (statusCode == 200) {
					HttpEntity entity = response.getEntity();
					InputStream content = entity.getContent();
					BufferedReader reader = new BufferedReader(new InputStreamReader(content));
					String line;
					
					while ((line = reader.readLine()) != null) {
						builder.append(line);
					}
				} else {
					Log.e(TAG, "Failed to download file");
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return builder.toString();
		}
		
		/**
		 * Parse JSON representation of clues and return an ArrayList of clues
		 * @param data JSON representation of the clues
		 * @return ArrayList<Clue>
		 * @author eric
		 */
		private void parseClues(String data) {
			try {
				JSONArray jsonArray = new JSONArray(data);
				Log.i(TAG, "Number of entries " + jsonArray.length());
				
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject obj = jsonArray.getJSONObject(i);
					
					/*String clueid = obj.getString("clueid");*/
					String clueid = ((Integer) obj.getInt("number")).toString();
					String answer = obj.getString("answer");
					/*String originalClue = obj.getString("clue");*/
					String originalClue = obj.getString("hint");
					Integer points = obj.getInt("points");
					/*String solved = obj.getString("solved");*/
					JSONArray ll = obj.getJSONArray("latlng");
					Double[] latlng = { 0.0, 0.0 };
					if(ll.length() == 2) {
						latlng[0] = (Double) ll.get(0);
						latlng[1] = (Double) ll.get(1);
					}
					
					String solved = "unsolved";
					
					// 
					
					Log.i(TAG, originalClue);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return clueList;
		}

		@Override
		protected Void doInBackground(String... params) {
			String groupHash = params[0];
			parseClues(requestClues(groupHash));
			// Get data from the server
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// Shouldn't have to do anything to update the list because the CursorLoader should update
			// Toast that we're done downloading!
		}
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
    public int onStartCommand(Intent intent, int flags, int startId) {
		Bundle extras = intent.getExtras();
		String GROUP_HASH = extras.getString("grouphash");
		new DownloadCluesTask().execute(GROUP_HASH);
        return Service.START_FLAG_REDELIVERY;
    }

}
