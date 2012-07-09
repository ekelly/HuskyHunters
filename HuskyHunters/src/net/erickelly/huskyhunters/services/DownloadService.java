package net.erickelly.huskyhunters.services;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;

public class DownloadService extends Service {
	
	class DownloadCluesTask extends AsyncTask<String, Integer, Void>{

		@Override
		protected Void doInBackground(String... params) {
			String groupHash = params[0];
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
