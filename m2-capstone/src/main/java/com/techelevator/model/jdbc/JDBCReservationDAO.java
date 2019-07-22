package com.techelevator.model.jdbc;

import java.time.LocalDate;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.ReservationDAO;
import com.techelevator.model.Site;

public class JDBCReservationDAO implements ReservationDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCReservationDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public String createReservation(Site site, String name, LocalDate arrivalDate, LocalDate departureDate) {
		String sql = "insert into reservation (reservation_id, site_id, name, from_date, to_date, create_date) "
				+ "values (default, ?, ?, ?, ?, ?) returning reservation_id;";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, site.getSiteId(), name, arrivalDate, departureDate,
				LocalDate.now());
		result.next();
		return result.getString(1);
	}

}
