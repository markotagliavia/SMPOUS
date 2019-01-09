package smpous.models;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("serial")
@Document
public class Projection implements Serializable{

	public Projection()
	{
		
	}
	
	
	
	public Projection(String id, Date dateAndTime, Boolean isPremiere, Boolean isActive, int idCinema, int idTheatre,
			int idMovie, String cinemaName, String theatreName, String movieName) {
		super();
		this.id = id;
		this.dateAndTime = dateAndTime;
		this.isPremiere = isPremiere;
		this.isActive = isActive;
		this.idCinema = idCinema;
		this.idTheatre = idTheatre;
		this.idMovie = idMovie;
		this.cinemaName = cinemaName;
		this.theatreName = theatreName;
		this.movieName = movieName;
	}



	@Id
	private String id;
	
	private Date dateAndTime;
	   
	private Boolean isPremiere;
	   
	private Boolean isActive;
	   
	private int idCinema;
	   
	private int idTheatre;
	   
	private int idMovie;
	   
	private String cinemaName;
	  
	private String theatreName;
	   
	private String movieName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public Boolean getIsPremiere() {
		return isPremiere;
	}

	public void setIsPremiere(Boolean isPremiere) {
		this.isPremiere = isPremiere;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public int getIdCinema() {
		return idCinema;
	}

	public void setIdCinema(int idCinema) {
		this.idCinema = idCinema;
	}

	public int getIdTheatre() {
		return idTheatre;
	}

	public void setIdTheatre(int idTheatre) {
		this.idTheatre = idTheatre;
	}

	public int getIdMovie() {
		return idMovie;
	}

	public void setIdMovie(int idMovie) {
		this.idMovie = idMovie;
	}

	public String getCinemaName() {
		return cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}
}

