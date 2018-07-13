package it.polito.tdp.flightdelays.db;

import it.polito.tdp.flightdelays.model.Airline;
import it.polito.tdp.flightdelays.model.AirlineIdMap;
import it.polito.tdp.flightdelays.model.Airport;
import it.polito.tdp.flightdelays.model.AirportIdMap;
import it.polito.tdp.flightdelays.model.FlightIdMap;

public class TestDAO {

	public static void main(String[] args) {

		FlightDelaysDAO dao = new FlightDelaysDAO();
		
		AirlineIdMap airlineIdMap = new AirlineIdMap();
		AirportIdMap airportIdMap = new AirportIdMap();
		FlightIdMap flightIdMap = new FlightIdMap();
		
		Airline airline = new Airline(10, "EV", "Atlantic Southeast Airlines");
		

//		System.out.println(dao.loadAllAirlines(airlineIdMap));
//		System.out.println(dao.loadAllAirports(airportIdMap));
//		System.out.println(dao.loadAllFlights(airlineIdMap, flightIdMap, airportIdMap));
		
		
		Airport origin = new Airport(151, "IAH", "George Bush Intercontinental Airport", "Houston", "TX", "USA", 29.98047, -95.33972, -5);
		
		Airport destination = new Airport(81,"DAL", "Dallas Love Field", "Dallas", "TX", "USA", 32.84711, 96.85177, -5);
		
		System.out.println(dao.calcolaMediaDelayDiTratta(airline, origin, destination, airportIdMap, airlineIdMap));
		
	}

}
