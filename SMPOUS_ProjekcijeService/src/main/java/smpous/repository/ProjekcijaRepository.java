package smpous.repository;

import java.util.HashSet;

import org.springframework.data.mongodb.repository.MongoRepository;

import smpous.models.Cinema;
import smpous.models.Projection;


public interface ProjekcijaRepository extends MongoRepository<Projection, String>{
	public HashSet<Projection> findProjectionsByIdCinema(String idCinema);

	public HashSet<Projection> findProjectionByIdMovie(String idMovie);
}
