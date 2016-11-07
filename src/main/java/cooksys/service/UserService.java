package cooksys.service;

import java.util.List;

import cooksys.component.User;
import cooksys.entity.Credentials;
import cooksys.entity.UserEntity;

public interface UserService {
//	User findById(Long id);
//	User findByUsername(String username);
	User findByUsernameAndActiveTrue(String username);
//	List<User> findAll();
	List<User> findByActiveTrue();
	User add(UserEntity userEntity);
	User delete(Credentials credentials);
	boolean usernameExists(String username);
	boolean usernameAvailable(String username);
}
