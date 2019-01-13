package smpous.models;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

@SuppressWarnings("serial")
@Document
public class User implements Serializable{
	
	public User()
	{
		
	}
	
	
	
	public User(String id, String name, String lastname, String username, String password, Date registrationDay,
			Date birthday, Boolean gender, Boolean isActive, double x, double y, String street, int number, String  typeOfUser, GeoJsonPoint address) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.registrationDay = registrationDay;
		this.birthday = birthday;
		this.gender = gender;
		this.isActive = isActive;
		this.x = x;
		this.y = y;
		this.street = street;
		this.number = number;
		this.typeOfUser = typeOfUser;
		this.address = new GeoJsonPoint(x, y);
	}



	@Id
	private String id;
	private String name;
	private String lastname;
	private String username;
	private String password;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date registrationDay;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	private Boolean gender;
	private Boolean isActive;
	@GeoSpatialIndexed(type=GeoSpatialIndexType.GEO_2DSPHERE)
	private GeoJsonPoint address;
	private double x;
	private double y;
	private String street;
	private int number;
	private String typeOfUser;
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
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getRegistrationDay() {
		return registrationDay;
	}
	public void setRegistrationDay(Date registrationDay) {
		this.registrationDay = registrationDay;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Boolean getGender() {
		return gender;
	}
	public void setGender(Boolean gender) {
		this.gender = gender;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public GeoJsonPoint getAddress() {
		return address;
	}
	public void setAddress(GeoJsonPoint address) {
		this.address = address;
	}
	public String getTypeOfUser() {
		return typeOfUser;
	}
	public void setTypeOfUser(String typeOfUser) {
		this.typeOfUser = typeOfUser;
	}



	public double getX() {
		return x;
	}



	public void setX(double x) {
		this.x = x;
	}



	public double getY() {
		return y;
	}



	public void setY(double y) {
		this.y = y;
	}



	public String getStreet() {
		return street;
	}



	public void setStreet(String street) {
		this.street = street;
	}



	public int getNumber() {
		return number;
	}



	public void setNumber(int number) {
		this.number = number;
	}
}

