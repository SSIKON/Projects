package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.ReservationDAO;
import com.techelevator.model.jdbc.JDBCReservationDAO;

public class ReservationDAOIntegrationTest extends DAOIntegrationTest {

	private ReservationDAO dao;
	private JdbcTemplate jdbcTemplate;

	@Before
	public void setup() {
		jdbcTemplate = new JdbcTemplate(getDataSource());
		dao = new JDBCReservationDAO(getDataSource());
	}

	@Test
	public void create_reservation() {
		int counter = 0;

		String sql1 = "select count(*) from reservation";
		SqlRowSet result1 = jdbcTemplate.queryForRowSet(sql1);
		result1.next();
		int count1 = result1.getInt(1);

		insert_reservation();
		counter++;

		String sql2 = "select count(*) from reservation";
		SqlRowSet result2 = jdbcTemplate.queryForRowSet(sql2);
		result2.next();
		int count2 = result2.getInt(1);

		Assert.assertTrue(count2 == count1 + counter);

	}

	private void insert_reservation() {
		String sql = "insert into reservation (reservation_id, site_id, name, from_date, to_date, create_date) values (default, 1, 'Smith', '2019-06-17', '2019-06-23', '2019-06-25');";
		jdbcTemplate.update(sql);
	}

}
