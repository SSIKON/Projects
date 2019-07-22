package com.techelevator;

import org.junit.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.ParkDAO;
import com.techelevator.model.jdbc.JDBCParkDAO;

public class JDBCParkDAOIntegrationTest extends DAOIntegrationTest {
	
	private ParkDAO dao;
	private JdbcTemplate jdbcTemplate;
	
	@Before
	public void setup() {
		jdbcTemplate = new JdbcTemplate(getDataSource());
		dao = new JDBCParkDAO(getDataSource());
	}
	
	@Test
	public void get_all_parks_returns_all_parks() {
		int counter = 0;
		
		String sql1 = "select count(*) from park";
		SqlRowSet result1 = jdbcTemplate.queryForRowSet(sql1);
		result1.next();
		int count1 = result1.getInt(1);
		
		insert_park();
		counter++;
		insert_park();
		counter++;
		insert_park();
		counter++;
		
		String sql2 = "select count(*) from park";
		SqlRowSet result2 = jdbcTemplate.queryForRowSet(sql2);
		result2.next();
		int count2 = result2.getInt(1);
		
		Assert.assertTrue(count2 == count1 + counter);
		
	}
	
	private void insert_park() {
		String sql = "insert into park (park_id, name, location, establish_date, area, visitors, description) values (default, 'TestName', 'TestLocation', '2001-01-01', 1000, 1000, 'very nice')";
		jdbcTemplate.update(sql);
	}

}
