package it.polito.tdp.flightdelays.model;

import java.util.*;

public class AirportIdMap {
	
private Map<Integer, Airport> airports;
	
	public AirportIdMap() {
		this.airports = new HashMap<>();
	}	

	
	public Airport getAirportByID(int id) {
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
	
	public void put(Airport a, int airlineId) {
		this.airports.put(airlineId, a);
	}
	
	public boolean containsKey(int id) {
		return airports.containsKey(id);
	}
	

}
