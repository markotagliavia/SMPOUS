package smpous.models;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("serial")
@Document
public class Chair implements Serializable{
	
	public Chair()
	{
		
	}
	
	
	
	public Chair(int id, int serialNumber, int column, int row) {
		super();
		this.id = id;
		this.serialNumber = serialNumber;
		this.column = column;
		this.row = row;
	}



	@Id
	private int id;
	   
	private int serialNumber;
	   
	private int column;
	   
	private int row;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
}

