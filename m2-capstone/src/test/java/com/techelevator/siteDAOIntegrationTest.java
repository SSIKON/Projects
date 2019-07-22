package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.SiteDAO;
import com.techelevator.model.jdbc.JDBCSiteDAO;

public class siteDAOIntegrationTest extends DAOIntegrationTest {
	
	private SiteDAO dao;
	private JdbcTemplate jdbcTemplate;
	
	@Before
	public void setup() {
		jdbcTemplate = new JdbcTemplate(getDataSource());
		dao = new JDBCSiteDAO(getDataSource());
	}
	
	@Test
	public void get_sites_available_within_range() {
		int counter = 0;
		
		String sql1 = "select count(*) from site";
		SqlRowSet result1 = jdbcTemplate.queryForRowSet(sql1);
		result1.next();
		int count1 = result1.getInt(1);
		
		insert_site();
		counter++;
		
		
		String sql2 = "select count(*) from site";
		SqlRowSet result2 = jdbcTemplate.queryForRowSet(sql2);
		result2.next();
		int count2 = result2.getInt(1);
		
		Assert.assertTrue(count2 == count1 + counter);
		
	}
	
	private void insert_site() {
		String sql = "insert into site (site_number,campground_id, max_occupancy, accessible, max_rv_length, utilities) values (1,1, 4, true, 0, false);";
		jdbcTemplate.update(sql);
	}

}
