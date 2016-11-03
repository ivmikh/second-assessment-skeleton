package cooksys.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cooksys.entity.User;
//import cooksys.entity.UserTable;
import cooksys.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/{id}")
	public User getUser(@PathVariable long id) {
		return userService.findById(id);
	}

}
