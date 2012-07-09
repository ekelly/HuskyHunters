package net.erickelly.huskyhunters.data;

import android.provider.BaseColumns;

/**
 * 
 * @author francis
 *
 */
public final class DbConstants {
	public static final String DB_NAME = "cluedata";
	public static final int DB_VERSION = 1;
	
	public static final String ROWID = "_id";
	public static final String TIME = "time";
	
	// server data table
	public static final String SERVER_TABLE = "serverTable";
	//public static final String SERVER_ROWID = "_id";
	public static final String SERVER_CLUEID = "clueid";
	public static final String SERVER_TEXT = "cluetext";
	public static final String SERVER_ANS = "clueanswer";
	public static final String SERVER_POINTS = "points";
	public static final String SERVER_PICS_REQD = "picsreqd";
	public static final String SERVER_PICS_ON_SERVER = "picsonserver";
	
	
	// device data table
	public static final String DEVICE_TABLE = "deviceTable";
	//public static final String Device_ROWID = "_id";
	public static final String DEVICE_CLUEID = "clueid";
	public static final String DEVICE_PICS_ON_DEVICE = "picsondevice";
	
	// photo table
	public static final String PHOTO_TABLE = "photoTable";
	public static final String PHOTO_PICURI = "picUri";
	public static final String PHOTO_CRTIME = "crtime";
	
	
	
	
	// table creation strings
	public static final String SQL_DEVICE_TABLE_CREATION =
			"CREATE TABLE " + DEVICE_TABLE + " (" + ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ DEVICE_CLUEID + " TEXT NOT NULL UNIQUE, " + DEVICE_PICS_ON_DEVICE + " INTEGER NOT NULL);";
	public static final String SQL_PHOTO_TABLE_CREATION = 
			"CREATE TABLE " + PHOTO_TABLE + " (" + ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ PHOTO_PICURI + " TEXT NOT NULL, " + PHOTO_CRTIME + " INTEGER NOT NULL);";
	
	// table creation string for server data - is method that takes name of table
	// to allow for temporary table
	/**
	 * Table creation string for server data.  Takes name of table to provide string
	 * for creation of temporary table for storing downloaded data.
	 * @param tableName
	 * @return a SQL command to create a table of the given name.
	 */
	public static final String SQL_SERVER_TABLE_CREATION(String tableName)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE ");
		sb.append(tableName);
		sb.append(" (");
		sb.append(BaseColumns._ID);
		sb.append(" integer primary key autoincrement, ");
		sb.append(SERVER_CLUEID);
		sb.append(" text not null, ");
		sb.append(SERVER_TEXT);
		sb.append(" text not null, ");
		sb.append(SERVER_ANS);
		sb.append(" text not null, ");
		sb.append(SERVER_POINTS);
		sb.append(" integer not null, ");
		sb.append(SERVER_PICS_REQD);
		sb.append(" integer not null, ");
		sb.append(SERVER_PICS_ON_SERVER);
		sb.append(" integer not null);");
		return sb.toString();
	}
	
	// Clear database
	
	
	
}
