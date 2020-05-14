package com.montagna.apirestful.aws.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.montagna.apirestful.aws.domain.User;
import com.montagna.apirestful.aws.domain.enums.Role;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
public class UserRepositoryTests {
	
	@Autowired private UserRepository userRepository;

	@Disabled("Usuario Já Inserido Nos Testes")
	@Test
	@Order(1)
	public void saveTest() {
		User user = new User(null, "Henrique", "henriqueferreia@brq.com", "123", Role.ADMINISTRATOR, null, null);
		User createdUser = userRepository.save(user);
		
		assertThat(createdUser.getId()).isEqualTo(1L);	
	}
	
	@Disabled
	@Test
	public void updateTest() {
		User user = new User(1L, "Henrique Montagna", "henriqueferreia@brq.com", "123", Role.ADMINISTRATOR, null, null);
		User updatedUser = userRepository.save(user);
		
		assertThat(updatedUser.getName()).isEqualTo("Henrique Montagna");
	}
	
	@Disabled
	@Test
	public void getByIdTest() {
		Optional<User> result = userRepository.findById(1L);
		User user = result.get();
		
		assertThat(user.getPassword()).isEqualTo("123");
		
	}
	
	@Disabled
	@Test
	public void listTest() {
		List<User> users = userRepository.findAll();
		
		assertThat(users.size()).isEqualTo(1);
	}
	
	@Disabled
	@Test
	public void loginTest() {
		Optional<User> result = userRepository.login("henriqueferreia@brq.com", "123");
		User loggedUser = result.get();
		
		assertThat(loggedUser.getId()).isEqualTo(1L);
	}
	
	@Disabled
	@Test
	public void updateRoleTest() {
		int affectedRows = userRepository.updateRole(16L, Role.ADMINISTRATOR);
		assertThat(affectedRows).isEqualTo(1);
	}
	
	
}
