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
	
	public static final String CLUEID = "clueid";
	
	
	public static final String TIME = "time";
	
	// server data table - for data downloaded from server
	public static final String SERVER_TABLE = "server_table";
	public static final String DOWNLOADED_TABLE = "download_table";
	public static final String SERVER_TEXT = "cluetext";
	public static final String SERVER_ANS = "clueanswer";
	public static final String SERVER_POINTS = "points";
	public static final String SERVER_PICS_REQD = "picsreqd";
	public static final String SERVER_PICS_ON_SERVER = "server_pics";
	
	
	// device data table - NOT an actual table, used as virtual table for query
	public static final String DEVICE_TABLE = "device_table";
	public static final String DEVICE_PICS = "device_pics";
	public static final String TOTAL_PICS = "total_pics";
	
	// photo table
	public static final String PHOTO_TABLE = "photo_table";
	public static final String PHOTO_PICURI = "picuri";
	public static final String PHOTO_CRTIME = "crtime";
	
	
	
	
	// table creation strings
	public static final String SQL_PHOTO_TABLE_CREATION = 
			"CREATE TABLE " + PHOTO_TABLE + " (" + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ CLUEID + " TEXT NOT NULL, " + PHOTO_PICURI + " TEXT NOT NULL, " + PHOTO_CRTIME + " INTEGER NOT NULL);";
	
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
		sb.append(CLUEID);
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
	
	// Drop server table
	public static final String SQL_DROP_SERVER_TABLE = "DROP TABLE IF EXISTS " + SERVER_TABLE;
	
	// Rename downloaded table to server table
	public static final String SQL_RENAME_DOWNLOADED_TO_SERVER = "ALTER TABLE " + DOWNLOADED_TABLE
			+ " RENAME TO " + SERVER_TABLE;
	
	// Clear database, drop all tables
	public static final String SQL_DROP_ALL_TABLES = SQL_DROP_SERVER_TABLE
			+ "; DROP TABLE IF EXISTS " + DOWNLOADED_TABLE
			+ "; DROP TABLE IF EXISTS " + PHOTO_TABLE + ";";
	
	// Tables portion of query
	public static final String SQL_QUERY_TABLES = SERVER_TABLE + " LEFT JOIN " +
			"(SELECT " + CLUEID + ", COUNT(" + CLUEID + ") AS " + DEVICE_PICS + " FROM " + 
			PHOTO_TABLE + " GROUP BY " + CLUEID + ") " +
			"AS " + DEVICE_TABLE + " WHERE " + SERVER_TABLE + "." + CLUEID + " = " + 
			DEVICE_TABLE + "." + CLUEID + " GROUP BY " + SERVER_TABLE + "." + CLUEID;
	
	// Sum portion of SELECT statement
	public static final String SQL_SELECT_SUM = "SUM(device_pics + " + SERVER_TABLE + "." + SERVER_PICS_ON_SERVER
			+ ") as " + TOTAL_PICS;
	
	// SELECT portion of query - includes sum
	public static final String[] SQL_SELECT = new String[]{BaseColumns._ID, SERVER_TABLE + "." + CLUEID, SERVER_TEXT, SERVER_ANS,
		SERVER_POINTS, SERVER_PICS_REQD, SERVER_PICS_ON_SERVER, SQL_SELECT_SUM};
}
