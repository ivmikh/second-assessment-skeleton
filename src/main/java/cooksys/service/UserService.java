package cooksys.service;

import java.util.List;

import cooksys.entity.User;

public interface UserService {
//	User findById(Long id);
	User findByUsername(String username);
	List<User> findAll();
	
	public User add(User user);
//	public boolean exists(User user);
}
