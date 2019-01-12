package smpous.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.data.geo.Distance;
import org.springframework.stereotype.Service;

import smpous.models.Cinema;
import smpous.repositories.CinemaRepository;
import org.springframework.data.geo.Point;

@Service
public class BioskopSalaService extends AbstractCRUDService<Cinema, String>{
	
	private CinemaRepository cinemaRepository;
	
	public BioskopSalaService(CinemaRepository repo) {
		super(repo);
		this.cinemaRepository = repo;
		// TODO Auto-generated constructor stub
	}

	public HashSet<Cinema> findCinemaByName(String name)
	{
		return cinemaRepository.findCinemaByName(name);
	}
	
	public Cinema findCinemaById(String id)
	{
		return cinemaRepository.findCinemaById(id);
	}
	
	public List<Cinema> findAllCinema()
	{
		return cinemaRepository.findAll();
	}
	
	public List<Cinema> findNearPoint(Point p,Double maxDistance)
	{
		Distance distance = new Distance(maxDistance);
		return cinemaRepository.findByLocationNear(p, distance);

	}

}
