package com.webradar.stackoverflow.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webradar.stackoverflow.entities.Question;
import com.webradar.stackoverflow.services.QuestionService;

@RestController
@RequestMapping(value = "/questions")
public class QuestionResource {
	
	@Autowired
	private QuestionService service;
	
	@GetMapping
	public ResponseEntity<List<Question>> findAll() {
		List<Question> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
}
