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
	
	@BeforeEach
	void setUp() throws Exception {
		
	}
	
	@Test
	public void verifyIfAnswerIsFromUserOnSessionShouldReturnTrueWhenUserIsAuthenticated() {
		User user = new User(null, "felipe", "123456");
		Answer answer = new Answer(null, "Testando, ok?", user, null);
		
		boolean result = service.verifyIfAnswerIsFromUserOnSession(answer, "felipe");
		
		Assertions.assertTrue(result);
	}
	
	@Test
	public void verifyIfAnswerIsFromUserOnSessionShouldReturnFalseWhenUserIsNotAuthenticated() {
		User user = new User(null, "felipe", "123456");
		Answer answer = new Answer(null, "Testando, ok?", user, null);
		
		boolean result = service.verifyIfAnswerIsFromUserOnSession(answer, "felipe2");
		
		Assertions.assertFalse(result);
	}
}
