package smpous.repository;

import java.util.HashSet;

import org.springframework.data.mongodb.repository.MongoRepository;

import smpous.models.Movie;



public interface MovieRepository extends MongoRepository<Movie, String>{

	public HashSet<Movie> findMovieByName(String name);
	
	public Movie findMovieById(String id);
}
