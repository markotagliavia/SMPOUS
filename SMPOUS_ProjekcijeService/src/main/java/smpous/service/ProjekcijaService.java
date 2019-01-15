package smpous.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import smpous.models.Cinema;
import smpous.models.Projection;
import smpous.repository.ProjekcijaRepository;

@Service
public class ProjekcijaService extends AbstractCRUDService<Projection, String> {
	private ProjekcijaRepository projekcijaRepository;

	public ProjekcijaService(ProjekcijaRepository repo) {
		super(repo);
		this.projekcijaRepository = repo;
	}
	
	public List<Projection> findAllProjekcije()
	{
		return projekcijaRepository.findAll();
	}

	public HashSet<Projection> findProjectionByIdCinema(String idCinema) {
		return projekcijaRepository.findProjectionsByIdCinema(idCinema);
	}

	public HashSet<Projection> findProjectionByIdMovie(String idMovie) {
		return projekcijaRepository.findProjectionByIdMovie(idMovie);
	}
	
}
