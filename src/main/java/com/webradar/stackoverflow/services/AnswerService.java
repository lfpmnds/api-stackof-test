package com.webradar.stackoverflow.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webradar.stackoverflow.entities.Answer;
import com.webradar.stackoverflow.entities.User;
import com.webradar.stackoverflow.repositories.AnswerRepository;

@Service
public class AnswerService {

	@Autowired
	private AnswerRepository repository;
	
	public List<Answer> findAll() {
		return repository.findAll();
	}	
	
	public List<Answer> findByUser(User user) {
		return repository.findByUserId(user);
	}
}
