package smpous.service;

import java.util.HashSet;
import java.util.List;

import smpous.models.Cinema;
import smpous.models.Projection;
import smpous.repository.ProjekcijaRepository;

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

	public HashSet<Projection> findProjectionByCinema(Cinema cinema) {
		return projekcijaRepository.findProjectionsByCinema(cinema);
	}
}
