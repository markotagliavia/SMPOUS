package smpous.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
	
	public List<User> findByFirstName(String firstName){
		return userRepository.findByName(firstName);
	}
	
	public Page<User> findByFirstName(String firstName, Pageable pageable){
		return userRepository.findByName(firstName, pageable);
	}

	public User findByIdAndActive(String userId, Boolean isActive) {
		return userRepository.findByIdAndIsActive(userId, isActive);
	}

}

