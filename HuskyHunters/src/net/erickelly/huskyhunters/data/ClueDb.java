package net.erickelly.huskyhunters.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ClueDb extends SQLiteOpenHelper {
	public static final String TAG = "ClueDbAdapter";
	
	/**
	 * Creates a ClueDb instance.
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 */
	public ClueDb(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Creates a ClueDb instance from a given context.
	 * @param context the context to use.
	 */
	public ClueDb(Context context) {
		super(context, DbConstants.DB_NAME, null, DbConstants.DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
