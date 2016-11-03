package cooksys.service.impl;

import org.springframework.stereotype.Service;

import cooksys.entity.User;
import cooksys.repository.UserRepository;
import cooksys.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	UserRepository userRepo;
	
	@Override
	public User findById(Long id) {
		return userRepo.findById(id);
	}
	@Override
	public User findByUserName(String userName) {
		return userRepo.findByUserName(userName);
	}
}
