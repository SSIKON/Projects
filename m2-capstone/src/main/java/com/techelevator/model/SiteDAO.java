package com.techelevator.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface SiteDAO {
	
	List<Site> getSitesAvailableWithinRange(int campgroundId, LocalDate arrivalDate, LocalDate departureDate);
	

}
