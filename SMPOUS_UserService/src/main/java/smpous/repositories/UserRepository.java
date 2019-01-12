package smpous.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.mongodb.repository.MongoRepository;

import smpous.models.User;

public interface UserRepository extends MongoRepository<User, String>{

//	  @Query("{ 'firstname' : ?0 }")
//	  List<User> findByFirstName(String firstName);
	
	List<User> findByName(String name);
	
	List<User> findByName(String name, Sort sort);
	
	Page<User> findByName(String name, Pageable pageable);
	
//	Stream<User> findByFirstName(String firstName);
	
	List<User> findByNameAndLastname(String name, String lastname);
	
	//List<User> findByAgeGreaterThan(Integer age);
	
	//List<User> findByAgeLessThan(Integer age);
	
	//List<User> findByAgeBetween(Integer from, Integer to);
	
	List<User> findByNameNotNull();
	
	List<User> findByNameNull();
	
	//List<User> findByUserLocationNear(Point point, Distance distance);
	
	//List<User> findByUserLocationWithin(GeoJsonPolygon polygon);
	
	List<User> findByIsActiveIsTrue();
	
	List<User> findByIsActiveIsFalse();
	
	//List<User> findByDateOfBirthBetween(Date start, Date end);

	User findByIdAndIsActive(String id, Boolean isActive);
	
	User findByUsernameAndIsActive(String username, Boolean isActive);
	
	User findByUsername(String username);
	
	List<User> findByTypeOfUser(String typeOfUser);
	
	List<User> findByUsernameAndNameAndLastnameAndIsActive(String username, String name, String lastname, Boolean isActive);
	
	//User findByMailAndActive(String mail, Boolean isActive);
	
}

