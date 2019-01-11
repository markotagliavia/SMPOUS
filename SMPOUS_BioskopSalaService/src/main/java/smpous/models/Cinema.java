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



	public Cinema(String id, String name, GeoJsonPoint location, String street, String number, Map<String, Rate> rates,
			int ranking, HashSet<Theater> theaters) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.street = street;
		this.number = number;
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
	@GeoSpatialIndexed(type=GeoSpatialIndexType.GEO_2DSPHERE)
	private GeoJsonPoint location;
	
	private String street;
	private String number;
	
	public GeoJsonPoint getLocation() {
		return location;
	}

	public void setLocation(GeoJsonPoint location) {
		this.location = location;
	}
	
	   
	private Map<String,Rate> rates;
	   
	private int ranking;
	   
	public HashSet<Theater> theaters;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	
}

