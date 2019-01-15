package smpous.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.core.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
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
import com.mongodb.client.model.geojson.Geometry;
import org.springframework.data.geo.Point;
import com.mongodb.client.model.geojson.Position;

import smpous.models.Address;
import smpous.models.Cinema;
import smpous.models.Rate;
import smpous.models.Theater;
import smpous.models.User;
import smpous.services.BioskopSalaService;

@CrossOrigin(origins = "*", maxAge = 3600)
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
	
	@RequestMapping(value = "/addCinema",method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Boolean AddCinema(@RequestBody Cinema cinema,@RequestHeader(value="Username") String username)
	{
		if(IsAuthorized("admin",username)== false)
		{
			return false;
		}
		cinema.setTheaters(new HashSet<Theater>());
		cinema.setRates(new HashMap<String,Rate>());
		cinema.setId(UUID.randomUUID().toString());
		bioskopSalaService.save(cinema);
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
	
	@RequestMapping(value = "/editCinema",method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	public Boolean EditCinema(@RequestBody Cinema cinema,@RequestHeader(value="Username") String username)
	{
		if(IsAuthorized("admin",username)== false)
		{
			return false;
		}
		bioskopSalaService.update(cinema.getId(), cinema);
		return true;
	}
	
	@RequestMapping(value = "/deleteCinema",method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	public Boolean DeleteCinema(@RequestParam(name = "id") String id,@RequestHeader(value="Username") String username)
	{
		if(IsAuthorized("admin",username)== false)
		{
			return false;
		}
		bioskopSalaService.delete(id);
		return true;
	}
	
	@RequestMapping(value = "/addTheater",method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Boolean AddTheater(@RequestBody Theater theater,@RequestParam(name = "id") String id,@RequestHeader(value="Username") String username)
	{
		if(IsAuthorized("admin",username)== false)
		{
			return false;
		}
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
	
	@RequestMapping(value = "/deleteTheater",method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	public Boolean DeleteTheater(@RequestParam(name = "idTheater") String idTheater,@RequestParam(name = "idCinema") String idCinema,@RequestHeader(value="Username") String username)
	{
		if(IsAuthorized("admin",username)== false)
		{
			return false;
		}
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
	
	@RequestMapping(value = "/editTheater",method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	public Boolean EditTheater(@RequestBody Theater theater,@RequestParam(name = "idCinema") String idCinema,@RequestHeader(value="Username") String username)
	{
		if(IsAuthorized("admin",username)== false)
		{
			return false;
		}
		Cinema cinema = bioskopSalaService.findOne(idCinema);
		HashSet<Theater> theaters = cinema.getTheaters();
		if(theaters != null)
		{
			Theater temp = null;
			for (Theater t : theaters) {
		        if (t.getId().equals(theater.getId())) {
		            temp = t;
		            break;
		        }
		    }
			if(temp!= null)
			{
				theaters.remove(temp);
				theaters.add(theater);
			}
		}
		
		cinema.setTheaters(theaters);
		bioskopSalaService.update(cinema.getId(), cinema);
		return true;
	}
	
	@RequestMapping(value = "/getTheater",method = RequestMethod.GET)
	public Theater GetTheater(@RequestParam(name = "idTheater") String idTheater,@RequestParam(name = "idCinema") String idCinema)
	{
		
		Cinema cinema = bioskopSalaService.findOne(idCinema);
		if(cinema == null)
		{
			return null;
		}
		HashSet<Theater> theaters = cinema.getTheaters();
		if(theaters != null)
		{
			Theater temp = null;
			for (Theater t : theaters) {
		        if (t.getId().equals(idTheater)) {
		            return t;
		        }
		    }
		}
		return null;
	}
	
	
	@RequestMapping(value = "/allTheaters",method = RequestMethod.GET)
	public HashSet<Theater> GetAllTheathers(@RequestParam(name = "id") String id)
	{
		return bioskopSalaService.findOne(id).getTheaters();
	}
	
	@RequestMapping(value = "/addRate",method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Boolean AddRate(@RequestBody ObjectNode obj,@RequestParam(name = "id") String id,@RequestHeader(value="Username") String username)
	{
		Rate rate = Rate.getRate(obj.get("rate").asInt());
		final String uri = "http://localhost:8765/user-service/users/getUserId?username="+username;
	     
	    RestTemplate restTemplate = new RestTemplate();
	    String user = restTemplate.getForObject(uri, String.class);
	    if(user.equals(""))
	    {
	    	return false;
	    }
		Cinema cinema = bioskopSalaService.findCinemaById(id);
		Map<String,Rate> rates = cinema.getRates();
		if(rates == null)
		{
			rates = new HashMap<String,Rate>();
		}
		if(rates.containsKey(username))
		{
			return false;
		}
		rates.put(username,rate);
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
	
	@RequestMapping(value = "search/findGeneral", method = RequestMethod.POST)
	public List<Cinema> findGeneral(@RequestBody ObjectNode json) {
		String userOnSession = json.get("userOnSession").asText();
		 String name = json.get("name").asText();
		 Boolean isSort = json.get("isSort").asBoolean();
		 double radius = json.get("radius").asDouble();
		 double x = json.get("x").asDouble();
		 double y = json.get("y").asDouble();
		List<Cinema> all = bioskopSalaService.findByNameAndAndAddressNear(userOnSession, name, isSort, radius, x, y);
		return all;
	}
}
