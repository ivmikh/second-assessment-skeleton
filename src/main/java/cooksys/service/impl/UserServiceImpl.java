package cooksys.service.impl;

import org.springframework.stereotype.Service;

import cooksys.entity.User;
import cooksys.repository.UserRepository;
import cooksys.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	UserRepository userRepo;
	
	public UserServiceImpl(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
//	@Override
//	public User findById(Long id) {
//		return userRepo.findById(id);
//	}
//	@Override
//	public User findByUserName(String userName) {
//		return userRepo.findByUserName(userName);
//	}
	
	@Override
	public void add(User user) {
//		System.out.println("Save and Flush!!!**************************!!!" + user.getUserName());
		userRepo.saveAndFlush(user);
	}

	@Override
	public boolean exists(User user) {
		// returns true if user can be found by userName
		return userRepo.findByUserName(user.getUserName()) == null ? false : true;
	}
}
