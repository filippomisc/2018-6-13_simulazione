package it.polito.tdp.flightdelays.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.flightdelays.model.Airline;
import it.polito.tdp.flightdelays.model.AirlineIdMap;
import it.polito.tdp.flightdelays.model.Airport;
import it.polito.tdp.flightdelays.model.AirportIdMap;
import it.polito.tdp.flightdelays.model.Flight;
import it.polito.tdp.flightdelays.model.FlightIdMap;

public class FlightDelaysDAO {

	public List<Airline> loadAllAirlines(AirlineIdMap aIdMap) {
		String sql = "SELECT id, iata_code, airline from airlines";
		List<Airline> result = new ArrayList<Airline>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
			
				Airline airline = new Airline(rs.getInt("ID"), rs.getString("iata_code"), rs.getString("airline"));
				
				result.add(aIdMap.getAirline(airline));
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Airport> loadAllAirports(AirportIdMap aIdMap) {
		String sql = "SELECT id, iata_code, airport, city, state, country, latitude, longitude, timezone_offset FROM airports";
		List<Airport> result = new ArrayList<Airport>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				Airport airport = new Airport(rs.getInt("id"), rs.getString("iata_code") ,rs.getString("airport"), rs.getString("city"), rs.getString("state"), rs.getString("country"), rs.getDouble("latitude"), rs.getDouble("longitude"), rs.getDouble("timezone_offset"));
				
				result.add(aIdMap.getAirport(airport));
			}
			
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Flight> loadAllFlights(AirlineIdMap aLIdMap, FlightIdMap fIdMap, AirportIdMap aPIdMap) {
		String sql = "SELECT id, airline_id, flight_number, origin_airport_id, destination_airport_id, scheduled_departure_date, "
				+ "arrival_date, departure_delay, arrival_delay, elapsed_time, distance FROM flights";
		List<Flight> result = new LinkedList<Flight>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				Airport airportOrigin = aPIdMap.getAirportByID(rs.getInt("origin_airport_id"));
				Airport airportDestination = aPIdMap.getAirportByID(rs.getInt("destination_airport_id"));
				Airline airline = aLIdMap.getAirlineByID(rs.getInt("airline_id"));

				
				Flight flight = new Flight(rs.getInt("id"), airline, rs.getInt("flight_number"), airportOrigin, airportDestination, rs.getTimestamp("scheduled_departure_date").toLocalDateTime(), rs.getTimestamp("arrival_date").toLocalDateTime(), rs.getInt("departure_delay"), rs.getInt("arrival_delay"), rs.getInt("elapsed_time"), rs.getInt("distance"));
				result.add(fIdMap.getFlight(flight));
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
	
	
	public List<Flight> loadFlightsOfAirline(AirlineIdMap aLineIdMap, FlightIdMap flyIdMap, AirportIdMap airportIdMap, Airline aL) {
		String sql = "select distinct flights.ID, flights.AIRLINE_ID,flights.FLIGHT_NUMBER, flights.origin_airport_id, flights.destination_airport_id, flights.scheduled_departure_date, flights.arrival_date, flights.departure_delay, flights.arrival_delay, flights.elapsed_time, flights.distance " + 
				"from airlines, flights " + 
				"where airlines.ID=? " + 
				"and airlines.ID= flights.AIRLINE_ID ";
		List<Flight> result = new LinkedList<Flight>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, aL.getId());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				Airport airportOrigin = airportIdMap.getAirportByID(rs.getInt("origin_airport_id"));
				Airport airportDestination = airportIdMap.getAirportByID(rs.getInt("destination_airport_id"));
				Airline airline = aLineIdMap.getAirlineByID(rs.getInt("airline_ID"));

				
				Flight flight = new Flight(rs.getInt("id"), airline, rs.getInt("flight_number"),
						airportOrigin, airportDestination,
						rs.getTimestamp("scheduled_departure_date").toLocalDateTime(),
						rs.getTimestamp("arrival_date").toLocalDateTime(), rs.getInt("departure_delay"),
						rs.getInt("arrival_delay"), rs.getInt("elapsed_time"), rs.getInt("distance"));
				result.add(flyIdMap.getFlight(flight));
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
		
	}

	public double calcolaMediaDelayDiTratta(Airline airline, Airport origin, Airport destination,AirportIdMap airportIdMap,AirlineIdMap airlineIdMap) {
		String sql = "select  AVG(flights.ARRIVAL_DELAY) as ritardi " + 
				"FROM flights " + 
				"WHERE flights.AIRLINE_ID =? " + 
				"AND flights.ORIGIN_AIRPORT_ID =? " + 
				"AND flights.DESTINATION_AIRPORT_ID =? ";	
		double result = 0.0;

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, airline.getId());
			st.setInt(2, origin.getId());
			st.setInt(3, destination.getId());
			
			
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				result = rs.getDouble("ritardi");
				
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
		
	}
}
