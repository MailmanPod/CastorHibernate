package com.polux.str.launcher;

//import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.polux.hmg.domain.Country;
import com.polux.hmg.domain.Race;
import com.polux.hmg.domain.Tyre;
import com.polux.utl.utility.RaceTyreUtility;

public class HibernateLauncher {

	private static Logger logger = Logger.getLogger(HibernateLauncher.class);
	
	private static ArrayList<String> races = new ArrayList<String>(20);
	private static ArrayList<String> tyres = new ArrayList<String>(5);
	
	private static void fillRaces() {
		races.add("FORMULA 1 ROLEX AUSTRALIAN GRAND PRIX");
		races.add("FORMULA 1 PETRONAS MALAYSIA GRAND PRIX");
		races.add("FORMULA 1 CHINESE GRAND PRIX");
		races.add("FORMULA 1 GULF AIR BAHRAIN GRAND PRIX");
		races.add("Korea(TBA)");
		races.add("FORMULA 1 GRAN PREMIO DE ESPANIA 2015");
		races.add("FORMULA 1 GRAND PRIX DE MONACO 2015");
		races.add("FORMULA 1 GRAND PRIX DU CANADA 2015");
		races.add("FORMULA 1 GROSSER PREIS VON OSTERREICH 2015");
		races.add("2015 FORMULA 1 BRITISH GRAND PRIX");
		races.add("FORMULA 1 GROSSER PREIS VON DEUTSCHLAND 2015");
		races.add("FORMULA 1 MAGYAR NAGYDIJ 2015");
		races.add("2015 FORMULA 1 SHELL BELGIAN GRAND PRIX");
		races.add("FORMULA 1 GRAN PREMIO DITALIA 2015");
		races.add("2015 FORMULA 1 SINGAPORE AIRLINES SINGAPORE GRAND PRIX");
		races.add("2015 FORMULA 1 JAPANESE GRAND PRIX");
		races.add("2015 FORMULA 1 RUSSIAN GRAND PRIX");
		races.add("2015 FORMULA 1 UNITED STATES GRAND PRIX");
		races.add("FORMULA 1 GRAN PREMIO DE MEXICO 2015");
		races.add("FORMULA 1 GRANDE PREMIO DO BRASIL 2015");
		races.add("2015 FORMULA 1 ETIHAD AIRWAYS ABU DHABI GRAND PRIX");
	}
	
	private static void fillTyres() {
		tyres.add("Super Soft");
		tyres.add("Soft");
		tyres.add("Medium");
		tyres.add("Hard");
		tyres.add("Intermediate");
		tyres.add("Wet");
	}
	
	private static Race createRace(Country country) {
		logger.warn("Creating new Race...");
		String fullName = races.get(1);
		int laps=56;
		Date startDate = new Date(System.currentTimeMillis());
		Date endDate = new Date(64060513200000L);
		
		System.out.println("OUTERRORDATE: " + endDate.toString());
		
		return new Race(fullName, laps, startDate, endDate, country);
	}
	
	private static Country createCountry() {
		
		String place = "Sepang";
		String country = "Malasia";
		
		return new Country(place, country);
	}
	
	private static Tyre createTyre() {
		logger.warn("Creating new Tyre...");
		String description = tyres.get(1);
		int minWear = 1;
		int maxWear = 12;
		Date startDate = new Date(System.currentTimeMillis());
		Date endDate = new Date(64060513200000L);
		return new Tyre(description, minWear, maxWear, startDate, endDate);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		fillRaces();
		fillTyres();
		
		Country country = createCountry();
		Race race = createRace(country);
		Tyre tyre = createTyre();
		
		logger.info("Starting application");
		RaceTyreUtility utility = new RaceTyreUtility();
		
		logger.info("Adding items");
		utility.addItems(race, tyre);
		//utility.addItemRace(race, country);
		//utility.addItem();
	}

}
