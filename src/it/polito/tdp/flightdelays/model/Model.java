package it.polito.tdp.flightdelays.model;



import java.util.Iterator;
import java.util.List;

import javax.tools.Diagnostic;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

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
	
	


	public Model() {
		
		this.dao = new FlightDelaysDAO();
		
		this.graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		
		this.airlineIdMap = new AirlineIdMap();
		this.airportIdMap = new AirportIdMap();
		this.flightIdMap = new FlightIdMap();
	
		}


	public void createGraph(Airline airline) {
		
		
		this.airports = dao.loadAllAirports(airportIdMap);
		
		this.graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(graph, airports);
		System.out.println("numero di vertici creati: \n" + graph.vertexSet().size());
		
		
		for (Flight flight : flights) {
			
			Airport AirportDestination = flight.getDestinationAirportId();
			Airport airportOrigin = flight.getOriginAirportId();
			
			
			
		}
			
		}
		
		
		
		
		
		
		

		
		
		
	
	
}
