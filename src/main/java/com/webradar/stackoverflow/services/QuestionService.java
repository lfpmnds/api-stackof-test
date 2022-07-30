package com.webradar.stackoverflow.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webradar.stackoverflow.entities.Question;
import com.webradar.stackoverflow.entities.User;
import com.webradar.stackoverflow.repositories.QuestionRepository;
import com.webradar.stackoverflow.services.exceptions.ResourceNotFoundException;

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

	@Transactional
	public Question update(Long id, Question question) {
		try {
			Question entity = repository.getById(id);
			if (entity.getAuthor().getId() == question.getAuthor().getId()) {
				entity.setBody(question.getBody());
				entity = repository.save(entity);
			}
			return entity;
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Questão não encontrada");
		}		
	}
}
