package com.techelevator.npgeek;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;


@Component
public class JDBCParksDAO implements ParksDAO{
	
	private static final String SELECT_PARKS_SQL = "SELECT * FROM park"; 
			
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void JdbcParksDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
    public Parks getByParkCode(String parkCode) {
        SqlRowSet result = jdbcTemplate.queryForRowSet(SELECT_PARKS_SQL  + " WHERE parkcode = ?", parkCode);
        if (result.next()) {
            return mapRowSetToPark(result);
        }
        return null;
    }
	
	@Override
	public List<Weather> getAllWeather(String parkCode) {
		List<Weather> allWeather = new ArrayList<>();
		String sqlSelectAllResults = "SELECT * FROM weather WHERE parkcode = ?";
		SqlRowSet weathers = jdbcTemplate.queryForRowSet(sqlSelectAllResults, parkCode);
		while (weathers.next()) {
			Weather weather= new Weather();
			weather.setParkCode(weathers.getString("parkCode"));
			weather.setFiveDayForecastValue(weathers.getInt("fiveDayForecastValue"));
			weather.setLow(weathers.getInt("low"));
			weather.setHigh(weathers.getInt("high"));
			weather.setForecast(weathers.getString("forecast"));
		
			
			allWeather.add(weather);
		}
		return allWeather;
	}
	
	
	
	
	@Override
	public List<Parks> getAllParks() {
		List<Parks> allParks = new ArrayList<>();
		String sqlSelectAllResults = "SELECT * FROM park";
		SqlRowSet parks = jdbcTemplate.queryForRowSet(sqlSelectAllResults);
		while (parks.next()) {
			Parks park= new Parks();
			park.setParkCode(parks.getString("parkCode"));
			park.setParkName(parks.getString("parkName"));
			park.setState(parks.getString("state"));
			park.setAcreage(parks.getInt("acreage"));
			park.setElevationInFeet(parks.getInt("elevationInFeet"));
			park.setMilesOfTrail(parks.getFloat("milesOfTrail"));
			park.setNumberOfCampsites(parks.getInt("numberOfCampsites"));
			park.setClimate(parks.getString("climate"));
			park.setYearFounded(parks.getInt("yearFounded"));
			park.setAnnualVisitorCount(parks.getInt("annualVisitorCount"));
			park.setInspirationalQuote(parks.getString("inspirationalQuote"));
			park.setInspirationalQuoteSource(parks.getString("inspirationalQuoteSource"));
			park.setParkDescription(parks.getString("parkDescription"));
			park.setEntryFee(parks.getInt("entryFee"));
			park.setNumberOfAnimalSpecies(parks.getInt("numberOfAnimalSpecies"));
			
			allParks.add(park);
		}
		return allParks;
		
	}
	
	 private Parks mapRowSetToPark(SqlRowSet result) {
	        Parks park = new Parks();
	        park.setParkCode(result.getString("parkCode"));
			park.setParkName(result.getString("parkName"));
			park.setState(result.getString("state"));
			park.setAcreage(result.getInt("acreage"));
			park.setElevationInFeet(result.getInt("elevationInFeet"));
			park.setMilesOfTrail(result.getFloat("milesOfTrail"));
			park.setNumberOfCampsites(result.getInt("numberOfCampsites"));
			park.setClimate(result.getString("climate"));
			park.setYearFounded(result.getInt("yearFounded"));
			park.setAnnualVisitorCount(result.getInt("annualVisitorCount"));
			park.setInspirationalQuote(result.getString("inspirationalQuote"));
			park.setInspirationalQuoteSource(result.getString("inspirationalQuoteSource"));
			park.setParkDescription(result.getString("parkDescription"));
			park.setEntryFee(result.getInt("entryFee"));
			park.setNumberOfAnimalSpecies(result.getInt("numberOfAnimalSpecies"));

	        return park;
	 }
	
	
	
	
	
	
	
	

}
