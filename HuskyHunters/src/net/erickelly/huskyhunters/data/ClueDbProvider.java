package net.erickelly.huskyhunters.data;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

public class ClueDbProvider extends ContentProvider {
	
	private ClueDbAdapter mDbAdapter;
	
	// URI
	private static final String AUTHORITY = "net.erickelly.huskyhunters.data.ClueDbProvider";
	public static final int CLUES = 100;
	public static final int CLUE_ID = 110;
	 
	private static final String CLUES_BASE_PATH = "clues";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
	        + "/" + CLUES_BASE_PATH);
	 
	public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
	        + "/vnd.erickelly.clue";
	public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
	        + "/vnd.erickelly.clue";
	
	private static final UriMatcher sURIMatcher = new UriMatcher(
	        UriMatcher.NO_MATCH);
	static {
	    sURIMatcher.addURI(AUTHORITY, CLUES_BASE_PATH, CLUES);
	    sURIMatcher.addURI(AUTHORITY, CLUES_BASE_PATH + "/#", CLUE_ID);
	}
	
	
	private class ClueDbAdapter extends SQLiteOpenHelper {
		public static final String TAG = "ClueDbAdapter";
		
		/**
		 * Creates a ClueDbAdapter instance.
		 * @param context
		 * @param name
		 * @param factory
		 * @param version
		 */
		public ClueDbAdapter(Context context, String name, CursorFactory factory,
				int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}
		
		/**
		 * Creates a ClueDbAdapter instance from a given context.
		 * @param context the context to use.	
		 */
		public ClueDbAdapter(Context context) {
			super(context, DbConstants.DB_NAME, null, DbConstants.DB_VERSION);
		}
		
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DbConstants.SQL_SERVER_TABLE_CREATION(DbConstants.SERVER_TABLE));
			db.execSQL(DbConstants.SQL_DEVICE_TABLE_CREATION);
			db.execSQL(DbConstants.SQL_PHOTO_TABLE_CREATION);
		}

		/**
		 * Handles database upgrades.  On upgrade, deletes all clue data.
		 */
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to " 
					+ newVersion + ", which will destroy all data.");
			clear(db);
		}
		
		/**
		 * Replaces the server table with the downloaded table in the database.
		 * @param db
		 */
		public void replaceServerTable(SQLiteDatabase db)
		{
			db.execSQL(DbConstants.SQL_DROP_SERVER_TABLE);
			db.execSQL(DbConstants.SQL_RENAME_DOWNLOADED_TO_SERVER);
		}
		
		/**
		 * Clears and recreates the tables for the database.
		 * @param db The database to clear.
		 */
		private void clear(SQLiteDatabase db) {
			db.execSQL(DbConstants.SQL_DROP_ALL_TABLES);
			onCreate(db);
		}
	}


	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean onCreate() {
		mDbAdapter = new ClueDbAdapter(getContext());
		return true;
	}


	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteDatabase db = mDbAdapter.getReadableDatabase();
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		
		
		
		String query = DbConstants.SQL_SELECT;
		if (true) {
			
		}
		db.rawQuery(query, selectionArgs);
		
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
