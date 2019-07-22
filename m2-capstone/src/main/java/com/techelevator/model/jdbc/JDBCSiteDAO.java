package com.techelevator.model.jdbc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.Site;
import com.techelevator.model.SiteDAO;

public class JDBCSiteDAO implements SiteDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCSiteDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Site> getSitesAvailableWithinRange(int campgroundId, LocalDate arrivalDate, LocalDate departureDate) {
		String sql = "select site.site_id, site.campground_id, site.site_number, site.max_occupancy, site.accessible, site.max_rv_length, site.utilities "
				+ "from site " + "join campground c on site.campground_id = c.campground_id "
				+ "where c.campground_id = ? " + "and site.site_id " + "not in ( " + "select site.site_id from site "
				+ "join reservation r on site.site_id = r.site_id "
				+ "where (r.from_date between ? and ? and r.to_date between ? and ?) " + ") limit 5; ";

		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, campgroundId, arrivalDate, departureDate, arrivalDate,
				departureDate);

		List<Site> availableSites = new ArrayList<Site>();

		while (result.next()) {
			availableSites.add(mapRowToSite(result));
		}
		return availableSites;
	}

	private Site mapRowToSite(SqlRowSet result) {
		Site s = new Site();
		s.setSiteId(result.getInt("site_id"));
		s.setCampgroundId(result.getInt("campground_id"));
		s.setSiteNumber(result.getInt("site_number"));
		s.setMaxOccupancy(result.getInt("max_occupancy"));
		s.setAccessible(result.getBoolean("accessible"));
		s.setMaxRvLength(result.getInt("max_rv_length"));
		s.setUtilities(result.getBoolean("utilities"));
		return s;
	}

}
