package com.webradar.stackoverflow.services;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.webradar.stackoverflow.entities.User;
import com.webradar.stackoverflow.repositories.UserRepository;
import com.webradar.stackoverflow.services.exceptions.UserInsertException;

@Service
public class UserService implements UserDetailsService {

	private static Logger logger = org.slf4j.LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}

	public User insert(User user) {
		try {	
			User entity = user;
			entity.setPassword(passwordEncoder.encode(user.getPassword()));
			return repository.save(entity);
		}
		catch (DataIntegrityViolationException e) {
			throw new UserInsertException("Usuário já cadastrado");
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByUsername(username);
		if (user == null) {
			logger.error("Usuário não encontrado: " + username);
			throw new UsernameNotFoundException("Usuário não encontrado");
		}
		logger.info("Usuário encontrado: " + username);
		return user;
	}
}
