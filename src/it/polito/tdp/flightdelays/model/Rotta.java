package it.polito.tdp.flightdelays.model;

import javax.naming.spi.DirStateFactory.Result;

import org.jgrapht.graph.DefaultWeightedEdge;

public class Rotta implements Comparable<Rotta>{
	
	private Airport origine;
	private Airport destinazione;
	private double peso;
	
	
	public Rotta(Airport origine, Airport destinazione, double peso) {
		super();
		this.origine = origine;
		this.destinazione = destinazione;
		this.peso = peso;
	}


	public Airport getOrigine() {
		return origine;
	}


	public void setOrigine(Airport origine) {
		this.origine = origine;
	}


	public Airport getDestinazione() {
		return destinazione;
	}


	public void setDestinazione(Airport destinazione) {
		this.destinazione = destinazione;
	}


	public double getPeso() {
		return peso;
	}


	public void setPeso(double peso) {
		this.peso = peso;
	}


	@Override
	public String toString() {
		return "Airport Origin: " + origine.getName() + "\n" + "Airport Destination: " +destinazione.getName() + "\n" + "Peso: " +  peso + "\n";
		
	}

//hashCode e equals (origin, destination, peso)
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destinazione == null) ? 0 : destinazione.hashCode());
		result = prime * result + ((origine == null) ? 0 : origine.hashCode());
		long temp;
		temp = Double.doubleToLongBits(peso);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Rotta other = (Rotta) obj;
		if (destinazione == null) {
			if (other.destinazione != null)
				return false;
		} else if (!destinazione.equals(other.destinazione))
			return false;
		if (origine == null) {
			if (other.origine != null)
				return false;
		} else if (!origine.equals(other.origine))
			return false;
		if (Double.doubleToLongBits(peso) != Double.doubleToLongBits(other.peso))
			return false;
		return true;
	}

//hashCode e equals (origin, destination)
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((destinazione == null) ? 0 : destinazione.hashCode());
//		result = prime * result + ((origine == null) ? 0 : origine.hashCode());
//		return result;
//	}
//
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Rotta other = (Rotta) obj;
//		if (destinazione == null) {
//			if (other.destinazione != null)
//				return false;
//		} else if (!destinazione.equals(other.destinazione))
//			return false;
//		if (origine == null) {
//			if (other.origine != null)
//				return false;
//		} else if (!origine.equals(other.origine))
//			return false;
//		return true;
//	}


	@Override
	public int compareTo(Rotta r) {

		
		if(this.peso > r.getPeso()) {
			return -1;
		}

		if(this.peso < r.getPeso()) {
			return 1;
		}
		return 0;

		//return (int) (this.peso-r.getPeso());
	}
	
	
	

}
