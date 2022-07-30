package com.webradar.stackoverflow.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.webradar.stackoverflow.entities.User;
import com.webradar.stackoverflow.repositories.UserRepository;
import com.webradar.stackoverflow.services.exceptions.UserInsertException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}

	public User save(User user) {
		try {			
			return repository.save(user);
		}
		catch (DataIntegrityViolationException e) {
			throw new UserInsertException("Usuário já cadastrado");
		}
	}
}
