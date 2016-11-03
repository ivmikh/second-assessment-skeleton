package cooksys.service;

import cooksys.entity.User;

public interface UserService {
	User findById(Long id);
	User findByUserName(String userName);
}
