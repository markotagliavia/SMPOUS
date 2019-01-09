package smpous.models;

import java.io.Serializable;
import java.util.HashSet;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("serial")
@Document
public class Theater implements Serializable{

	public Theater()
	{
		
	}
	
	
	
	public HashSet<Chair> getChair() {
		return chair;
	}



	public void setChair(HashSet<Chair> chair) {
		this.chair = chair;
	}



	public Theater(String id, int name, int capacity, TheaterType theaterType, HashSet<Chair> chair) {
		super();
		this.id = id;
		this.name = name;
		this.capacity = capacity;
		this.theaterType = theaterType;
		this.chair = chair;
	}



	@Id
	private String id;

	private int name;

	private int capacity;

	private TheaterType theaterType;
	  
	public HashSet<Chair> chair;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public TheaterType getTheaterType() {
		return theaterType;
	}

	public void setTheaterType(TheaterType theaterType) {
		this.theaterType = theaterType;
	}
}
