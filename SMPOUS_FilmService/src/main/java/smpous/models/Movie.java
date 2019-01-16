package smpous.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("serial")
@Document
public class Movie implements Serializable{
	
		public Movie()
		{
			
		}
		
		public Movie(String id, String name, String description, int durationIMinuts, String language, Map<String,Rate> rates,
				Genre genre, Date datePremier, HashSet<Person> directors, HashSet<Person> actors) {
			super();
			this.id = id;
			this.name = name;
			this.description = description;
			this.durationIMinuts = durationIMinuts;
			this.language = language;
			this.rates = rates;
			this.genre = genre;
			this.datePremier = datePremier;
			this.directors = directors;
			this.actors = actors;
		}
		@Id
		private String id;
	   
		private String name;
	  
		private String description;
	   
		private int durationIMinuts;
		   
		private String language;
		   
		private Map<String,Rate> rates;
		   
		private Genre genre;
		   
		private Date datePremier;
		   
		private HashSet<Person> directors;
		   
		private HashSet<Person> actors;

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
	
		public String getDescription() {
			return description;
		}
	
		public void setDescription(String description) {
			this.description = description;
		}
	
		public int getDurationIMinuts() {
			return durationIMinuts;
		}
	
		public void setDurationIMinuts(int durationIMinuts) {
			this.durationIMinuts = durationIMinuts;
		}
	
		public String getLanguage() {
			return language;
		}
	
		public void setLanguage(String language) {
			this.language = language;
		}
	
		public Map<String,Rate> getRates() {
			return rates;
		}
	
		public void setRates(Map<String,Rate> rates) {
			this.rates = rates;
		}
	
		public Genre getGenre() {
			return genre;
		}
	
		public void setGenre(Genre genre) {
			this.genre = genre;
		}
	
		public Date getDatePremier() {
			return datePremier;
		}
	
		public void setDatePremier(Date datePremier) {
			this.datePremier = datePremier;
		}
	
		public HashSet<Person> getDirectors() {
			return directors;
		}
	
		public void setDirectors(HashSet<Person> directors) {
			this.directors = directors;
		}
	
		public HashSet<Person> getActors() {
			return actors;
		}
	
		public void setActors(HashSet<Person> actors) {
			this.actors = actors;
		}
}
