package cooksys.service;

import java.util.List;

import cooksys.component.UserObj;
import cooksys.entity.Credentials;
import cooksys.entity.User;

public interface UserService {
//	User findById(Long id);
//	User findByUsername(String username);
	UserObj findByUsernameAndActiveTrue(String username);
//	List<User> findAll();
	List<UserObj> findByActiveTrue();
	UserObj add(User userEntity);
	UserObj delete(Credentials credentials);
	boolean usernameExists(String username);
	boolean usernameAvailable(String username);
}
