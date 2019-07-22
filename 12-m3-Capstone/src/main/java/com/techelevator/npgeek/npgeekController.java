package com.techelevator.npgeek;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class npgeekController {
	
	@Autowired
	private SurveyDAO dao;
	
	@Autowired
	private ParksDAO parkDao;

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String getMainScreen(HttpServletRequest req) {
		
		List<Parks>parks = parkDao.getAllParks(); 
		req.setAttribute("parks", parks);
		
		return "homePage";
	}
	
	@RequestMapping(path = "/homePage", method = RequestMethod.GET)
	public String showParks(HttpServletRequest req) {
		
		List<Parks>parks = parkDao.getAllParks(); 
		req.setAttribute("parks", parks);
		
		return "homePage";
	}
		
		@RequestMapping(path = "/detailPage", method = RequestMethod.GET)
		public String showDetailPage(HttpServletRequest req, @RequestParam String parkCode) {
			
			
			List<Weather>weathers = parkDao.getAllWeather(parkCode); 
			req.setAttribute("weathers", weathers);
			
			Parks park = parkDao.getByParkCode(parkCode);
			req.setAttribute("park", park);
			
			
			return "/detailPage";
		}
	
	
	@RequestMapping(path = "/surveyForm", method = RequestMethod.GET)
	public String getSurveyForm(ModelMap map) {
		map.addAttribute("stateList", states());
		map.addAttribute("allParks", parkDao.getAllParks());
		return "surveyForm";

}

	@RequestMapping(path="/surveyForm", method=RequestMethod.POST)
	public String save(Survey survey) {

	dao.createSurvey(survey);
	return "redirect:/surveyResults";
}
	
	@RequestMapping("/surveyResults")
	public String displayResults(HttpServletRequest req) {
		
		List<Survey>results = dao.getAllResults(); 
		req.setAttribute("results", results);

		return "surveyResults";
	}
	
	private List<String> parks(List<Parks> allParks){
		
		ArrayList<String> parkList= new ArrayList<String>();
		for (Parks park : allParks) {
			
		parkList.add(park.getParkName());
		}
		return parkList;
	}
	
	private List<String> states() {
		 ArrayList<String> stateList = new ArrayList<String>();
		    stateList.add("AL");
		    stateList.add("AK");
		    stateList.add("AZ");
		    stateList.add("AR");
		    stateList.add("CA");
		    stateList.add("CO");
		    stateList.add("CT");
		    stateList.add("DE");
		    stateList.add("FL");
		    stateList.add("GA");
		    stateList.add("HI");
		    stateList.add("ID");
		    stateList.add("IL");
		    stateList.add("IN");
		    stateList.add("IA");
		    stateList.add("KS");
		    stateList.add("KY");
		    stateList.add("LA");
		    stateList.add("ME");
		    stateList.add("MD");
		    stateList.add("MA");
		    stateList.add("MI");
		    stateList.add("MN");
		    stateList.add("MS");
		    stateList.add("MO");
		    stateList.add("MT");
		    stateList.add("NE");
		    stateList.add("NV");
		    stateList.add("NH");
		    stateList.add("NJ");
		    stateList.add("NM");
		    stateList.add("NY");
		    stateList.add("NC");
		    stateList.add("ND");
		    stateList.add("OH");
		    stateList.add("OK");
		    stateList.add("OR");
		    stateList.add("PA");
		    stateList.add("RI");
		    stateList.add("SC");
		    stateList.add("SD");
		    stateList.add("TN");
		    stateList.add("TX");
		    stateList.add("UT");
		    stateList.add("VT");
		    stateList.add("VA");
		    stateList.add("WA");
		    stateList.add("WV");
		    stateList.add("WI");
		    stateList.add("WY");
		  
		    return stateList;
		}
	
	
}
