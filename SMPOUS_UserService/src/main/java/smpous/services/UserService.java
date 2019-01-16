package smpous.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import smpous.models.TypeOfUser;
import smpous.models.User;
import smpous.repositories.UserRepository;

@Service
public class UserService extends AbstractCRUDService<User, String>{
	
	private UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository){
		super(userRepository);
		this.userRepository = userRepository;
	}
	
	public User login(String username, String password){
		Boolean isActive = true;
		User user = userRepository.findByUsernameAndIsActive(username, isActive);
		
		if(user != null){
			if(user.getPassword().equals(password)){
				return user;
			}
		}
		
		return null;
	}
	
	public boolean logout(String username){
			return true;
	}
	
	public List<User> findByFirstName(String firstName){
		return userRepository.findByName(firstName);
	}
	
	public User findByUsername(String username){
		return userRepository.findByUsername(username);
	}
	
	public Page<User> findByFirstName(String firstName, Pageable pageable){
		return userRepository.findByName(firstName, pageable);
	}

	public User findByIdAndActive(String userId, Boolean isActive) {
		return userRepository.findByIdAndIsActive(userId, isActive);
	}

	public List<User> findByTypeOfUser(String type) {
		return userRepository.findByTypeOfUser(type);
	}
	
	public String findRoleByUsername(String username){
		return this.findByUsername(username).getTypeOfUser();
	}
	
	public User changePass(String username, String newP){
		User u = this.findByUsername(username);
		u.setPassword(newP);
		update(u.getId(), u);
		return u;
	}
	
	public User changeInfo(String username, String name, String lastname, String street, int number, double x, double y, String birth){
		User u = this.findByUsername(username);
		u.setName(name);
		u.setLastname(lastname);
		u.setStreet(street);
		u.setNumber(number);
		u.setX(x);
		u.setY(y);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = format.parse(birth);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		u.setBirthday(date);
		update(u.getId(), u);
		return u;
	}
	
	public List<User> findByAddressNear(int radius, double x, double y) {
		Distance d = new Distance(radius, Metrics.KILOMETERS);
		Point p = new Point(x,y);
		return userRepository.findByAddressNear(p, d);
	}
	
	public Boolean activate(String userOnSession, String userToActivate) {
		User sesija = userRepository.findByUsernameAndIsActive(userOnSession, true);
		if(sesija.getTypeOfUser().equals("admin"))
		{
			User u = userRepository.findByUsernameAndIsActive(userToActivate, false);
			u.setIsActive(true);
			u.setTypeOfUser("registered");
			update(u.getId(), u);
			return true;
		}
		else return false;
	}
	
	public Boolean deactivate(String userOnSession, String userToActivate) {
		User sesija = userRepository.findByUsernameAndIsActive(userOnSession, true);
		if(sesija.getTypeOfUser().equals("admin"))
		{
			User u = userRepository.findByUsernameAndIsActive(userToActivate, true);
			u.setIsActive(false);
			u.setTypeOfUser("unregistered");
			update(u.getId(), u);
			return true;
		}
		else return false;
	}
	
	public List<User> findByUsernameAndFirstNameAndSurnameAndIsActiveAndAddressNear(String userOnSession, String username, String firstName, String surname, Boolean isActive, int radius, double x, double y){
		User sesija = userRepository.findByUsernameAndIsActive(userOnSession, true);
		if(sesija != null)
		{
			if(!sesija.getTypeOfUser().equals("admin"))
			{
				isActive = true;
			}
		}
		else
		{
			isActive = true;
		}
		
			List<User> list = this.findByAddressNear(radius, x, y);
			List<User> filtered1 = new ArrayList<User>();
			List<User> filtered2 = new ArrayList<User>();
			List<User> filtered3 = new ArrayList<User>();
			List<User> filtered4 = new ArrayList<User>();
			if(!username.equals("*"))
			{
				for(int i = 0; i < list.size(); i++)
				{
					if(list.get(i).getUsername().contains(username) && list.get(i).getIsActive() == isActive)
					{
						filtered1.add(list.get(i));
					}
				}
				
				list = filtered1;
			}
			
			
			if(!firstName.equals("*"))
			{
				for(int i = 0; i < list.size(); i++)
				{
					if(list.get(i).getName().contains(firstName) && list.get(i).getIsActive() == isActive)
					{
						filtered2.add(list.get(i));
					}
				}
				
				list = filtered2;
			}
			
			if(!surname.equals("*"))
			{
				for(int i = 0; i < list.size(); i++)
				{
					if(list.get(i).getLastname().contains(surname) && list.get(i).getIsActive() == isActive)
					{
						filtered3.add(list.get(i));
					}
				}
				
				list = filtered3;
			}
			
			for(int i = 0; i < list.size(); i++)
			{
				if(list.get(i).getIsActive() == isActive)
				{
					filtered4.add(list.get(i));
				}
			}
			list = filtered4;
			
			return list;
		
		
	}

}

