package com.unit.testing.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.unit.testing.model.User;
import com.unit.testing.service.UserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@AutoConfigureMockMvc
public class UserControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private UserService userService;

	@Test
	public void givenEmployees_whenGetEmployees_thenReturnJsonArray() throws Exception {

		User user = new User("tmg2", "pass2");

		List<User> userList = Arrays.asList(user);

		Mockito.when(userService.getAllUsers()).thenReturn(userList);

		MvcResult result = mvc.perform(get("/user").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$[0].name", is(user.getName()))).andReturn();

		String content = result.getResponse().getContentAsString();

		System.out.println("Response: " + content);
	}

	@Test
	public void whenValidInput_thenCreateEmployee() throws Exception {

		User user = new User("tmg3", "pass3");
		Mockito.when(userService.addUser(ArgumentMatchers.<User>any())).thenReturn(user);

		MvcResult result = mvc
				.perform(post("/user").contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\": \"tmg3\",\"password\": \"pass3\"}"))
				.andDo(print()).andExpect(status().isOk()).andReturn();

		String content = result.getResponse().getContentAsString();

		System.out.println("Response: " + content);

	}

}
