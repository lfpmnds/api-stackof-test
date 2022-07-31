package com.webradar.stackoverflow.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<List<Answer>> findAllAnswersByQuestionId(@PathVariable String id) {
		Question question = new Question(Long.parseLong(id));
		List<Answer> list = answerService.findByQuestion(question);
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping(value = "/new")
	public ResponseEntity<Question> insert(@RequestBody @Valid Question question) {
		question = questionService.save(question);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(question.getId()).toUri();
		return ResponseEntity.created(uri).body(question);
	}	
	
	@PutMapping(value = "/{id}/edit")
	public ResponseEntity<Question> update(@PathVariable Long id, @RequestBody @Valid Question question) {
		question = questionService.update(id, question);
		return ResponseEntity.ok().body(question);
	}
}
