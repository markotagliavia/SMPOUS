package smpous.repositories;

import java.util.HashSet;
import java.util.List;

import org.springframework.data.geo.Distance;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.mongodb.repository.MongoRepository;


import org.springframework.data.geo.Point;

import smpous.models.Cinema;


public interface CinemaRepository extends MongoRepository<Cinema, String>{

	public HashSet<Cinema> findCinemaByName(String name);
	
	public Cinema findCinemaById(String id);

	public List<Cinema> findByLocationNear(Point point, Distance distance);
	
	public List<Cinema> findByLocationWithin(GeoJsonPolygon polygon);
	//public void find(Bson bson);	
	
}
