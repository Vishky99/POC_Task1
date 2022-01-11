package com.poc.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poc.model.User;
import com.poc.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<User> getUsers()
	{
		return userService.getUsers();
	}
	
	// .../users/sort?field={fieldName}&dir={asc OR desc}
	@GetMapping("/users/sort")
	public List<User> getUsersSorted(@RequestParam String field, @RequestParam String dir)
	{
		Sort sort = dir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(field).ascending() :
			Sort.by(field).descending();
		return userService.getUsersSorted(sort);
	}

	@GetMapping("/users/{id}")
	public Optional<User> getUser(@PathVariable long id)
	{
		return userService.getUser(id);
	}

	@PostMapping("/users")
	public void addUser(@Valid @RequestBody User user) {
		userService.addUser(user);
	}

	// soft delete
	@DeleteMapping("/users/{id}")
	public void softDeleteUser(@PathVariable long id) {
		userService.softDeleteUser(id); 
	}
	
	// hard delete
	@DeleteMapping("/deleteuser/{id}")
	public void deleteUser(@PathVariable long id) {
		userService.deleteUser(id); 
	}

	@PutMapping("/users/{id}")
	public void updateUser(@RequestBody User user, @PathVariable long id) { 
		user.setId(id);
		userService.updateUser(user, id); 
	}
	
	// search by name
	@GetMapping("/users/name/{name}")
	public List<User> searchName(@PathVariable String name)
	{
		return userService.findUserByName(name);
	}
	
	// search by surname
	@GetMapping("/users/surname/{surname}")
	public List<User> searchSurname(@PathVariable String surname)
	{
		return userService.findUserBySurname(surname);
	}
	
	// search by pincode
	@GetMapping("/users/pincode/{pincode}")
	public List<User> searchPincode(@PathVariable String pincode)
	{
		return userService.findUserByPincode(pincode);
	}

}
