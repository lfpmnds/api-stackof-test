package com.webradar.stackoverflow.resources;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webradar.stackoverflow.entities.Answer;
import com.webradar.stackoverflow.entities.Question;
import com.webradar.stackoverflow.entities.User;

@SpringBootTest
@AutoConfigureMockMvc
public class AnswerResourceIntegrationTests {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;

	@Value("${security.oauth2.client.client-id}")
	private String clientId;

	@Value("${security.oauth2.client.client-secret}")
	private String clientSecret;

	private String existingUsername;
	private String password;
	
	private Long existingIdAnswerFromUsernameOnSession;
	private Long existingIdAnswerFromAnotherUsernameNotOnSession;
	private Long existingIdQuestion;

	@BeforeEach
	void setUp() throws Exception {
		existingUsername = "maria@gmail.com";
		password = "123456";
		existingIdAnswerFromUsernameOnSession = 4L;
		existingIdAnswerFromAnotherUsernameNotOnSession = 1L;
		existingIdQuestion = 3L;
	}

	@Test
	public void insertShouldReturnNewAnswerWhenUserIsAuthenticated()
			throws Exception {

		String accessToken = obtainAccessToken(existingUsername, password);
		User user = new User(null, existingUsername, password);
		Question question = new Question(existingIdQuestion, "Teste?", user);

		Answer answer = new Answer(null, "Espero que o teste esteja passando", user, question);

		String jsonBody = objectMapper.writeValueAsString(answer);

		ResultActions result = mockMvc
				.perform(post("/answers/new")
				.header("Authorization", "Bearer " + accessToken)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isCreated());
		result.andExpect(jsonPath("$.id").exists());
	}

	@Test
	public void updateShouldReturnUpdatedAnswerWhenAnswerBelongsToUserOnSession()
			throws Exception {
		String accessToken = obtainAccessToken(existingUsername, password);
		User user = new User(null, existingUsername, password);
		Question question = new Question(existingIdQuestion, "Teste?", user);

		Answer answer = new Answer(null, "Espero que o teste esteja passando", user, question);
		String jsonBody = objectMapper.writeValueAsString(answer);

		ResultActions result = mockMvc
				.perform(put("/answers/{id}/edit",
						existingIdAnswerFromUsernameOnSession)
				.header("Authorization", "Bearer " + accessToken)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.body").value("Espero que o teste esteja passando"));
	}
	
	@Test
	public void updateShouldReturnNotFoundWhenAnswerBelongsToAnotherUserNotOnSession()
			throws Exception {
		String accessToken = obtainAccessToken(existingUsername, password);
		User user = new User(null, existingUsername, password);
		Question question = new Question(existingIdQuestion, "Teste?", user);

		Answer answer = new Answer(null, "Espero que o teste esteja passando", user, question);
		String jsonBody = objectMapper.writeValueAsString(answer);

		ResultActions result = mockMvc
				.perform(put("/answers/{id}/edit",
						existingIdAnswerFromAnotherUsernameNotOnSession)
				.header("Authorization", "Bearer " + accessToken)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNotFound());
	}

	private String obtainAccessToken(String username, String password) throws Exception {

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "password");
		params.add("client_id", clientId);
		params.add("username", username);
		params.add("password", password);

		ResultActions result = mockMvc
				.perform(post("/oauth/token").params(params)
						.with(httpBasic(clientId, clientSecret))
						.accept("application/json;charset=UTF-8"))
				.andExpect(status().isOk()).andExpect(content()
						.contentType("application/json;charset=UTF-8"));

		String resultString = result.andReturn().getResponse().getContentAsString();

		JacksonJsonParser jsonParser = new JacksonJsonParser();
		return jsonParser.parseMap(resultString).get("access_token").toString();
	}
}
