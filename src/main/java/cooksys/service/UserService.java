package cooksys.service;

import java.util.List;

import cooksys.entity.User;

public interface UserService {
//	User findById(Long id);
	User findByUsername(String username);
	User findByUsernameAndActiveTrue(String username);
//	List<User> findAll();
	List<User> findByActiveTrue();
	User add(User user);
//	public boolean exists(User user);
	User delete(User user);
}
