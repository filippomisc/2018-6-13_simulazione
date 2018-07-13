package it.polito.tdp.flightdelays.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		Airline a = new Airline(10, "EV", "Atlantic Southeast Airlines");
		
		model.createGraph(a);
		
		model.creaEOrdinaRotte();
		
//		System.out.println(model.getRotte().toString());
		
		System.out.println(model.stampaLeDieciPeggioriRotte());
		
		
	}

}
