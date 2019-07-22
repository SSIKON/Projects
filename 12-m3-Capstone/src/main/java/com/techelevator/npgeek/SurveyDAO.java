package com.techelevator.npgeek;

import java.util.List;


public interface SurveyDAO {
	
	public List<Survey> getAllResults();
	
	

	public void createSurvey(Survey survey);

}
