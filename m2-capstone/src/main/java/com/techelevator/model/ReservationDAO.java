package com.techelevator.model;

import java.time.LocalDate;

public interface ReservationDAO {

	public String createReservation(Site site, String name, LocalDate arrivalDate, LocalDate departureDate);

}
