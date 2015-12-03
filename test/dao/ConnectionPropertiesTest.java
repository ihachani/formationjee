package dao;

import java.io.IOException;
import java.util.Properties;

import org.junit.*;

public class ConnectionPropertiesTest {
	@Test
	public void testProperties() {
		Properties connectionProperties = new Properties();
		try {
			connectionProperties.load(getClass().getClassLoader().getResourceAsStream("mysqlconfig.properties"));
			Assert.assertEquals(connectionProperties.getProperty("host"), "jdbc:mysql://localhost:3306/");
			Assert.assertEquals(connectionProperties.getProperty("database"), "books");
			Assert.assertEquals(connectionProperties.getProperty("user"), "formation");
			Assert.assertEquals(connectionProperties.getProperty("password"), "formation");

		} catch (IOException e) {
			Assert.fail();
		}

	}
}
