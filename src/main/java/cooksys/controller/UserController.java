package cooksys.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
		return userService.findAll();
	}
	
	@GetMapping("/{user}")
	public User getUser(@PathVariable User user) {
//		System.out.println("User ID is received!!!!!*****************!!!!");
		return user;
	}
	
	@PostMapping
	public User putUser(@RequestBody User user) throws NotAddedException {
		System.out.println("User is creating!!!!!*****************!!!!" + user.getCredentials().getUsername());
//		if(!userService.exists(user)){
		
		user.setId(null); // user id should not be in the input!
		User returnUser = userService.add(user);
		if (returnUser.getId() == null)
			throw new NotAddedException("User was not added!");
		return returnUser;
//		}
	}
	
//	@GetMapping("validate")
//	public boolean usernameExists() {
//		System.out.println("Seraching for username ............ Ivan ");
//		return userService.findByUsername("Ivan") == null ? false : true;
//	}
	
}
@ResponseStatus
class NotAddedException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotAddedException(String msg) {
        super(msg);
    }
}
