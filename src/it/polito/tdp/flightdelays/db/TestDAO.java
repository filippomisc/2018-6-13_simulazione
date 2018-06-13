package it.polito.tdp.flightdelays.db;

import it.polito.tdp.flightdelays.model.AirlineIdMap;
import it.polito.tdp.flightdelays.model.AirportIdMap;
import it.polito.tdp.flightdelays.model.FlightIdMap;

public class TestDAO {

	public static void main(String[] args) {

		FlightDelaysDAO dao = new FlightDelaysDAO();
		
		AirlineIdMap airlineIdMap = new AirlineIdMap();
		AirportIdMap airportIdMap = new AirportIdMap();
		FlightIdMap flightIdMap = new FlightIdMap();

		System.out.println(dao.loadAllAirlines(airlineIdMap));
		System.out.println(dao.loadAllAirports(airportIdMap));
		System.out.println(dao.loadAllFlights(airlineIdMap, flightIdMap, airportIdMap));
	}

}
