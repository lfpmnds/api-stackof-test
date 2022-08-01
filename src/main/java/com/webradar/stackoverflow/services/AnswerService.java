package com.webradar.stackoverflow.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webradar.stackoverflow.entities.Answer;
import com.webradar.stackoverflow.entities.Question;
import com.webradar.stackoverflow.entities.User;
import com.webradar.stackoverflow.repositories.AnswerRepository;
import com.webradar.stackoverflow.services.exceptions.ResourceNotFoundException;

@Service
public class AnswerService {

	@Autowired
	private AnswerRepository repository;
	
	@Autowired
	private UserService userService;
	
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
		try {
			String userOnSession = userService.getUserOnSession();
			User user = userService.findByUsername(userOnSession);
			answer.setAuthor(user);	
			return repository.save(answer);
		}
		catch (Exception e) {
			throw new ResourceNotFoundException("Pergunta não encontrada. Impossível responder");
		}
		
	}
	
	@Transactional
	public Answer update(Long id, Answer answer) {

		Answer entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Resposta não encontrada"));

		String userOnSession = userService.getUserOnSession();

		if (verifyIfAnswerIsFromUserOnSession(entity, userOnSession)) {
			entity.setBody(answer.getBody());
			return repository.save(entity);
		} else {
			throw new ResourceNotFoundException("Você não pode editar uma resposta que não é sua");
		}
	}
	protected boolean verifyIfAnswerIsFromUserOnSession(Answer answer, String userOnSession) {
		return answer.getAuthor().getUsername().equals(userOnSession);
	}
}
