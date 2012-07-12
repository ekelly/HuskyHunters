package net.erickelly.huskyhunters;

import net.erickelly.huskyhunters.services.DownloadService;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

/**
 * Essentially a wrapper for ClueListFragment
 * Adds support for handling the system-wide menu bar?
 * @author eric
 */
public class ClueListActivity extends Activity implements ClueListFragment.OnClueSelectedListener {
	private BroadcastReceiver receiver;
	
	public static String CLUEID = "clue_id";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.cluelist_fragment);
        
        // Register a response handler for the clue downloader
        IntentFilter filter = new IntentFilter(ResponseReceiver.ACTION_RESP);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        receiver = new ResponseReceiver();
        registerReceiver(receiver, filter);
    }
    
    /**
     * Fetches clues from the server by starting the download service
     */
    protected void fetchCluesFromServer() {
    	startService(new Intent(this, DownloadService.class));
    }
    
    /**
     * Takes action based on success/failure of download service
     * @author eric
     */
    public class ResponseReceiver extends BroadcastReceiver {
    	public static final String ACTION_RESP = 
    		"net.erickelly.huskyhunters.intent.action.MESSAGE_PROCESSED";

    	@Override
    	public void onReceive(Context context, Intent intent) {
    		Boolean success = intent.getBooleanExtra(DownloadService.PARAM_OUT_MESSAGE, false);
    	}

    }

	@Override
	public void onClueSelected(String clueid) {
		Intent showContent = new Intent(getApplicationContext(),
	            ClueDetailActivity.class);
	    showContent.getExtras().putString(CLUEID, clueid);
	    startActivity(showContent);
	}
}