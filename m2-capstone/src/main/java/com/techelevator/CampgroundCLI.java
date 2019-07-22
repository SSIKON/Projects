package com.techelevator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.model.Campground;
import com.techelevator.model.CampgroundDAO;
import com.techelevator.model.Park;
import com.techelevator.model.ParkDAO;
import com.techelevator.model.ReservationDAO;
import com.techelevator.model.Site;
import com.techelevator.model.SiteDAO;
import com.techelevator.model.jdbc.JDBCCampgroundDAO;
import com.techelevator.model.jdbc.JDBCParkDAO;
import com.techelevator.model.jdbc.JDBCReservationDAO;
import com.techelevator.model.jdbc.JDBCSiteDAO;
import com.techelevator.view.Menu;

public class CampgroundCLI {

	private Menu menu;
	private ParkDAO parkDAO;
	private CampgroundDAO campgroundDAO;
	private SiteDAO siteDAO;
	private ReservationDAO reservationDAO;
	private List<Park> parkList;
	private List<Campground> campgroundList;
	private int parkId;

	public static void main(String[] args) {
		CampgroundCLI application = new CampgroundCLI();
		application.run();
	}

	public CampgroundCLI() {
		this.menu = new Menu();
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		parkDAO = new JDBCParkDAO(dataSource);
		campgroundDAO = new JDBCCampgroundDAO(dataSource);
		siteDAO = new JDBCSiteDAO(dataSource);
		reservationDAO = new JDBCReservationDAO(dataSource);

		this.parkList = parkDAO.getAllParks();

	}

	public void run() {

		menu.displayBanner();
		mainMenu();

	}

	public void mainMenu() {
		while (true) {
			menu.viewParksInterface(parkList);
			String userInput = menu.getChoice();

			if (userInput.toUpperCase().contains("q".toUpperCase())) {
				menu.displayThanks();
				break;
			} else if (!userInput.matches(".*\\d.*") || Integer.parseInt(userInput) > parkList.size()) {
				menu.displayWrongChoice();
			} else {
				Park parkToDisplay = parkList.get((Integer.parseInt(userInput) - 1));
				parkInfoMenu(parkToDisplay);

			}
		}
	}

	public void parkInfoMenu(Park parkToDisplay) {
		while (true) {
			menu.viewParkInfo(parkToDisplay);
			String userInput = menu.getChoice();
			campgroundList = campgroundDAO.getParkCampgrounds(parkToDisplay.getId());

			if (!userInput.matches(".*\\d.*") || Integer.parseInt(userInput) > 3) {
				menu.displayWrongChoice();
			} else if (Integer.parseInt(userInput) == 1) {
				campGroundDescriptionMenu(campgroundList);
			} else if (Integer.parseInt(userInput) == 2) {
				reservationsMenu(campgroundList); // reservations
			} else if (Integer.parseInt(userInput) == 3) {
				break;
			}

		}
	}

	public void campGroundDescriptionMenu(List<Campground> campgroundList) {
		String userInput = null;
		menu.viewCampgrounds(campgroundList); // campgrounds
		while (true) {
			menu.displayCampgroundInfoScreenChoices();
			userInput = menu.getChoice();
			if (!userInput.matches(".*\\d.*") || Integer.parseInt(userInput) > 2 || Integer.parseInt(userInput) < 1) {
				menu.displayWrongChoice();
			} else if (Integer.parseInt(userInput) == 1) {
				reservationsMenu(campgroundList);
			} else {
				break;
			}
		}
	}

	public void reservationsMenu(List<Campground> campgroundList) {
		menu.displaySearchBanner();
		menu.viewCampgrounds(campgroundList);

		int campgroundMenuChoice = getCampGroundChoice(campgroundList); // collect values to pass to next menu
		LocalDate arrivalDate = getArrivalDate();
		LocalDate departureDate = getDepartureDate();

		while (true) {
			// collect choice for which campground to display sites
			Campground campgroundToDisplay = campgroundList.get(campgroundMenuChoice - 1);

			sitesAvailableMenu(campgroundToDisplay, arrivalDate, departureDate);

		}
	}

	public void sitesAvailableMenu(Campground campgroundToDisplay, LocalDate arrivalDate, LocalDate departureDate) {
		double totalFee;
		while (true) {
			int campgroundId = campgroundToDisplay.getCampgroundId();
			List<Site> siteList = siteDAO.getSitesAvailableWithinRange(campgroundId, arrivalDate, departureDate);
			if (siteList.size() < 1) {
				menu.displayNoAvailableSitesMessage();
				break;
			}
			totalFee = calculateTotalFee(campgroundToDisplay, arrivalDate, departureDate);
			menu.viewSitesInterface(siteList, totalFee);
			siteBookingMenu(siteList, arrivalDate, departureDate);

		}

	}

	private void siteBookingMenu(List<Site> siteList, LocalDate arrivalDate, LocalDate departureDate) {
		menu.displaySiteReservationPrompt();
		while (true) {
			try {
				int userInput = Integer.parseInt(menu.getChoice());
				if (userInput == 0) {
					mainMenu();
				} else
					for (Site site : siteList) {
						if (site.getSiteId() == userInput) {
							menu.displayNamePrompt();
							String name = menu.getChoice();
							String reservationId = reservationDAO.createReservation(site, name, arrivalDate,
									departureDate);
							menu.displaySuccessfulReservationMessage(reservationId);
							mainMenu();
						}
					}
			} catch (NumberFormatException e) {
				menu.displayWrongChoice();
				break;
			}
		}

	}

	public int getCampGroundChoice(List<Campground> campgroundList) {
		String campgroundChoice = null;
		int choiceInt;

		while (true) {
			campgroundChoice = menu.reservationCampgroundChoice();

			try {
				choiceInt = Integer.parseInt(campgroundChoice);
				if (choiceInt < campgroundList.size())
					return choiceInt;
				menu.displayWrongChoice();
			} catch (Exception e) {
				menu.displayWrongChoice();
			}
		}
	}

	public LocalDate getArrivalDate() {
		String arrivalDate = null;

		while (true) {
			arrivalDate = menu.reservationDate("Arrival");

			try {
				arrivalDate = formatUserDate(arrivalDate);
				return LocalDate.parse(arrivalDate);
			} catch (Exception e) {
				menu.displayInvalidDateFormat();
			}
		}

	}

	public LocalDate getDepartureDate() {
		String departureDate = null;

		while (true) {
			departureDate = menu.reservationDate("Departure");

			try {
				departureDate = formatUserDate(departureDate);
				return LocalDate.parse(departureDate);
			} catch (Exception e) {
				menu.displayInvalidDateFormat();
			}
		}

	}

	private String formatUserDate(String date) {

		String[] newArr = new String[3];
		newArr = date.split("/");
		String newDate;
		newDate = newArr[2] + "-" + newArr[0] + "-" + newArr[1];
		return newDate;

	}

	private double calculateTotalFee(Campground campgroundToDisplay, LocalDate arrivalDate, LocalDate departureDate) {
		double dailyRate = campgroundToDisplay.getDailyFee();
		long durationOfStay = ChronoUnit.DAYS.between(arrivalDate, departureDate);
		return (double) durationOfStay * dailyRate;

	}

}
