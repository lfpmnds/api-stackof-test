package com.webradar.stackoverflow.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webradar.stackoverflow.entities.Answer;
import com.webradar.stackoverflow.services.AnswerService;

@RestController
@RequestMapping(value = "/answers")
public class AnswerResource {
	
	@Autowired
	private AnswerService service;
	
	@GetMapping
	public ResponseEntity<List<Answer>> findAll() {
		List<Answer> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public Answer insert(@RequestBody @Valid Answer answer) {
		return service.save(answer);
	}	
}
