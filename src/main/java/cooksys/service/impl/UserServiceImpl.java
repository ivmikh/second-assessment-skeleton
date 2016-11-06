package cooksys.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import cooksys.entity.Credentials;
import cooksys.entity.User;
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
	
//	public boolean exists(User user) {
//		return true;
//	}
	
	@Override
	public User findByUsername(String username) {
		return userRepo.findByUsername(username);
	}
	
	@Override
	public User findByUsernameAndActiveTrue(String username) {
		return userRepo.findByUsernameAndActiveTrue(username);
	}
	
	@Override
	public User add(User user) {

		Credentials credentials = user.getCredentials();
		String username = credentials.getUsername();
		String password = credentials.getPassword();
		// Check for missing credentials:		
		if(username == null || username.equals("") || password == null || password.equals("")){
			return null;
		}

//		System.out.println("Cheking if user exists!!!**************************!!!" + username);
		User dbUser = userRepo.findByUsername(username);
		if( dbUser == null ) { // if User never existed:
			System.out.println("Creating a new user!!!**************************!!!" + username);
		   user.setUsername(username);
		   user.setActive(true);
//		   user.setJoined(new Timestamp( (new Date()).getTime() ) );
		   return userRepo.saveAndFlush(user);
		} else if ( ! password.equals(dbUser.getCredentials().getPassword() ) ) { // if Password doesn't match:
			System.out.println("Password doesn't match!          ********************! ");		
			return null;
		} else if (!dbUser.isActive()) { // if user not active
			System.out.println("Restoring user!          ********************! ");
			dbUser.setActive(true);
			return userRepo.saveAndFlush(dbUser);
		} else {  // if exact active match
			return null;
		}
		
	}
	@Override
	public User delete(User user) {
		System.out.println("1: Deleting the user>>>>>>>>>>>>>>>>>><<<<<<<<");
		User dbUser = userRepo.findByUsernameAndActiveTrue(user.getUsername());
		if(dbUser == null || ! dbUser.getCredentials().getPassword().equals(user.getCredentials().getPassword()))
			return null;
		dbUser.setActive(false);
		System.out.println("2: Deleting the user>>>>>>>>>>>>>>>>>><<<<<<<<");
		userRepo.saveAndFlush(dbUser);
		dbUser.setActive(true); // if one needs a user before its deletion
		return dbUser;
		
	}
	@Override
	public List<User> findByActiveTrue() {
		return userRepo.findByActiveTrue();
	}

	
//	@Override
//	public boolean exists(User user) {
//		// returns true if user can be found by userName
//		return userRepo.findByUserName(user.getUserName()) == null ? false : true;
//	}
}
