package net.erickelly.huskyhunters;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class ClueListFragment extends ListFragment {
	private static String TAG = "ClueListFragment";
	private OnClueSelectedListener clueSelectedListener;

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO: Get the clue id of the selected view
	    clueSelectedListener.onClueSelected("");
	}
	 
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    /*setListAdapter(ArrayAdapter.createFromResource(getActivity()
	            .getApplicationContext(), R.array.tut_titles,
	            R.layout.list_item));*/
	}
	
	public interface OnClueSelectedListener {
		public void onClueSelected(String clueid);
	}
	
	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            clueSelectedListener = (OnClueSelectedListener) activity;
        } catch (ClassCastException e) {
            Log.e(TAG, "Bad class", e);
            throw new ClassCastException(activity.toString()
                    + " must implement OnClueSelectedListener");
        }
    }
	
}
