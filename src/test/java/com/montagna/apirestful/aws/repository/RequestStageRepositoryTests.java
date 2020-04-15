package com.montagna.apirestful.aws.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
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

import com.montagna.apirestful.aws.domain.Request;
import com.montagna.apirestful.aws.domain.RequestStage;
import com.montagna.apirestful.aws.domain.User;
import com.montagna.apirestful.aws.domain.enums.RequestState;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
public class RequestStageRepositoryTests {
	
	@Autowired RequestStageRepository repo;

	@Disabled
	@Test
	@Order(1)
	public void saveTest() {
		Request request = new Request();
		request.setId(1L);
		
		User owner = new User();
		owner.setId(1L);
		
		RequestStage stage = new RequestStage(null, new Date(), "Pedido Realizado", RequestState.CLOSED, request, owner); 
		RequestStage returned = repo.save(stage);
		
		
		assertThat(returned.getId()).isEqualTo(1L);
	}
	
	@Test
	@Order(2)
	public void getByIdTest() {
		Optional<RequestStage> response = repo.findById(1L);
		RequestStage createdStage = response.get();
		
		assertThat(createdStage.getDescription()).isEqualTo("Pedido Realizado");
	}
	
	@Test
	@Order(3)
	public void listAllById() {
		List<RequestStage> list = repo.findAllByRequestId(1L);
		assertThat(list.size()).isEqualTo(1);
	}
}
