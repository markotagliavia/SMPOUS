package smpous.models;

import java.io.Serializable;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("serial")
@Document
public class Address implements Serializable{
	
	public Address()
	{
		
	}
	
	public Address(String street, String number, GeoJsonPoint location) {
		super();
		this.street = street;
		this.number = number;
		this.location = location;
	}
	private String street;
	private String number;
	@GeoSpatialIndexed(type=GeoSpatialIndexType.GEO_2DSPHERE)
	private GeoJsonPoint location;
	
	public GeoJsonPoint getLocation() {
		return location;
	}

	public void setLocation(GeoJsonPoint location) {
		this.location = location;
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
