package com.webradar.stackoverflow.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceIntegrationTests {

	@Autowired
	private UserService service;
	
	private String existingUsername;
	
	@BeforeEach
	void setUp() throws Exception {
		existingUsername = "maria@gmail.com";
	}
	
	@Test
	public void loadUserByUsernameShouldReturnNotNullWhenUsernameExists() {
		Assertions.assertNotNull(service.loadUserByUsername(existingUsername));
	}
	
	
}