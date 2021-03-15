package com.unit.testing.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.unit.testing.model.User;

/**
 * If you are using Junit version < 5, so you have to
 * use @RunWith(SpringRunner.class) or @RunWith(MockitoJUnitRunner.class) etc.
 * 
 * If you are using Junit version = 5, so you have to
 * use @ExtendWith(SpringExtension.class) or @ExtendWith(MockitoExtension.class)
 * etc.
 * 
 */

/*
 * Use @AutoConfigureDataMongo with @SpringBootTest and this will resolve this
 * ambiguity issue. @SpringBootTest and @DataMongoTest cannot be used together.
 */

@SpringBootTest
@DirtiesContext
@ExtendWith(SpringExtension.class)
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	void whenFindByName_thenReturnEmployee() {

		// given
		User user = new User("tmg", "pass");
		userRepository.save(user);

		// when
		User found = userRepository.findByName(user.getName());

		// then
		assertThat(found.getName()).isEqualTo(user.getName());

	}

}
