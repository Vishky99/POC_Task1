package com.poc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.poc.model.User;
import com.poc.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRep;
	
	public List<User> getUsers(){
		return userRep.findAll();
	}
	
	public List<User> getUsersSorted(Sort sort){
		return userRep.findAll(sort);
	}
	
	public Optional<User> getUser(long id) {
		return userRep.findById(id);
	}
	
	public void addUser(User user) {
		userRep.save(user);
	}
	
	public void deleteUser(long id) {
		userRep.deleteById(id);
	}
	
	public void updateUser(User user, long id) {
		Optional<User>list=userRep.findById(user.getId());
		if(list.isPresent())
			userRep.save(user);
	}
	
    public List<User> findUserByName(String name) {
        return userRep.findUserByName(name);
    }
    
    public List<User> findUserBySurname(String surname) {
        return userRep.findUserBySurname(surname);
    }
    
    public List<User> findUserByPincode(String pincode) {
        return userRep.findUserByPincode(pincode);
    }

	public void softDeleteUser(long id) {
		userRep.softDeleteById(id);		
	}

}
