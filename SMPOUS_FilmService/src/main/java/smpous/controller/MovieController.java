package smpous.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;

import smpous.models.Movie;
import smpous.models.Person;
import smpous.models.Rate;
import smpous.servise.MovieService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("movies")
public class MovieController extends AbstractRESTController<Movie, String> {

	@Autowired
	Environment environment;

	private MovieService movieService;

	@Autowired
	public MovieController(MovieService movieService) {
		super(movieService);
		this.movieService = movieService;

		// TODO Auto-generated constructor stub
	}

	@RequestMapping(value = "/addMovie", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Boolean AddFilm(@RequestBody Movie film, @RequestHeader(value = "Username") String username) {
		if (IsAuthorized("admin", username) == false) {
			return false;
		}
		film.setId(UUID.randomUUID().toString());
		movieService.save(film);

		return true;

	}

	private Boolean IsAuthorized(String r, String username) {
		if (username == null) {
			return false;
		}
		RestTemplate restTemplate = new RestTemplate();
		final String uri = "http://localhost:8765/user-service/users/getUserRole?username=" + username;
		String rola = restTemplate.getForObject(uri, String.class);
		if (!rola.equals(r)) {
			return false;
		}
		return true;
	}

	@RequestMapping(value = "/editMovie", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	public Boolean EditFilm(@RequestBody Movie movie, @RequestHeader(value = "Username") String username) {
		if (IsAuthorized("admin", username) == false) {
			return false;
		}
		movieService.update(movie.getId(), movie);
		return true;
	}

	@RequestMapping(value = "/deleteMovie", method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	public Boolean DeleteMovie(@RequestParam(name = "id") String id,
			@RequestHeader(value = "Username") String username) {
		if (IsAuthorized("admin", username) == false) {
			return false;
		}
		movieService.delete(id);
		return true;
	}

	@RequestMapping(value = "/allMovies", method = RequestMethod.GET)
	public List<Movie> GetAllMovies() {
		return movieService.findAllMovies();
	}

	@RequestMapping(value = "/actualMovies", method = RequestMethod.GET)
	public List<Movie> actualMovies() {
		List<Movie> sve = movieService.findAllMovies();
		List<Movie> actual = new ArrayList<>();
		for (Movie film1 : sve) {
			if (film1.getDatePremier().getYear() == (new Date()).getYear())
				actual.add(film1);
		}
		return actual;
	}

	@RequestMapping(value = "/poGodiniPremiere", method = RequestMethod.GET)
	public List<Movie> poGodiniPremiere(@RequestParam(name = "year") int year) {
		List<Movie> sve = movieService.findAllMovies();
		List<Movie> poGodiniPremiere = new ArrayList<>();
		for (Movie film1 : sve) {
			if (film1.getDatePremier().getYear() == year)
				poGodiniPremiere.add(film1);
		}
		return poGodiniPremiere;
	}

	@RequestMapping(value = "/perdirector", method = RequestMethod.GET)
	public List<Movie> perdirectors(@RequestParam(name = "director") String idDirector) {
		List<Movie> sve = movieService.findAllMovies();
		List<Movie> perdirectors = new ArrayList<>();
		for (Movie film1 : sve) {
			for (Person director : film1.getDirectors()) {
				if (director.getId().equals(idDirector))
					perdirectors.add(film1);
			}
		}
		return perdirectors;
	}

	@RequestMapping(value = "/peractor", method = RequestMethod.GET)
	public List<Movie> peractors(@RequestParam(name = "actor") String idActor) {
		List<Movie> sve = movieService.findAllMovies();
		List<Movie> peractors = new ArrayList<>();
		for (Movie film1 : sve) {
			for (Person actor : film1.getActors()) {
				if (actor.getId().equals(idActor))
					peractors.add(film1);
			}
		}
		return peractors;
	}

	@RequestMapping(value = "/addRate", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Boolean AddRate(@RequestBody ObjectNode obj, @RequestParam(name = "id") String id,
			@RequestHeader(value = "Username") String username) {
		Rate rate = Rate.getRate(obj.get("rate").asInt());
		final String uri = "localhost:8765/user-service/users/getUserId?username=" + username;

		RestTemplate restTemplate = new RestTemplate();
		String user = restTemplate.getForObject(uri, String.class);
		if (user.equals("")) {
			return false;
		}
		Movie movie = movieService.findMovieById(id);
		Map<String, Rate> rates = movie.getRates();
		if (rates == null) {
			rates = new HashMap<String, Rate>();
		}
		if (rates.containsKey(username)) {
			return false;
		}
		rates.put(username, rate);
		movie.setRates(rates);
		movieService.update(movie.getId(), movie);
		return true;
	}

	@RequestMapping(value = "/perRateMovies", method = RequestMethod.GET)
	public List<Movie> perRateMovies() {
		List<Movie> svii = movieService.findAllMovies();
		Collections.sort(svii, new Comparator<Movie>() {

			@Override
			public int compare(Movie o1, Movie o2) {
				return averageRating(o1) < averageRating(o2) ? 0 : 1;
			}

		});

		return svii;
	}

	private double averageRating(Movie m) {
		double sum = 0;
		int num = 0;
		for (Rate r : m.getRates().values()) {
			sum += r.ordinal();
			num++;
		}
		return sum / (double) num;
	}
}