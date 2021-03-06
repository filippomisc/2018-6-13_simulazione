package it.polito.tdp.flightdelays.model;

import java.time.LocalDateTime;

public class Flight {

	private int id;
	private Airline airline;
	private int flightNumber;
	private Airport originAirportId;
	private Airport destinationAirportId;
	private LocalDateTime scheduledDepartureDate;
	private LocalDateTime arrivalDate;
	private int departureDelay;
	private int arrivalDelay;
	private int airTime;
	private int distance;
	
	
	public Flight(int id, Airline airline, int flightNumber, Airport originAirportId, Airport destinationAirportId,
			LocalDateTime scheduledDepartureDate, LocalDateTime arrivalDate, int departureDelay, int arrivalDelay,
			int airTime, int distance) {
		super();
		this.id = id;
		this.airline = airline;
		this.flightNumber = flightNumber;
		this.originAirportId = originAirportId;
		this.destinationAirportId = destinationAirportId;
		this.scheduledDepartureDate = scheduledDepartureDate;
		this.arrivalDate = arrivalDate;
		this.departureDelay = departureDelay;
		this.arrivalDelay = arrivalDelay;
		this.airTime = airTime;
		this.distance = distance;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Airline getAirline() {
		return airline;
	}


	public void setAirline(Airline airline) {
		this.airline = airline;
	}


	public int getFlightNumber() {
		return flightNumber;
	}


	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}


	public Airport getOriginAirportId() {
		return originAirportId;
	}


	public void setOriginAirportId(Airport originAirportId) {
		this.originAirportId = originAirportId;
	}


	public Airport getDestinationAirportId() {
		return destinationAirportId;
	}


	public void setDestinationAirportId(Airport destinationAirportId) {
		this.destinationAirportId = destinationAirportId;
	}


	public LocalDateTime getScheduledDepartureDate() {
		return scheduledDepartureDate;
	}


	public void setScheduledDepartureDate(LocalDateTime scheduledDepartureDate) {
		this.scheduledDepartureDate = scheduledDepartureDate;
	}


	public LocalDateTime getArrivalDate() {
		return arrivalDate;
	}


	public void setArrivalDate(LocalDateTime arrivalDate) {
		this.arrivalDate = arrivalDate;
	}


	public int getDepartureDelay() {
		return departureDelay;
	}


	public void setDepartureDelay(int departureDelay) {
		this.departureDelay = departureDelay;
	}


	public int getArrivalDelay() {
		return arrivalDelay;
	}


	public void setArrivalDelay(int arrivalDelay) {
		this.arrivalDelay = arrivalDelay;
	}


	public int getAirTime() {
		return airTime;
	}


	public void setAirTime(int airTime) {
		this.airTime = airTime;
	}


	public int getDistance() {
		return distance;
	}


	public void setDistance(int distance) {
		this.distance = distance;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		if (id != other.id)
			return false;
		return true;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Flight [id=");
		builder.append(id);
		builder.append(", airlineId=");
		builder.append(airline);
		builder.append(", flightNumber=");
		builder.append(flightNumber);
		builder.append(", originAirportId=");
		builder.append(originAirportId);
		builder.append(", destinationAirportId=");
		builder.append(destinationAirportId);
		builder.append(", scheduledDepartureDate=");
		builder.append(scheduledDepartureDate);
		builder.append(", arrivalDate=");
		builder.append(arrivalDate);
		builder.append(", departureDelay=");
		builder.append(departureDelay);
		builder.append(", arrivalDelay=");
		builder.append(arrivalDelay);
		builder.append(", airTime=");
		builder.append(airTime);
		builder.append(", distance=");
		builder.append(distance);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
