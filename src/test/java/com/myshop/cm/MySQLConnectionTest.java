package com.myshop.cm;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" )
public class MySQLConnectionTest {

	@Inject
	private DataSource dataSource;
	
	@Test
	public void dataSourceTest() throws Exception {
		assertNotNull(dataSource);
		assertNotNull(dataSource.getConnection());
	}
}
