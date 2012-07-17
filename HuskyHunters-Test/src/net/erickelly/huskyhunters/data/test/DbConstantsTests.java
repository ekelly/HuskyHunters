package net.erickelly.huskyhunters.data.test;

import junit.framework.Assert;
import junit.framework.TestCase;
import net.erickelly.huskyhunters.data.*;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

public class DbConstantsTests extends TestCase {
	
	public void testDbServerTableCreationString() {
		String SqlServerTestCreationResult = "CREATE TABLE test (_id integer primary key autoincrement, clueid text not null, cluetext text not null, clueanswer text not null, points integer not null, picsreqd integer not null, picsonserver integer not null);";
		Assert.assertEquals(DbConstants.SQL_SERVER_TABLE_CREATION("test"), SqlServerTestCreationResult);
	}
	
	public void testDropTableSql() {
		String SqlDropTableResult = "DROP TABLE IF EXISTS server_table; DROP TABLE IF EXISTS device_table; DROP TABLE IF EXISTS download_table; DROP TABLE IF EXISTS photo_table;";
		Assert.assertEquals(DbConstants.SQL_DROP_ALL_TABLES, SqlDropTableResult);
	}
	
	public void testSqlQueryTables() {
		String SqlQueryTableResult = "server_table LEFT JOIN (SELECT COUNT(clueid) AS device_pics FROM photo_table GROUP BY clueid) AS device_table ON server_table.clueid = device_table.clueid";
		Assert.assertEquals(DbConstants.SQL_QUERY_TABLES, SqlQueryTableResult);
	}
	
	public static void test() {
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		queryBuilder.setTables(DbConstants.SQL_QUERY_TABLES);
		Log.w("test", queryBuilder.buildQuery(DbConstants.SQL_SELECT, null, null, null, null, null, null));
	}
	
	
}
