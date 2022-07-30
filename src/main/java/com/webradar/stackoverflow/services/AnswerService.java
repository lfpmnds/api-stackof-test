package com.webradar.stackoverflow.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webradar.stackoverflow.entities.Answer;
import com.webradar.stackoverflow.entities.Question;
import com.webradar.stackoverflow.entities.User;
import com.webradar.stackoverflow.repositories.AnswerRepository;
import com.webradar.stackoverflow.services.exceptions.ResourceNotFoundException;

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
	
	public List<Answer> findByQuestion(Question question) {
		return repository.findByQuestionId(question);
	}
	
	public Answer save(Answer answer) {
		return repository.save(answer);
	}

	public Answer update(Long id, Answer answer) {
		try {
			Answer entity = repository.getById(id);
			if (entity.getAuthor().getId() == answer.getAuthor().getId() 
					&& entity.getQuestion().getId() == answer.getQuestion().getId()) {
				entity.setBody(answer.getBody());
				entity = repository.save(entity);
			}
			return entity;
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Questão não encontrada");
		}			
	}
}
