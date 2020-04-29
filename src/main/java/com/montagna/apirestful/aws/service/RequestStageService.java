package com.montagna.apirestful.aws.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.montagna.apirestful.aws.domain.RequestStage;
import com.montagna.apirestful.aws.domain.enums.RequestState;
import com.montagna.apirestful.aws.repository.RequestRepository;
import com.montagna.apirestful.aws.repository.RequestStageRepository;

@Service
public class RequestStageService {
	
	@Autowired
	RequestStageRepository requestStageRepo;
	
	@Autowired
	RequestRepository requestRepo;
	
	public RequestStage save(RequestStage stage) {
		stage.setRealizationDate(new Date());
		
		RequestStage createdStage = requestStageRepo.save(stage);
		
		Long requestId = stage.getRequest().getId();
		RequestState state = stage.getState();
		
		requestRepo.updateStatus(requestId, state);
		
		return createdStage;
	}
	
	public RequestStage getById(Long id) {
		Optional<RequestStage> result = requestStageRepo.findById(id);
		return result.get();
	}
	
	public List<RequestStage> listAllByRequestId(Long id){
		List<RequestStage> list = requestStageRepo.findAllByRequestId(id);
		return list;
	}
	
}
