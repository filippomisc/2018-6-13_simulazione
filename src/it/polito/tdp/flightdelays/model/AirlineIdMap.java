package it.polito.tdp.flightdelays.model;

import java.util.HashMap;
import java.util.Map;

public class AirlineIdMap {

private Map<String, Airline> airline;
	
	public AirlineIdMap() {
		this.airline = new HashMap<>();
	}	

	
	public Airline getAirlineByID(String id) {
		return this.airline.get(id);
	}
	
	public Airline getAirline(Airline a) {
		Airline old = airline.get(a.getId());
		if(old==null) {
			this.airline.put(a.getId(), a);
			return a;
		}
		return old;
	}
	
	public void put(Airline a, String airlineid) {
		this.airline.put(airlineid, a);
	}
	
}
