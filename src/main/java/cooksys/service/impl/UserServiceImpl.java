package cooksys.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import cooksys.component.User;
import cooksys.entity.Credentials;
import cooksys.entity.UserEntity;
import cooksys.repository.UserRepo;
import cooksys.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	UserRepo userRepo;
	
	public UserServiceImpl(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
//	@Override
//	public User findById(Long id) {
//		return userRepo.findById(id);
//	}
	
	@Override
	public boolean usernameExists(String username) {
		return (userRepo.findByUsername(username) == null) ? false : true;
	}
	
	
	@Override
	public User findByUsernameAndActiveTrue(String username) {
		UserEntity userEntity = userRepo.findByUsernameAndActiveTrue(username);
		return (userEntity == null) ? null : new User(userEntity);
	}
	
	@Override
	public User add(UserEntity userEntity) {
		// Check for missing credentials:
		Credentials credentials = userEntity.getCredentials();
		if(credentials == null)
			return null;
		String username = credentials.getUsername();
		String password = credentials.getPassword();		
		if(username == null || username.equals("") || password == null || password.equals("")){
			return null;
		}
		UserEntity dbUser = userRepo.findByUsername(username);
		if( dbUser == null ) { // if User never existed:
		   userEntity.setActive(true);
		   userEntity.setUsername(username);
//		   user.setJoined(new Timestamp( (new Date()).getTime() ) );
		   return new User( userRepo.saveAndFlush(userEntity) );
		} else if ( ! password.equals(dbUser.getCredentials().getPassword() ) ) { // if Password doesn't match:	
			return null;
		} else if (!dbUser.isActive()) { // if user not active
			dbUser.setActive(true);
			return new User( userRepo.saveAndFlush(dbUser) );
		} else {  // if exact active match
			return null;
		}
		
	}
	@Override
	public User delete(Credentials credentials) {
		UserEntity userEntity = userRepo.findByUsernameAndActiveTrue(credentials.getUsername());
		if(userEntity == null || ! userEntity.getCredentials().getPassword().equals(credentials.getPassword()))
			return null;
		User user = new User(userEntity);
		userEntity.setActive(false);
		userRepo.saveAndFlush(userEntity);
		return user;
		
	}
	@Override
	public List<User> findByActiveTrue() {
		List<User> listUser = new ArrayList<>();
		for (UserEntity userEntity: userRepo.findByActiveTrue()){
			listUser.add( new User(userEntity) );
		}
		return listUser;
	}

	
//	@Override
//	public boolean exists(User user) {
//		// returns true if user can be found by userName
//		return userRepo.findByUserName(user.getUserName()) == null ? false : true;
//	}
}
