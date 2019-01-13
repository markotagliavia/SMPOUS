package smpous.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.HeaderParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mongodb.client.model.geojson.Geometry;
import org.springframework.data.geo.Point;
import com.mongodb.client.model.geojson.Position;

import smpous.models.Address;
import smpous.models.Cinema;
import smpous.models.Rate;
import smpous.models.Theater;
import smpous.models.User;
import smpous.services.BioskopSalaService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("cinemas")
public class BioskopSalaController extends AbstractRESTController<Cinema, String>{

	@Autowired
	Environment environment;
	
	private BioskopSalaService bioskopSalaService;
	
	@Autowired
	public BioskopSalaController(BioskopSalaService bioskopSalaService) {
		super(bioskopSalaService);
		this.bioskopSalaService = bioskopSalaService;
		
	}
	
	@RequestMapping(value = "/hello")
	public String hello(){

		return "hi";
	}
	
	@RequestMapping(value = "/addCinema",method = RequestMethod.POST)
	public Boolean AddCinema(@RequestBody Cinema cinema,@HeaderParam("Username") String username)
	{
		cinema.setId(UUID.randomUUID().toString());
		bioskopSalaService.save(cinema);
		
		/*Cinema cin = new Cinema();
		cin.setId(UUID.randomUUID().toString());
		cin.setRanking(0);
		cin.setName("Test1");
		GeoJsonPoint t = new GeoJsonPoint(40.743827,-73.989015);
		cin.setLocation(t);
		cin.setStreet("Pere Perica");
		cin.setNumber("25");
		Map<String,Rate> rates = new HashMap<String,Rate>();
		//rates.add(Rate.three);
		cin.setRates(rates);
		bioskopSalaService.save(cin);
		
		Cinema cin1 = new Cinema();
		cin1.setId(UUID.randomUUID().toString());
		cin1.setRanking(0);
		cin1.setName("Test1");
		GeoJsonPoint t1 = new GeoJsonPoint(30.743827,-72.989015);
		cin1.setLocation(t1);
		cin1.setStreet("Marka Markovica");
		cin1.setNumber("47");
		//Map<String,Rate> rates = new HashMap<String,Rate>();
		//rates.add(Rate.three);
		cin1.setRates(rates);
		bioskopSalaService.save(cin1);
		
		Cinema cin2 = new Cinema();
		cin2.setId(UUID.randomUUID().toString());
		cin2.setRanking(0);
		cin2.setName("Test1");
		
		GeoJsonPoint t2 = new GeoJsonPoint(35.743827,-71.989015);
		cin2.setLocation(t2);
		cin2.setStreet("Mike Mikica");
		cin2.setNumber("56");
		//Map<String,Rate> rates = new HashMap<String,Rate>();
		//rates.add(Rate.three);
		cin2.setRates(rates);
		bioskopSalaService.save(cin2);*/
		return true;
		
	}
	
	@RequestMapping(value = "/findCinemaById",method = RequestMethod.GET)
	public Cinema FindCinemaById(@RequestParam(name = "id") String id)
	{
		return bioskopSalaService.findCinemaById(id);
	}
	
	@RequestMapping(value = "/findCinemaByName",method = RequestMethod.GET)
	public HashSet<Cinema> FindCinemaByName(@RequestParam(name = "name") String name)
	{
		return bioskopSalaService.findCinemaByName(name);
	}
	
	@RequestMapping(value = "/allCinemas",method = RequestMethod.GET)
	public List<Cinema> GetAllCinemas()
	{
		return bioskopSalaService.findAllCinema();
	}
	
	@RequestMapping(value = "/editCinema",method = RequestMethod.PUT)
	public Boolean EditCinema(@RequestBody Cinema cinema)
	{
		bioskopSalaService.update(cinema.getId(), cinema);
		return true;
	}
	
	@RequestMapping(value = "/deleteCinema",method = RequestMethod.DELETE)
	public Boolean DeleteCinema(@RequestParam(name = "id") String id)
	{
		bioskopSalaService.delete(id);
		return true;
	}
	
	@RequestMapping(value = "/addTheater",method = RequestMethod.POST)
	public Boolean AddTheater(@RequestBody Theater theater,@RequestParam(name = "id") String id,@HeaderParam("Username") String username)
	{
		Cinema cinema = bioskopSalaService.findOne(id);
		theater.setId(UUID.randomUUID().toString());
		HashSet<Theater> theaters = cinema.getTheaters();
		if(theaters == null)
		{
			theaters = new HashSet<Theater>();
		}
		theaters.add(theater);
		cinema.setTheaters(theaters);
		bioskopSalaService.update(cinema.getId(), cinema);
		return true;
	}
	
	@RequestMapping(value = "/deleteTheater",method = RequestMethod.DELETE)
	public Boolean DeleteTheater(@RequestParam(name = "idTheater") String idTheater,@RequestParam(name = "idCinema") String idCinema)
	{
		Cinema cinema = bioskopSalaService.findOne(idCinema);
		HashSet<Theater> theaters = cinema.getTheaters();
		if(theaters != null)
		{
			Theater temp = null;
			for (Theater t : theaters) {
		        if (t.getId().equals(idTheater)) {
		            temp = t;
		            break;
		        }
		    }
			if(temp!= null)
			{
				theaters.remove(temp);
			}
		}
		
		cinema.setTheaters(theaters);
		bioskopSalaService.update(cinema.getId(), cinema);
		return true;
	}
	
	@RequestMapping(value = "/allTheaters",method = RequestMethod.GET)
	public HashSet<Theater> GetAllTheathers(@RequestParam(name = "id") String id)
	{
		return bioskopSalaService.findOne(id).getTheaters();
	}
	
	@RequestMapping(value = "/addRate",method = RequestMethod.POST)
	public Boolean AddRate(@RequestBody Rate rate,@RequestParam(name = "id") String id,@HeaderParam("Username") String username)
	{
		final String uri = "http://localhost:8765/user-service/users/";
	     
	    RestTemplate restTemplate = new RestTemplate();
	    User user = restTemplate.getForObject(uri, User.class);
		Cinema cinema = bioskopSalaService.findCinemaById(id);
		Map<String,Rate> rates = cinema.getRates();
		if(rates == null)
		{
			rates = new HashMap<String,Rate>();
		}
		//rates.add(rate);
		cinema.setRates(rates);
		bioskopSalaService.update(cinema.getId(), cinema);
		return true;
	}
	
	@RequestMapping(value = "/findNear",method = RequestMethod.GET)
	public List<Cinema> FindNearPoint()
	{
		//Position pos = new Position(30.743827,-72.989015);
	    Point point = new Point(35.743826,-71.989015);
		
		return bioskopSalaService.findNearPoint(point, 1.0);
	}
	
	@RequestMapping(value = "/getAverageRate",method = RequestMethod.GET)
	public double GetAverageRate(@RequestParam(name = "id") String id)
	{
		System.out.println("EVOOOOOO ID" + id);
		Cinema cinema = bioskopSalaService.findCinemaById(id);
		int sum = 0;
		if(cinema.getRates() != null)
		{
			for(Rate r: cinema.getRates().values())
			{
				sum += r.ordinal();
			}
			if(sum != 0)
			{
				double res = (double)sum/cinema.getRates().values().size();
				return res;
			}
		}
		
		return 0;
		
	}
}
