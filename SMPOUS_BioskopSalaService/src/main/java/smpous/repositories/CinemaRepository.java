package smpous.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import smpous.models.Cinema;


public interface CinemaRepository extends MongoRepository<Cinema, String>{

}
