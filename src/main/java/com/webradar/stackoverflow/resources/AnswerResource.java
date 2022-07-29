package com.webradar.stackoverflow.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	public ResponseEntity<Answer> insert(@RequestBody @Valid Answer answer) {
		answer = service.save(answer);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(answer.getId()).toUri();
		return ResponseEntity.created(uri).body(answer);
	}		
}
