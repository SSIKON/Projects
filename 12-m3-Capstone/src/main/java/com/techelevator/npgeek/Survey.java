package com.techelevator.npgeek;

import java.util.ArrayList;
import java.util.List;

import org.apache.bcel.generic.RETURN;

public class Survey {
	
	
	
	private Long parkCount;
	private String parkName;
	private Integer id;
	private Integer surveyId;
	private String parkCode;
	private String emailAddress;
	private String state;
	private String activityLevel;
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public Integer getSurveyId() {
		return surveyId;
	}


	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}


	public String getParkCode() {
		return parkCode;
	}


	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}


	public String getEmailAddress() {
		return emailAddress;
	}


	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getActivityLevel() {
		return activityLevel;
	}


	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}


	public Long getParkCount() {
		return parkCount;
	}


	public void setParkCount(Long parkCount) {
		this.parkCount = parkCount;
	}


	public String getParkName() {
		return parkName;
	}


	public void setParkName(String parkName) {
		this.parkName = parkName;
	}


	
	
	
	

}
