package net.erickelly.huskyhunters.data.test;

import junit.framework.Assert;
import junit.framework.TestCase;
import net.erickelly.huskyhunters.data.DbConstants;
import android.database.sqlite.SQLiteQueryBuilder;

/**
 * A class for testing DbConstants and the SQL query strings.  Requires Android JUnit.
 * @author francis
 *
 */
public class DbConstantsTests extends TestCase {
	
	public void testDbServerTableCreationString() {
		String SqlServerTestCreationResult = "CREATE TABLE test (_id integer primary key autoincrement, clueid text not null, cluetext text not null, clueanswer text not null, points integer not null, picsreqd integer not null, server_pics integer not null);";
		Assert.assertEquals(DbConstants.SQL_SERVER_TABLE_CREATION("test"), SqlServerTestCreationResult);
	}
	
	public static void testSqlQuery() {
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		queryBuilder.setTables(DbConstants.SQL_QUERY_TABLES);
		String SqlQueryResult = "SELECT _id, server_table.clueid, cluetext, clueanswer, points, picsreqd, server_pics, SUM(device_pics + server_table.server_pics) as total_pics FROM server_table LEFT JOIN (SELECT clueid, COUNT(clueid) AS device_pics FROM photo_table GROUP BY clueid) AS device_table WHERE server_table.clueid = device_table.clueid GROUP BY server_table.clueid";
		Assert.assertEquals(SqlQueryResult, queryBuilder.buildQuery(DbConstants.SQL_SELECT, null, null, null, null, null, null));
	}
	
	
}
