package smpous.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import smpous.models.TypeOfUser;
import smpous.models.User;
import smpous.services.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("users")
public class UserController extends AbstractRESTController<User, String>{
	
	@Autowired
	Environment environment;
	
	private UserService userService;
	
    private final Random random = new Random();

    private static final String[] NAMES = new String[] {
    		"Arnette Whitesides",
    		"Sherley Holifield ",
    		"Iva Mathias",
    		"Joellen Hatch",
    		"Harley Braziel",
    		"Oralee Thweatt",
    		"Mao Lammert",
    		"Dannette Peru",
    		"Sherell Service",
    		"Tamara Bratcher",
    		"Quintin Vankirk",
    		"Orval Tarter",
    		"Alysa Kesterson",
    		"Krissy Bothwell",
    		"Freeda Leicht",
    		"Gemma Crippen",
    		"Darci Caroll",
    		"Tarra Argento",
    		"Corinne Farah",
    		"Myrta Neuberger"

    };
    
	@Autowired
	public UserController(UserService userService) {
		super(userService);
		this.userService = userService;
	}
	
	@RequestMapping(value = "/hello")
	public String hello(){

		return NAMES[random.nextInt(NAMES.length)] +"[PORT: "+ environment.getProperty("local.server.port") + "]";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public User register(@RequestBody User user){
		user.setId(UUID.randomUUID().toString());
		user.setRegistrationDay(new Date());
		user.setAddress(new GeoJsonPoint(user.getX(), user.getY()));
		if(userService.findByUsername(user.getUsername()) != null)
		{
			return null;
		}
		else return userService.save(user);
	}
	
	@RequestMapping(value = "/activate", method = RequestMethod.POST)
	public Boolean activate(@RequestBody ObjectNode json){
		 String userOnsession = json.get("userOnSession").asText();
		 String userToActivate = json.get("userToActivate").asText();
		return userService.activate(userOnsession, userToActivate);
	}
	
	@RequestMapping(value = "/deactivate", method = RequestMethod.POST)
	public Boolean deactivate(@RequestBody ObjectNode json){
		 String userOnsession = json.get("userOnSession").asText();
		 String userToDeActivate = json.get("userToDeActivate").asText();
		return userService.deactivate(userOnsession, userToDeActivate);
	}
	
	@RequestMapping(value = "/checkUser", method = RequestMethod.GET)
	public Boolean checkUser(
			@RequestParam(name = "userId") String userId
	){
		User user = userService.findByIdAndActive(userId, true);
		return user != null;
	}
	
	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public User getUserRole(
			@RequestParam(name = "username") String username
	){
		return userService.findByUsername(username);
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<User> getAll(){
		List<User> all = new ArrayList<User>();
		List<User> verifiedUsers = userService.findByTypeOfUser("registered");
		List<User> unverifiedUsers = userService.findByTypeOfUser("unregistered");
		all.addAll(verifiedUsers);
		all.addAll(unverifiedUsers);
		return all;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public User login(@RequestBody ObjectNode json){
		 String username = json.get("username").asText();
		 String password = json.get("password").asText();
		return userService.login(username, password);
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public boolean logout(@RequestBody ObjectNode json){
		 String username = json.get("username").asText();
		return userService.logout(username);
	}
	
	@RequestMapping(value = "search/findByFirstName", method = RequestMethod.GET)
	public List<User> findByAreaOfDanger(
			@RequestParam(name = "firstName") String firstName) {
		List<User> all = userService.findByFirstName(firstName);
		return all;
	}
	
	
	@RequestMapping(value = "search/findGeneral", method = RequestMethod.POST)
	public List<User> findGeneral(@RequestBody ObjectNode json) {
		String userOnSession = json.get("userOnSession").asText();
		 String username = json.get("username").asText();
		 String firstName = json.get("firstName").asText();
		 String surname = json.get("surname").asText();
		 int radius = json.get("radius").asInt();
		 double x = json.get("x").asDouble();
		 double y = json.get("y").asDouble();
		 Boolean isActive = json.get("isActive").asBoolean();
		List<User> all = userService.findByUsernameAndFirstNameAndSurnameAndIsActiveAndAddressNear(userOnSession, username, firstName, surname, isActive, radius, x, y);
		return all;
	}


}
