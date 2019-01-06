package smpous.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;


import org.springframework.beans.BeanUtils;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import smpous.models.Cinema;
import smpous.repositories.CinemaRepository;

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
	

}
