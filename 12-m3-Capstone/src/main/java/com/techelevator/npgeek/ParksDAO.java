package com.techelevator.npgeek;

import java.util.List;

public interface ParksDAO {

	List<Parks> getAllParks();

	Parks getByParkCode(String parkCode);

	List<Weather> getAllWeather(String parkCode);

}
