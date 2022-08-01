package com.webradar.stackoverflow.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.webradar.stackoverflow.entities.Answer;
import com.webradar.stackoverflow.entities.User;

@ExtendWith(SpringExtension.class)
public class AnswerServiceTests {
	
	@Spy
	private AnswerService service;
	
	private String existingUsername;
	private String nonExistingUsername;
	private String password;
	
	@BeforeEach
	void setUp() throws Exception {
		existingUsername = "maria@gmail.com";
		nonExistingUsername = "felipe";
		password = "123456";
	}
	
	@Test
	public void verifyIfAnswerIsFromUserOnSessionShouldReturnTrueWhenUserIsAuthenticated() {
		User user = new User(null, existingUsername, password);
		Answer answer = new Answer(null, "Testando, ok?", user, null);
		
		boolean result = service.verifyIfAnswerIsFromUserOnSession(answer, existingUsername);
		
		Assertions.assertTrue(result);
	}
	
	@Test
	public void verifyIfAnswerIsFromUserOnSessionShouldReturnFalseWhenUserIsNotAuthenticated() {
		User user = new User(null, existingUsername, password);
		Answer answer = new Answer(null, "Testando, ok?", user, null);
		
		boolean result = service.verifyIfAnswerIsFromUserOnSession(answer, nonExistingUsername);
		
		Assertions.assertFalse(result);
	}
}
