package smpous.servise;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import smpous.models.Movie;
import smpous.repository.MovieRepository;

@Service
public class MovieService extends AbstractCRUDService<Movie, String> {
	private MovieRepository movieRepository;

	@Autowired
	public MovieService(MovieRepository movieRepository) {
		super(movieRepository);
		this.movieRepository = movieRepository;
		
		// TODO Auto-generated constructor stub
	}
	public List<Movie> findAllMovies()
	{
		return movieRepository.findAll();
	}
	/*public HashSet<Movie> findMovieByName(String name)
	{
		return filmRepository.findMovieByName(name);
	}*/
}
