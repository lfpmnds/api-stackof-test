package com.webradar.stackoverflow.services;

import java.util.List;

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
	
	@Autowired
	private UserService userService;

	public List<Question> findAll() {
		return repository.findAll();
	}

	public List<Question> findByUser(User user) {
		return repository.findByUserId(user);

	}

	public Question save(Question question) {
		String userOnSession = userService.getUserOnSession();
		User user = userService.findByUsername(userOnSession);
		question.setAuthor(user);
		return repository.save(question);		
	}

	@Transactional
	public Question update(Long id, Question question) {

		Question entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Questão não encontrada"));

		String userOnSession = userService.getUserOnSession();

		if (verifyIfQuestionIsFromUserOnSession(entity, userOnSession)) {
			entity.setBody(question.getBody());
			return repository.save(entity);
		} else {
			throw new ResourceNotFoundException("Você não pode editar uma questão que não é sua");
		}
	}
	protected boolean verifyIfQuestionIsFromUserOnSession(Question question, String userOnSession) {
		return question.getAuthor().getUsername().equals(userOnSession);
	}
}
