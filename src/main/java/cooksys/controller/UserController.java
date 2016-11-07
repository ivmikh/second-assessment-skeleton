package cooksys.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cooksys.component.UserObj;
import cooksys.entity.Credentials;
import cooksys.entity.User;
//import cooksys.entity.UserTable;
import cooksys.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public List<UserObj> getUsers() {
//		System.out.println("User ID is received!!!!!*****************!!!!");
		return userService.findByActiveTrue();
	}
	
	@GetMapping("/@{username}")
	public UserObj getUser(@PathVariable String username) throws UserControllerException {
//		System.out.println("Username is received!!!!!*****************!!!!");
		UserObj dbUser = userService.findByUsernameAndActiveTrue(username);
		if (dbUser == null)
			throw new UserControllerException("User was not found!");
		return dbUser;
	}
	
	@RequestMapping(value = "/@{username}", method = RequestMethod.DELETE)
	public UserObj deleteUser(@PathVariable String username, @RequestBody Credentials credentials) throws UserControllerException {
		if( username == null || ! username.equals( credentials.getUsername() ) ) 
			throw new UserControllerException("Username doesn't match!");
		UserObj dbUser = userService.delete(credentials);
		if (dbUser == null)
			throw new UserControllerException("User doesn't exist. Check credentials: username and password!");
		return dbUser;
	}
	
	@RequestMapping(value = "/@{username}", method = RequestMethod.PATCH)
	public UserObj patchUser(@PathVariable String username, @RequestBody User user) throws UserControllerException {
		if( username == null || ! username.equals( user.getCredentials().getUsername() ) ) 
			throw new UserControllerException("Username doesn't match!");
		UserObj dbUser = userService.patch(user);
		if (dbUser == null)
			throw new UserControllerException("User doesn't exist. Check credentials: username and password!");
		return dbUser;
	}
	
	@PostMapping
	public UserObj putUser(@RequestBody User userEntity) throws UserControllerException {
		UserObj dbUser = userService.add(userEntity);
		if (dbUser == null)
			throw new UserControllerException("User was not added!");
		return dbUser;
	}
	
	
}
@ResponseStatus
class UserControllerException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserControllerException(String msg) {
        super(msg);
    }
}
//class UserPostRequest {
//	  public Credentials credentials;
//	  public ProfileEntity profile;
//}