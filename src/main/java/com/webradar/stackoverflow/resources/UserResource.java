package com.webradar.stackoverflow.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webradar.stackoverflow.entities.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@GetMapping
	public ResponseEntity<User> findAll() {
		//teste
		User user1 = new User(1L, "Maria", "maria@gmail.com", "123");
		return ResponseEntity.ok().body(user1);
	}

}
