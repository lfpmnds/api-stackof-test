package com.webradar.stackoverflow.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.webradar.stackoverflow.entities.Answer;
import com.webradar.stackoverflow.entities.Question;
import com.webradar.stackoverflow.entities.User;
import com.webradar.stackoverflow.services.AnswerService;
import com.webradar.stackoverflow.services.QuestionService;
import com.webradar.stackoverflow.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	
	@Autowired
	private UserService userService;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private AnswerService answerService;	

	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> list = userService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}/questions")
	public ResponseEntity<List<Question>> findAllQuestionsByUserId(@PathVariable String id) {
		User user = new User(Long.parseLong(id));
		List<Question> list = questionService.findByUser(user);		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}/answers")
	public ResponseEntity<List<Answer>> findAllAnswersByUserId(@PathVariable String id) {
		User user = new User(Long.parseLong(id));
		List<Answer> list = answerService.findByUser(user);
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody @Valid User user) {
		user = userService.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}
}