package smpous.servise;

import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import smpous.models.Movie;
import sumpous.repository.MovieRepository;

@Service
public class FilmService extends AbstractCRUDService<Movie, String> {
	private MovieRepository filmRepository;

	public FilmService(MovieRepository repo) {
		super(repo);
		this.filmRepository = repo;
		
		// TODO Auto-generated constructor stub
	}
	public List<Movie> findAllMovies()
	{
		return filmRepository.findAll();
	}
	/*public HashSet<Movie> findMovieByName(String name)
	{
		return filmRepository.findMovieByName(name);
	}*/
}
