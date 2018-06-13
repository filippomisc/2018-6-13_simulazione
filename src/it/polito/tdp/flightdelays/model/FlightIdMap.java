package it.polito.tdp.flightdelays.model;

import java.util.HashMap;
import java.util.Map;

public class FlightIdMap {
	
private Map<Integer, Flight> flights;
	
	public FlightIdMap() {
		this.flights = new HashMap<>();
	}	

	
	public Flight getFlightByID(int id) {
		return this.flights.get(id);
	}
	
	public Flight getFlight(Flight f) {
		Flight old = flights.get(f.getId());
		if(old==null) {
			this.flights.put(f.getId(), f);
			return f;
		}
		return old;
	}
	
	public void put(Flight f, int flightid) {
		this.flights.put(flightid, f);
	}

}
