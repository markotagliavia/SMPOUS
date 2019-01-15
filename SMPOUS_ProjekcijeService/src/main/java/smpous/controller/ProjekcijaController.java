package smpous.controller;

import java.util.ArrayList;
import java.util.HashSet;
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

import smpous.models.Cinema;
import smpous.models.Projection;
import smpous.repository.ProjekcijaRepository;
import smpous.service.ProjekcijaService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("projekcija")
public class ProjekcijaController extends AbstractRESTController<Projection, String> {
	@Autowired
	Environment environment;

	private ProjekcijaService projekcijaService;
	
	//@Autowired
	//private ProjekcijaRepository projekcijaRepository;

	public ProjekcijaController(ProjekcijaService service) {
		super(service);
		this.projekcijaService = service;
	}

	@RequestMapping(value = "/addProjekcija", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Boolean AddProjekcija(@RequestBody Projection projekcija,
			@RequestHeader(value = "Username") String username) {
		if (IsAuthorized("admin", username) == false) {
			return false;
		}
		
		projekcija.setId(UUID.randomUUID().toString());
		projekcijaService.save(projekcija);

		return true;
	}
	
	@RequestMapping(value = "/allProjekcije",method = RequestMethod.GET)
	public List<Projection> GetAllProjekcije()
	{
		return projekcijaService.findAllProjekcije();
	}

	@RequestMapping(value = "/aktivneProjekcije",method = RequestMethod.GET)
	public List<Projection> GetAktivneProjekcije()
	{
		List<Projection> svee = projekcijaService.findAllProjekcije();
		List<Projection> aktivne = new ArrayList<>();
		for(Projection proja : svee) {
			if(proja.getIsActive()) aktivne.add(proja);
		}
		return aktivne;
	}

	@RequestMapping(value = "/premijere",method = RequestMethod.GET)
	public List<Projection> GetPremijere()
	{
		List<Projection> svee = projekcijaService.findAllProjekcije();
		List<Projection> premijere = new ArrayList<>();
		for(Projection proja : svee) {
			if(proja.getIsPremiere()) premijere.add(proja);
		}
		return premijere;
	}
	
	@RequestMapping(value = "/editProjekcija", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	public Boolean EditProjekcija(@RequestBody Projection projekcija,
			@RequestHeader(value = "Username") String username) {
		if (IsAuthorized("admin", username) == false) {
			return false;
		}
		projekcijaService.update(projekcija.getId(), projekcija);
		return true;
	}

	@RequestMapping(value = "/deleteProjekcija", method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	public Boolean DeleteProjekcija(@RequestParam(name = "id") String id,
			@RequestHeader(value = "Username") String username) {
		if (IsAuthorized("admin", username) == false) {
			return false;
		}
		projekcijaService.delete(id);
		return true;
	}
	
	@RequestMapping(value = "/findProjectionByCinemaId",method = RequestMethod.GET)
	public HashSet<Projection> FindProjectionByCinemaId(@RequestParam(name = "cinemaId") String cinemaId)
	{
		return projekcijaService.findProjectionByIdCinema(cinemaId);
	}
	
	@RequestMapping(value = "/findProjectionByMovieId",method = RequestMethod.GET)
	public HashSet<Projection> FindProjectionByMovieId(@RequestParam(name = "movieId") String movieId)
	{
		return projekcijaService.findProjectionByIdMovie(movieId);
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

}
