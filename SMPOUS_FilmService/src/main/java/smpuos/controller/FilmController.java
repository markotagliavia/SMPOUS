package smpuos.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

import smpous.models.Movie;
import smpous.servise.FilmService;



@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("movies")
public class FilmController extends AbstractRESTController<Movie, String>{

	@Autowired
	Environment environment;
	
	private FilmService FilmService;
	public FilmController(FilmService service) {
		super(service);
		this.FilmService = service;
	
		// TODO Auto-generated constructor stub
	}

	@RequestMapping(value = "/addFilm",method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Boolean AddFilm(@RequestBody Movie film,@RequestHeader(value="Username") String username)
	{
		if(IsAuthorized("admin",username)== false)
		{
			return false;
		}
		film.setId(UUID.randomUUID().toString());
		FilmService.save(film);
		
		
		return true;
		
	}
	private Boolean IsAuthorized(String r,String username)
	{
		if(username == null)
		{
			return false;
		}
		RestTemplate restTemplate = new RestTemplate();
		final String uri = "http://localhost:8765/user-service/users/getUserRole?username="+username;
		String rola = restTemplate.getForObject(uri, String.class);
		if(!rola.equals(r))
		{
			return false;
		}
		return true;
	}
	@RequestMapping(value = "/editMovie",method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	public Boolean EditFilm(@RequestBody Movie movie,@RequestHeader(value="Username") String username)
	{
		if(IsAuthorized("admin",username)== false)
		{
			return false;
		}
		FilmService.update(movie.getId(), movie);
		return true;
}
	@RequestMapping(value = "/deleteFilm",method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	public Boolean DeleteFilm(@RequestParam(name = "id") String id,@RequestHeader(value="Username") String username)
	{
		if(IsAuthorized("admin",username)== false)
		{
			return false;
		}
		FilmService.delete(id);
		return true;
	}
	@RequestMapping(value = "/allMovies",method = RequestMethod.GET)
	public List<Movie> GetAllMovies()
	{
		return FilmService.findAllMovies();
	}
	@RequestMapping(value = "/actualMovies",method = RequestMethod.GET)
	public List<Movie> actualMovies()
	{
		List<Movie> sve =  FilmService.findAllMovies();
		List<Movie> actual = new ArrayList<>();
		for(Movie film1 : sve) {
			if(film1.getDatePremier().getYear()==(new Date()).getYear()) actual.add(film1);
		} 
		return actual;
	}
	
	@RequestMapping(value = "/poGodiniPremiere",method = RequestMethod.GET)
	public List<Movie> poGodiniPremiere(@RequestParam(name = "year") int year)
	{
		List<Movie> sve =  FilmService.findAllMovies();
		List<Movie> poGodiniPremiere = new ArrayList<>();
		for(Movie film1 : sve) {
			if(film1.getDatePremier().getYear()==year) poGodiniPremiere.add(film1);
		} 
		return poGodiniPremiere;
	}
}