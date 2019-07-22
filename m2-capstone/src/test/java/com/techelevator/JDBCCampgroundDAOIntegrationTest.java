package com.techelevator;

import org.junit.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.CampgroundDAO;
import com.techelevator.model.jdbc.JDBCCampgroundDAO;

public class JDBCCampgroundDAOIntegrationTest extends DAOIntegrationTest {
	
	private CampgroundDAO dao;
	private JdbcTemplate jdbcTemplate;
	int rowCount = 0;
	
	@Before 
	public void setup() {
		jdbcTemplate = new JdbcTemplate(getDataSource());
		dao = new JDBCCampgroundDAO(getDataSource());
	}
	
	@Test
	public void get_campgrounds_by_park() {
		truncateTable();
		insertPark();
		String sql = "select park_id from park";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
		result.next();
		int parkId = result.getInt(1);
		insertCampground(parkId);
		countRowsOnCampgroundTable();
		Assert.assertTrue(rowCount > 0);
		
	}
	
	public void truncateTable() {
		String sql = "truncate park cascade;";
		jdbcTemplate.update(sql);
	}
	
	public void insertPark() {
		String sql = "insert into park (park_id, name, location, establish_date, area, visitors, description) values (default, 'testPark', 'testLocation', '2001-01-01', 100, 100, 'testDescription')";
		jdbcTemplate.update(sql);
	}
	
	public void insertCampground(int parkId) {
		String sql = "insert into campground (campground_id, park_id, name, open_from_mm, open_to_mm, daily_fee) values (default, ?, 'testName', '01', '06', '$5.00')";
		jdbcTemplate.update(sql, parkId);
	}
	
	public void countRowsOnCampgroundTable() {
		String sql = "select count(*) from campground";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
		result.next();
		rowCount = result.getInt(1);
	}
	
	
	
	

}
