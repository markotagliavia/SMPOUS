package smpous.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import smpous.models.Cinema;
import smpous.services.BioskopSalaService;

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
	public Boolean AddCinema(@RequestBody Cinema cinema)
	{
		cinema.setId(UUID.randomUUID().toString());
		bioskopSalaService.save(cinema);
		return true;
		
	}
	@RequestMapping(value = "/findCinemaByName",method = RequestMethod.GET)
	public HashSet<Cinema> FindCinemaByName(@RequestParam(name = "name") String name)
	{
		return bioskopSalaService.findCinemaByName(name);
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
}
