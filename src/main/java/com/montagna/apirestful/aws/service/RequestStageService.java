package com.montagna.apirestful.aws.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.montagna.apirestful.aws.domain.RequestStage;
import com.montagna.apirestful.aws.domain.enums.RequestState;
import com.montagna.apirestful.aws.exception.NotFoundException;
import com.montagna.apirestful.aws.model.PageModel;
import com.montagna.apirestful.aws.model.PageRequestModel;
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
		return result.orElseThrow(() -> new NotFoundException("Não foi possivel encontrar stage com o Id = "+id));
	}
	
	public PageModel<RequestStage> listAllByRequestIdOnLazyMode(Long requestId, PageRequestModel pr){
		Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());
		Page<RequestStage> page = requestStageRepo.findAllByRequestId(requestId, pageable);
		
		PageModel<RequestStage> pm = new PageModel<RequestStage>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
		return pm;
	}
	
}
