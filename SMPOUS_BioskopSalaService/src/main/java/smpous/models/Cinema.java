package smpous.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
@SuppressWarnings("serial")
@Document
public class Cinema implements Serializable{
	
	public Cinema()
	{
		
	}
	
	
	
	public Cinema(int id, String name, Address address, ArrayList<Rate> rates, int ranking, HashSet<Theater> theater) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.rates = rates;
		this.ranking = ranking;
		this.theater = theater;
	}



	@Id
	private int id;
   
	private String name;
	   
	private Address address;
	   
	private ArrayList<Rate> rates;
	   
	private int ranking;
	   
	public HashSet<Theater> theater;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public ArrayList<Rate> getRates() {
		return rates;
	}

	public void setRates(ArrayList<Rate> rates) {
		this.rates = rates;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
}

