package com.webradar.stackoverflow.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.webradar.stackoverflow.repositories.UserRepository;
import com.webradar.stackoverflow.services.exceptions.UserInsertException;

@SpringBootTest
public class UserServiceIntegrationTest {

	@Autowired
	private UserService service;
	
	@Autowired
	private UserRepository repository;
	
	private String existingUsername;
	private String nonExistingUsername;
	private String password;
	private Long countTotalUsers;
	
	@BeforeEach
	void setUp() throws Exception {
		existingUsername = "maria@gmail.com";
		nonExistingUsername = "felipe@gmail.com";
		password = "123456";
		countTotalUsers = 3L;
	}
	
	@Test
	public void insertShouldInsertNewUserWhenUsernameDoesNotExist() {
		service.insert(new com.webradar.stackoverflow.entities.User
				(null, nonExistingUsername, password));		
		Assertions.assertEquals(countTotalUsers + 1, repository.count());
	}
	
	@Test
	public void insertShouldThrowUserInsertExceptionWhenUsernameExist() {		
		Assertions.assertThrows(UserInsertException.class, () -> {
			service.insert(new com.webradar.stackoverflow.entities.User
					(null, existingUsername, password));
		});
	}
}
