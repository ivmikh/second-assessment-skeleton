package cooksys.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public User putUser(@RequestBody User user) {
		System.out.println("User is creating!!!!!*****************!!!!" + getUser(user).getUsername());
//		if(!userService.exists(user)){
		  userService.add(user);
		  return user;
//		}
	}
	
//	@PostMapping
//	public void add(@RequestBody Student student) {
//		studentService.add(student);
//	}
//	
//	@PutMapping("/{student}/skill/{skill}")
//	public void addSkill(@PathVariable Student student, @PathVariable Skill skill) {
//		studentService.addSkill(student, skill);
//	}
	
}
