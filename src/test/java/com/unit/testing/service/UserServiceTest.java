package com.unit.testing.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.unit.testing.model.User;
import com.unit.testing.repository.UserRepository;

/**
 * @InjectMocks – Instantiates testing object instance and tries to inject
 *              fields annotated with @Mock or @Spy into private fields of
 *              testing object
 * @Mock – Creates mock instance of the field it annotates
 * @Spy – Creates spy for instance of annotated field (for services)
 *
 */

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;

	@Test
	public void testGetAllUsers() {

		List<User> userList = Arrays.asList(new User("tmg1", "pass1"));
		Mockito.when(userRepository.findAll()).thenReturn(userList);

		// notice different Mockito syntax for spy
		// Mockito.doReturn(TEST_SHIRT_PRICE).when(priceService).getActualPrice(Item.SHIRT);
		// Mockito.doReturn(TEST_SHOES_PRICE).when(priceService).getActualPrice(Item.SHOES);

		List<User> found = userService.getAllUsers();

		assertEquals(1, found.size());
		assertNotNull(found.get(0));
		assertEquals(userList.get(0).getName(), found.get(0).getName());
		assertEquals(userList.get(0).getPassword(), found.get(0).getPassword());

	}

}
