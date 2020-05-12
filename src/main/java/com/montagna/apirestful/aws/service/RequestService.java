package com.montagna.apirestful.aws.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.montagna.apirestful.aws.domain.Request;
import com.montagna.apirestful.aws.domain.User;
import com.montagna.apirestful.aws.domain.enums.RequestState;
import com.montagna.apirestful.aws.exception.NotFoundException;
import com.montagna.apirestful.aws.model.PageModel;
import com.montagna.apirestful.aws.model.PageRequestModel;
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
		return request.orElseThrow(() -> new NotFoundException("Não foi possivel encontrar request com o Id = "+id));
	}
	
	public List<Request> listAll() {
		List<Request> list = repo.findAll();
		return list;
	}
	
	public PageModel<Request> listAllOnLazyMode(PageRequestModel pr){
		Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());
		Page<Request> page = repo.findAll(pageable);
		
		PageModel<Request> pm = new PageModel<Request>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
		return pm;
	}
	
	public List<Request> listByOwnerId(Long id){
		List<Request> list = repo.findAllByOwnerId(id);
		return list;
	}
	
	public PageModel<Request> listByOwnerIdOnLazyLoading(Long id, PageRequestModel pr){
		Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());
		Page<Request> page = repo.findAllByOwnerId(id, pageable);
		
		PageModel<Request> pm = new PageModel<Request>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
		
		return pm;
	}
	
//	public int updateStatus(Long id, RequestState state) {
//		return repo.updateStatus(id, state);
//	}
}
