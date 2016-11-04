package cooksys.service;

import java.util.List;

import cooksys.entity.User;

public interface UserService {
//	User findById(Long id);
//	User findByUserName(String userName);
	List<User> findAll();
	
	public void add(User user);
//	public boolean exists(User user);
}
