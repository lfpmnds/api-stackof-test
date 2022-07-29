package com.webradar.stackoverflow.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webradar.stackoverflow.entities.Question;
import com.webradar.stackoverflow.entities.User;
import com.webradar.stackoverflow.repositories.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository repository;
	
	public List<Question> findAll() {
		return repository.findAll();
	}
	
	public List<Question> findByUser(User user) {
		return repository.findByUserId(user);
	}
	
	public Question save(Question question) {
		return repository.save(question);
	}
}
