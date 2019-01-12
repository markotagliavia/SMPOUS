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

	public Theater(String id, String name, int capacity, TheaterType theaterType, int chairsPerRow, int chairsPerColumn) {
		super();
		this.id = id;
		this.name = name;
		this.capacity = capacity;
		this.theaterType = theaterType;
		this.chairsPerRow = chairsPerRow;
		this.chairsPerColumn = chairsPerColumn;
	}



	@Id
	private String id;

	private String name;

	private int capacity;

	private TheaterType theaterType;
	  
	public int chairsPerRow;
	
	public int chairsPerColumn;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
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

	public int getChairsPerRow() {
		return chairsPerRow;
	}

	public void setChairsPerRow(int chairsPerRow) {
		this.chairsPerRow = chairsPerRow;
	}

	public int getChairsPerColumn() {
		return chairsPerColumn;
	}

	public void setChairsPerColumn(int chairsPerColumn) {
		this.chairsPerColumn = chairsPerColumn;
	}
	
	
}
