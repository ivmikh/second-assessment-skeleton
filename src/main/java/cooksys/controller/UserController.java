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
	public List<User> getUsers() {
//		System.out.println("User ID is received!!!!!*****************!!!!");
		return userService.findByActiveTrue();
	}
	
	@GetMapping("/@{username}")
	public User getUser(@PathVariable String username) throws UserControllerException {
//		System.out.println("Username is received!!!!!*****************!!!!");
		User dbUser = userService.findByUsernameAndActiveTrue(username);
		if (dbUser == null)
			throw new UserControllerException("User was not found!");
		return dbUser;
	}
	
	@RequestMapping(value = "/@{username}", method = RequestMethod.DELETE)
	public User deleteUser(@PathVariable String username, @RequestBody Credentials credentials) throws UserControllerException {
		System.out.println("Username is received to delete the user!!!!!*****************!!!!");
		User user = new User(credentials);
		if( ! username.equals( credentials.getUsername() ) ) 
			throw new UserControllerException("Usernames don't match!");
		User dbUser = userService.delete(user);
		if (dbUser == null)
			throw new UserControllerException("User doesn't exist. Check credentials: username and password!");
		return dbUser;
	}
	@PostMapping
	public User putUser(@RequestBody User user) throws UserControllerException {
		System.out.println("User is creating!!!!!*****************!!!!" + user.getCredentials().getUsername());
//		if(!userService.exists(user)){
		
//		user.setId(null); // user id should not be in the input!
		User dbUser = userService.add(user);
		if (dbUser == null)
			throw new UserControllerException("User was not added!");
		return dbUser;
//		}
	}
	
//	@GetMapping("validate")
//	public boolean usernameExists() {
//		System.out.println("Seraching for username ............ Ivan ");
//		return userService.findByUsername("Ivan") == null ? false : true;
//	}
	
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
