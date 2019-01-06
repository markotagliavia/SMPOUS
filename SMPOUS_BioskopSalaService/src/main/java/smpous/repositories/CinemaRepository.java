package smpous.repositories;

import java.io.Serializable;
import java.util.HashSet;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import smpous.models.Cinema;


public interface CinemaRepository extends MongoRepository<Cinema, String>{

	public HashSet<Cinema> findCinemaByName(String name);
}
