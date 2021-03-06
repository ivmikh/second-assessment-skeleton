package cooksys.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cooksys.service.UserService;

@RestController
@RequestMapping("validate")
public class ValidateController {
	private UserService userService;

	public ValidateController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/username/exists/@{username}")
	public boolean usernameExsits(@PathVariable String username) {
		return userService.usernameExists(username);
	}
	
	@GetMapping("/username/available/@{username}")
	public boolean usernameAvailable(@PathVariable String username) {
		return userService.usernameAvailable(username);
	}

}
