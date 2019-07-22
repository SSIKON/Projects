package com.techelevator.npgeek;

public class Weather {
	
	private String parkCode;
	private Integer fiveDayForecastValue;
	private Integer low;
	private Integer high;
	private String forecast;
	
	
	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public Integer getLow() {
		return low;
	}
	public void setLow(Integer low) {
		this.low = low;
	}
	public Integer getHigh() {
		return high;
	}
	public void setHigh(Integer high) {
		this.high = high;
	}
	public String getForecast() {
		return forecast;
	}
	public void setForecast(String forecast) {
		this.forecast = forecast;
	}
	public Integer getFiveDayForecastValue() {
		return fiveDayForecastValue;
	}
	public void setFiveDayForecastValue(Integer fiveDayForecastValue) {
		this.fiveDayForecastValue = fiveDayForecastValue;
	}

}
