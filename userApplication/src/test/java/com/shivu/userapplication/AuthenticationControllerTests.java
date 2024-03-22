package com.shivu.userapplication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTests {

	@Autowired
	MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	public void testLogin() throws Exception {
		String requestBody = "{\"username\": \"admin\", \"password\": \"password\"}";

		mockMvc
				.perform(MockMvcRequestBuilders
						.post("http://localhost:8080/auth/loginb")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestBody))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testRegister() throws Exception {
		String requestBody = "{\"username\": \"testuser\", \"password\": \"password\",\"email\":\"testuser@email.com\"}";

		mockMvc
				.perform(MockMvcRequestBuilders
						.post("http://localhost:8080/auth/registerb")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestBody))
						.andExpect(MockMvcResultMatchers.status()
						.isOk());	
					
					}
		

}
