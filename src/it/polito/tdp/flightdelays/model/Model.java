package it.polito.tdp.flightdelays.model;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.naming.spi.DirStateFactory.Result;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;
import com.sun.javafx.geom.Edge;

import it.polito.tdp.flightdelays.db.FlightDelaysDAO;

public class Model {
	
	private SimpleDirectedWeightedGraph<Airport, DefaultWeightedEdge> graph;
	
	private FlightDelaysDAO dao;
	
	private List<Airline> airlines;
	private List<Airport> airports;
	private List<Flight> flights;
	
	private AirlineIdMap airlineIdMap;
	private AirportIdMap airportIdMap;
	private FlightIdMap flightIdMap;
	
	private List<Rotta> rotte;
	
	


	public Model() {
		
		this.dao = new FlightDelaysDAO();
		
		this.graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		
		this.airlineIdMap = new AirlineIdMap();
		this.airportIdMap = new AirportIdMap();
		this.flightIdMap = new FlightIdMap();
	
		airlines = dao.loadAllAirlines(airlineIdMap);
		//System.out.println(airlines.size());
		
		airports = dao.loadAllAirports(airportIdMap);
		//System.out.println(airports.size());

		flights = dao.loadAllFlights(airlineIdMap, flightIdMap, airportIdMap);
		//System.out.println(flights.size());
		
		this.rotte = new ArrayList<>();

		}


	public void createGraph(Airline airline) {
		
		Airline airLine = airlineIdMap.getAirline(airline);
		
//		airports = dao.loadAllAirports(airportIdMap);
		

		Graphs.addAllVertices(graph, airports);
		
		System.out.println("vertici creati: " + graph.vertexSet().size());
		
		
		
		List<Flight> voliDiAriline = dao.loadFlightsOfAirline(airlineIdMap, flightIdMap, airportIdMap, airLine);
		
		//System.out.println(voliDiAriline.toString());
		
		
		for (Flight volo : voliDiAriline) {

			
//			if(this.graph.containsVertex(airportIdMap.getAirport(volo.getOriginAirportId()))  && this.graph.containsVertex(airportIdMap.getAirport(volo.getDestinationAirportId()))) {
			Airport airportOrigin = airportIdMap.getAirport(volo.getOriginAirportId());
			Airport airportDestination = airportIdMap.getAirport(volo.getDestinationAirportId());
			
			
			
//			double distance = LatLngTool.distance(new LatLng(airportOrigin.getLatitude(), airportOrigin.getLongitude()), new LatLng(airportDestination.getLatitude(), airportDestination.getLongitude()), LengthUnit.KILOMETER);
			
			
//			double distance = calcolaDistanza(airportOrigin, airportDestination);
			
			double distance = volo.getDistance();
			
			
//			double media = dao.calcolaMediaDelayDiTratta(airLine, airportOrigin, airportDestination, airportIdMap, airlineIdMap);
			
			double media = calcolaMedia(airLine, airportOrigin, airportDestination);
			
			
			
			double weight = (media/distance);

			
			Graphs.addEdge(graph, airportOrigin, airportDestination, weight);
			
//			System.out.println("arco : "+this.graph.getEdge(airportOrigin, airportDestination).toString());
//			}else {
//				break;
//			}
		}
		
		System.out.println("archi aggiunti: " + graph.edgeSet().size());
		
		}


	public void creaEOrdinaRotte(){
		
//		List<Flight> trattePeggiori = new ArrayList<>();

//		Set<DefaultWeightedEdge> ListaEdgeOrdinata = this.graph.edgeSet();
		
		for (DefaultWeightedEdge e : this.graph.edgeSet()) {
			
			Airport origin = graph.getEdgeSource(e);
			Airport destination = graph.getEdgeTarget(e);
			double peso = graph.getEdgeWeight(e);
			
			Rotta rotta = new Rotta(origin, destination, peso);
			
			this.rotte.add(rotta);
			
		}
		
//		List<Rotta> rTemp = new ArrayList<>(this.rotte);
		
		Collections.sort(rotte);
			
		
	}
	
	public String stampaLeDieciPeggioriRotte() {
		
		StringBuilder result = new StringBuilder();
	
		result.append("Elenco delle 10 peggiori rotte (in oridine decrescente)\n");
	for(int i=0; i<10 ; i++) {
		
		int n = i+1;
		result.append("\n" + n +") \n" + this.rotte.get(i).toString() + "\n");
		
	}
		return result.toString();
	}
	
	public List<Rotta> getRotte() {
		return rotte;
	}


	public List<Airline> getAirlines() {
		if(this.airlines==null)
			return new ArrayList<Airline>();
		return this.airlines;
	}


	public List<Airport> getAirports() {
		if(this.airports==null)
			return new ArrayList<Airport>();
		return this.airports;
	}


	public List<Flight> getFlights() {
		if(this.flights==null)
			return new ArrayList<Flight>();
		return this.flights;
	}
			
		
		
	private double calcolaMedia(Airline airLine, Airport airportOrigin, Airport airportDestination) {
		return dao.calcolaMediaDelayDiTratta(airlineIdMap.getAirline(airLine), airportIdMap.getAirport(airportOrigin), airportIdMap.getAirport(airportDestination), airportIdMap, airlineIdMap);
	}

 
	public double calcolaDistanza (Airport origin, Airport destination) {
		
		return LatLngTool.distance(new LatLng(origin.getLatitude(), origin.getLongitude()), new LatLng(destination.getLatitude(), destination.getLongitude()), LengthUnit.KILOMETER);
	}
		
		
		
		

		
		
		
	
	
}
