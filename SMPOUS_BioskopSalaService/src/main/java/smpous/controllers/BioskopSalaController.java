package smpous.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
	

}
