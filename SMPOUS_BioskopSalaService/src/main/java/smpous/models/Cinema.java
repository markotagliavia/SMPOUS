package smpous.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;

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

	public Cinema(String id, String name, Address address, Map<String,Rate> rates, int ranking,
			HashSet<Theater> theaters) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.rates = rates;
		this.ranking = ranking;
		this.theaters = theaters;
	}



	@Id
	private String id;
	
	public String getId() {
		return id;
	}

	public HashSet<Theater> getTheaters() {
		return theaters;
	}


	public void setTheaters(HashSet<Theater> theaters) {
		this.theaters = theaters;
	}


	public void setId(String id) {
		this.id = id;
	}

   
	private String name;
	   
	private Address address;
	   
	private Map<String,Rate> rates;
	   
	private int ranking;
	   
	public HashSet<Theater> theaters;


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

	public Map<String,Rate> getRates() {
		return rates;
	}

	public void setRates(Map<String,Rate> rates) {
		this.rates = rates;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	
}

