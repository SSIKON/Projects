package com.techelevator.npgeek;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;




@Component
public class JDBCSurveyDAO implements SurveyDAO{
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void JdbcSurveyDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Survey> getAllResults() {
		List<Survey> allResults = new ArrayList<>();
		String sqlSelectAllResults = "SELECT COUNT(park.parkname), park.parkName, park.parkCode FROM survey_result\n" + 
				"JOIN park on survey_result.parkCode = park.parkCode\n" + 
				"GROUP BY park.parkName, park.parkcode\n" + 
				"ORDER BY COUNT(park.parkname) DESC, park.parkname asc";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllResults);
		while (results.next()) {
			Survey survey= new Survey();
			survey.setParkCode(results.getString("parkcode"));
			survey.setParkCount(results.getLong("count"));
			survey.setParkName(results.getString("parkname"));
			
			
			allResults.add(survey);
		}
		return allResults;
		
	}
	

	
	

	@Override
	public void createSurvey(Survey survey) {
		

		String sqlInsertSurvey = "INSERT INTO survey_result( parkcode, emailaddress, state, activitylevel) VALUES (?,?,?,?)";
		jdbcTemplate.update(sqlInsertSurvey, survey.getParkCode(), survey.getEmailAddress(), survey.getState(),
				survey.getActivityLevel());


}
	
	

}
