package com.Posidex.Day1W3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Posidex.Day1W3.entity.User;
import com.Posidex.Day1W3.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User createUser(User user) {
        return repository.save(user);
    }

    public List<User> getAllUsers() {
        return repository.findByIsDeletedFalse();
    }

    public User getUserById(Long id) {
        Optional<User> userOpt = repository.findById(id);
        return userOpt.filter(user -> !user.isDeleted()).orElse(null);
    }
    	
	    public User updateUser(Long id, User newData) {
        Optional<User> existingUserOpt = repository.findById(id);
        if (existingUserOpt.isPresent()) {
            User user = existingUserOpt.get();
            user.setName(newData.getName());
            user.setEmail(newData.getEmail());
            user.setAddress(newData.getAddress());
            user.setPhone(newData.getPhone());
            return repository.save(user);
        }
        return null;
    }

    public String softDeleteUser(Long id) {
        Optional<User> userOpt = repository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setDeleted(true);
            repository.save(user);
            return "User soft-deleted.";
        }
        return "User not found.";
    }


}
