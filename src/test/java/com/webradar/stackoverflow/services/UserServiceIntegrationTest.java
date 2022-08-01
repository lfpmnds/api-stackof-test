package com.webradar.stackoverflow.services;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webradar.stackoverflow.entities.User;
import com.webradar.stackoverflow.services.exceptions.UserInsertException;

@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceIntegrationTest {

	@Autowired
	private UserService service;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private MockMvc mockMvc;
	
	private String existingUsername;
	private String nonExistingUsername;
	private String password;
	
	@BeforeEach
	void setUp() throws Exception {
		existingUsername = "maria@gmail.com";
		nonExistingUsername = "felipe@gmail.com";
		password = "123456";
	}
	
	@Test
	public void insertShouldInsertNewUserWhenUsernameDoesNotExist() throws Exception {		
		User user = new User(null, nonExistingUsername, password);
		String jsonBody = objectMapper.writeValueAsString(user);
		
		ResultActions result = mockMvc.perform(post("/users/new", nonExistingUsername)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isCreated());
		result.andExpect(jsonPath("$.id").value(4L));
		result.andExpect(jsonPath("$.username").value(nonExistingUsername));
	}
	
	@Test
	public void insertShouldThrowUserInsertExceptionWhenUsernameExist() {		
		User user = new User(null, existingUsername, password);
		Assertions.assertThrows(UserInsertException.class, () -> {
			service.insert(user);
		});
	}
	
	@Test
	public void loadUserByUsernameShouldReturnNotNullWhenUsernameExists() {
		Assertions.assertNotNull(service.loadUserByUsername(existingUsername));
	}
}