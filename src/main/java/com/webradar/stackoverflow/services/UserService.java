package com.webradar.stackoverflow.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webradar.stackoverflow.entities.User;
import com.webradar.stackoverflow.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}

	// necessário tratar se usuário já existir
	public User save(User user) {
		if (!repository.existsByUsername(user.getUsername())) {
			repository.save(user);
		}
		return user;
	}
}
