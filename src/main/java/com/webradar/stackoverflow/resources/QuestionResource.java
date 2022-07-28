package com.webradar.stackoverflow.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webradar.stackoverflow.entities.Answer;
import com.webradar.stackoverflow.entities.Question;
import com.webradar.stackoverflow.services.AnswerService;
import com.webradar.stackoverflow.services.QuestionService;

@RestController
@RequestMapping(value = "/questions")
public class QuestionResource {
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private AnswerService answerService;
	
	@GetMapping
	public ResponseEntity<List<Question>> findAll() {
		List<Question> list = questionService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}/answers")
	public ResponseEntity<List<Answer>> findAllAnswersByQuestionId(@PathVariable String id) {
		Question question = new Question(Long.parseLong(id));
		List<Answer> list = answerService.findByQuestion(question);
		return ResponseEntity.ok().body(list);
	}
	
}
