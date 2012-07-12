package net.erickelly.huskyhunters;

import net.erickelly.huskyhunters.services.DownloadService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class HuskyHuntersActivity extends FragmentActivity {
	private BroadcastReceiver receiver;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        IntentFilter filter = new IntentFilter(ResponseReceiver.ACTION_RESP);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        receiver = new ResponseReceiver();
        registerReceiver(receiver, filter);
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
}