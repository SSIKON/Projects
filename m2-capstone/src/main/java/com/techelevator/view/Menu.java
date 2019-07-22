package com.techelevator.view;

import java.text.DateFormatSymbols;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Scanner;

import com.techelevator.model.Campground;
import com.techelevator.model.Park;
import com.techelevator.model.Site;

public class Menu {

	private Scanner in = new Scanner(System.in);
	private Park park;
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
	NumberFormat formatter = NumberFormat.getCurrencyInstance();

	public Menu() {

	}

	public void displayBanner() {
		System.out.println("*******Welcome to The Park Reservation System!*******");
		System.out.println();
	}

	public void displaySearchBanner() {
		System.out.println("******* Search for a Reservation *******");
		System.out.println();
	}

	public void displayThanks() {
		System.out.println("");
		System.out.println("****Thanks for using the park reservation system!****");
		System.out.println("                 ***GoodBye!***");
	}

	public void displayWrongChoice() {
		System.out.println("");
		System.out.println("****Invalid Selection****");
		System.out.println(" ***Please try again***");
		System.out.println("");

	}

	public void displayInvalidDateFormat() {
		System.out.println("");
		System.out.println("      ****Invalid Date Format****");
		System.out.println(" ***Please enter date as 'mm/dd/yyyy'***");
		System.out.println("");

	}

	public void displayNoAvailableSitesMessage() {
		System.out.println("");
		System.out.println("****There are no sites available for your dates****");
		System.out.println("               ***Please try again***");
		System.out.println("");
	}

	public void viewParkInfo(Park parkToDisplay) {
	
		System.out.println("_________________________");
		System.out.println();
		System.out.println(parkToDisplay.getName() + " National Park");
		System.out.printf("%-20s%-15s%n", "Location:", parkToDisplay.getLocation());
		System.out.printf("%-20s%-15s%n", "Established:", parkToDisplay.getEstDate().format(dateFormatter));
		System.out.printf("%-20s%-5s%-5s%n", "Area:", parkToDisplay.getArea(), " sq km");
		System.out.printf("%-20s%-15s%n%n", "Annual Visitors:", parkToDisplay.getVisitors());
		System.out.printf("%-35s%n", parkToDisplay.getDescription());
	
		displayParkInfoScreenChoices();
	}

	public void displayParkInfoScreenChoices() {
		System.out.println("_________________________");
		System.out.println();
		System.out.println("Select a Command");
		System.out.println("1) View Campgrounds");
		System.out.println("2) Search for Reservation");
		System.out.println("3) Return to Previous Screen");

	}

	public void viewParksInterface(List<Park> parkList) {
		int numChoice = 0;
		System.out.println();
		System.out.println("Select a park for further details");

		for (Park park : parkList) {
			numChoice++;
			System.out.println(numChoice + ") " + park.getName());
		}
		System.out.println("Q) Quit");
	}

	public void viewSitesInterface(List<Site> siteList, double totalFee) {
		int numChoice = 0;
		System.out.println();

		System.out.println("Results Matching Your Search Criteria");
//		8 10 11 13 10 4
		System.out.printf("%-8s%-10s%-11s%-13s%-10s%-4s%n", "Site No.", "Max Occup.", "Accessible?", "Max RV Length",
				"Utility", "Cost");

		for (Site site : siteList) {
			System.out.printf("%-8s%-10s%-11s%-13s%-10s%-4s%n", site.getSiteNumber(), site.getMaxOccupancy(),
					site.isAccessible(), site.getMaxRvLength(), site.isUtilities(), formatter.format(totalFee));
		}
	}

	public void viewCampgrounds(List<Campground> campgroundList) {
		int count = 0;
		System.out.printf("%-4s%-35s%-10s%-10s%-10s%n", " ", "Name", "Open", "Close", "Daily Fee");
		for (Campground campground : campgroundList) {
			String name = campground.getName();
			String open = formatMonth(campground.getOpenFrom());
			String close = formatMonth(campground.getOpenTo());
			String fee = formatter.format(campground.getDailyFee());
			count++;
			System.out.printf("%-4s%-35s%-10s%-10s%-10s%n", count, name, open, close, fee);

		}

	}

	public void displayCampgroundInfoScreenChoices() {
		System.out.println("_________________________");
		System.out.println();
		System.out.println("Select a Command");
		System.out.println("1) Search for Reservation");
		System.out.println("2) Return to Previous Screen");
	}

	public void displaySiteReservationPrompt() {
		System.out.println("_________________________");
		System.out.println();
		System.out.println("Which site should be reserved (enter 0 to cancel)");
	}

	public void displayNamePrompt() {
		System.out.println("What name should the reservation be made under");
	}

	public String reservationCampgroundChoice() {
		System.out.println("Which campground? (Enter 0 to cancel)");
		return getChoice();
	}

	public String reservationDate(String dateType) {
		System.out.println("What is the " + dateType + " Date?");
		System.out.println("Please enter date as MM/DD/YYYY");
		return getChoice();
	}

	public String getChoice() {
		System.out.flush();
		String choice = in.nextLine();
		return choice;
	}

	private String formatMonth(String month) {
		int n = Integer.parseInt(month);
		return new DateFormatSymbols().getMonths()[n - 1];
	}

	public void displaySuccessfulReservationMessage(String reservationId) {
		System.out.println("The reservation has been made, and your confirmation id is: " + reservationId);
	}

}
