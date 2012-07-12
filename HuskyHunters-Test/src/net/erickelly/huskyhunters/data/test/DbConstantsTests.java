package net.erickelly.huskyhunters.data.test;

import junit.framework.Assert;
import junit.framework.TestCase;
import net.erickelly.huskyhunters.data.*;

public class DbConstantsTests extends TestCase {
	
	public void testDbServerTableCreationString() {
		String SqlServerTestCreationResult = "CREATE TABLE test (_id integer primary key autoincrement, clueid text not null, cluetext text not null, clueanswer text not null, points integer not null, picsreqd integer not null, picsonserver integer not null);";
		Assert.assertEquals(DbConstants.SQL_SERVER_TABLE_CREATION("test"), SqlServerTestCreationResult);
	}
	
	public void testDropTableSql() {
		String SqlDropTableResult = "DROP TABLE IF EXISTS device_table; DROP TABLE IF EXISTS server_table; DROP TABLE IF EXISTS download_table; DROP TABLE IF EXISTS photo_table;";
		Assert.assertEquals(DbConstants.SQL_DROP_ALL_TABLES, SqlDropTableResult);
	}
	
	
}
