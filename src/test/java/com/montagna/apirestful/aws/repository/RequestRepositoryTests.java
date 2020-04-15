package com.montagna.apirestful.aws.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.montagna.apirestful.aws.domain.Request;
import com.montagna.apirestful.aws.domain.User;
import com.montagna.apirestful.aws.domain.enums.RequestState;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
public class RequestRepositoryTests {
	
	@Autowired RequestRepository repo;
	
	@Disabled("Requisição já salva no banco de dados")
	@Test
	@Order(1)
	public void saveTest() {
		User user = new User();
		user.setId(1L);
		Request req = new Request(null, "Aparelho de Audio", "Grava e Reproduz", new Date(), RequestState.OPEN, user, null);
		Request savedRequest = repo.save(req);
		
		assertThat(savedRequest.getId()).isEqualTo(1L);
	}
	
	@Test
	@Order(2)
	public void updateTest() {
		User user = new User();
		user.setId(1L);
		Request req = new Request(1L, "Aparelho de Audio", "Grava e Reproduz Áudio", null, RequestState.OPEN, user, null);
		Request updatedRequest = repo.save(req);
		
		assertThat(updatedRequest.getDescription()).isEqualTo("Grava e Reproduz Áudio");
	}
	
	@Test
	@Order(3)
	public void getByIdTest() {
		Optional<Request> response = repo.findById(1L);
		Request req = response.get();
	
		assertThat(req.getSubject()).isEqualTo("Aparelho de Audio");
	}

	@Test
	@Order(4)
	public void listTest() {
		List<Request> list = repo.findAll();
		
		assertThat(list.size()).isEqualTo(1);
	}
	
	@Test
	@Order(5)
	public void listByOwnerIdTest() {
		List<Request> list = repo.findAllByOwnerId(1L);
		assertThat(list.size()).isEqualTo(1);
	}
	
	@Test
	@Order(6)
	public void updateStatusTest() {
		int affectedRows = repo.updateStatus(1L, RequestState.IN_PROGRESS);
		assertThat(affectedRows).isEqualTo(1);
	}
}
