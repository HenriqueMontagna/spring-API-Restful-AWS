package com.montagna.apirestful.aws.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.montagna.apirestful.aws.domain.Request;
import com.montagna.apirestful.aws.domain.enums.RequestState;
import com.montagna.apirestful.aws.repository.RequestRepository;

@Service
public class RequestService {
	
	@Autowired RequestRepository repo;
	
	public Request save(Request request) {
		request.setState(RequestState.OPEN);
		request.setCreationDate(new Date());
		
		Request createdRequest = repo.save(request);
		return createdRequest;
	}
	
	public Request update(Request request) {
		Request updatedRequest = repo.save(request);
		return updatedRequest;
	}
	
	public Request getById(Long id) {
		Optional<Request> request = repo.findById(id);
		return request.get();
	}
	
	public List<Request> listAll() {
		List<Request> list = repo.findAll();
		return list;
	}
	
	public List<Request> listByOwnerId(Long id){
		List<Request> list = repo.findAllByOwnerId(id);
		return list;
	}
	
//	public int updateStatus(Long id, RequestState state) {
//		return repo.updateStatus(id, state);
//	}
}
