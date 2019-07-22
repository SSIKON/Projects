package com.techelevator.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.Campground;
import com.techelevator.model.Park;
import com.techelevator.model.ParkDAO;

public class JDBCParkDAO implements ParkDAO {
	
	private JdbcTemplate jdbcTemplate;

	public JDBCParkDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Park> getAllParks() {
		String sql = "SELECT park_id, name, location, establish_date, area, visitors, description from park order by name";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql);

		List<Park> allParks = new ArrayList<Park>();
		
		while (result.next()) {
			allParks.add(mapRowToPark(result));
		}
		return allParks;
	}
	
	
	
	private Park mapRowToPark(SqlRowSet result) {
		Park p = new Park();
		p.setId(result.getInt("park_id"));
		p.setName(result.getString("name"));
		p.setLocation(result.getString("location"));
		p.setEstDate(result.getDate("establish_date").toLocalDate());
		p.setArea(result.getInt("Area"));
		p.setVisitors(result.getInt("visitors"));
		p.setDescription(result.getString("description"));
		
		return p;
		
	}
	
	

	

}
