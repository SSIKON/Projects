package com.techelevator.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.Campground;
import com.techelevator.model.CampgroundDAO;

public class JDBCCampgroundDAO implements CampgroundDAO{
	
	private JdbcTemplate jdbcTemplate;

	public JDBCCampgroundDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Campground> getParkCampgrounds(int parkId) {
		String sql = "SELECT campground_id, park_id, name, open_from_mm, open_to_mm, daily_fee from campground where park_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, parkId);

		List<Campground> allCampgrounds = new ArrayList<Campground>();
		
		while (result.next()) {
			allCampgrounds.add(mapRowToCampground(result));
		}
		return allCampgrounds;
	}
	
	
	private Campground mapRowToCampground(SqlRowSet result) {
		Campground c = new Campground();
		c.setCampgroundId(result.getInt("campground_id"));
		c.setParkId(result.getInt("park_id"));
		c.setName(result.getString("name"));
		c.setOpenFrom(result.getString("open_from_mm"));
		c.setOpenTo(result.getString("open_to_mm"));
		c.setDailyFee(result.getDouble("daily_fee"));
		return c;
	}

	

	
	
	

}
