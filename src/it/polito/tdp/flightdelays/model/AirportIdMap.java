package it.polito.tdp.flightdelays.model;

import java.util.*;

public class AirportIdMap {
	
private Map<String, Airport> airports;
	
	public AirportIdMap() {
		this.airports = new HashMap<>();
	}	

	
	public Airport getAirportByID(String id) {
		return this.airports.get(id);
	}
	
	public Airport getAirport(Airport a) {
		Airport old = airports.get(a.getId());
		if(old==null) {
			this.airports.put(a.getId(), a);
			return a;
		}
		return old;
	}
	
	public void put(Airport a, String airlineId) {
		this.airports.put(airlineId, a);
	}
	

}
