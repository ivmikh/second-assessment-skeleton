package cooksys.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import cooksys.component.UserObj;
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
	
	@Override
	public boolean usernameExists(String username) {
		return (userRepo.findByUsernameAndActiveTrue(username) == null) ? false : true;
	}
	
	@Override
	public boolean usernameAvailable(String username) {
		return (userRepo.findByUsername(username) == null) ? false : true;
	}
	
	@Override
	public UserObj findByUsernameAndActiveTrue(String username) {
		User userEntity = userRepo.findByUsernameAndActiveTrue(username);
		return (userEntity == null) ? null : new UserObj(userEntity);
	}
	
	@Override
	public UserObj add(User userEntity) {
		// Check for missing credentials:
		Credentials credentials = userEntity.getCredentials();
		if(credentials == null)
			return null;
		String username = credentials.getUsername();
		String password = credentials.getPassword();		
		if(username == null || username.equals("") || password == null || password.equals("")){
			return null;
		}
		User dbUser = userRepo.findByUsername(username);
		if( dbUser == null ) { // if User never existed:
		   userEntity.setActive(true);
		   userEntity.setUsername(username);
//		   user.setJoined(new Timestamp( (new Date()).getTime() ) );
		   return new UserObj( userRepo.saveAndFlush(userEntity) );
		} else if ( ! password.equals(dbUser.getCredentials().getPassword() ) ) { // if Password doesn't match:	
			return null;
		} else if (!dbUser.isActive()) { // if user not active
			dbUser.setActive(true);
			return new UserObj( userRepo.saveAndFlush(dbUser) );
		} else {  // if exact active match
			return null;
		}
		
	}
	@Override
	public UserObj delete(Credentials credentials) {
		User userEntity = userRepo.findByUsernameAndActiveTrue(credentials.getUsername());
		if(userEntity == null || ! userEntity.getCredentials().getPassword().equals(credentials.getPassword()))
			return null;
		UserObj user = new UserObj(userEntity);
		userEntity.setActive(false);
		userRepo.saveAndFlush(userEntity);
		return user;
		
	}
	@Override
	public List<UserObj> findByActiveTrue() {
		List<UserObj> listUser = new ArrayList<>();
		for (User userEntity: userRepo.findByActiveTrue()){
			listUser.add( new UserObj(userEntity) );
		}
		return listUser;
	}

	
//	@Override
//	public boolean exists(User user) {
//		// returns true if user can be found by userName
//		return userRepo.findByUserName(user.getUserName()) == null ? false : true;
//	}
}
