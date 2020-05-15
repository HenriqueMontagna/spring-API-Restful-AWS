package com.montagna.apirestful.aws.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.montagna.apirestful.aws.DTO.RequestStageSaveDTO;
import com.montagna.apirestful.aws.domain.RequestStage;
import com.montagna.apirestful.aws.service.RequestStageService;

@RestController
@RequestMapping(value = "request-stage")
public class RequestStageResource {

	@Autowired
	private RequestStageService requestStageService;
	
	//save
	@PostMapping
	public ResponseEntity<RequestStage> save(@RequestBody @Valid RequestStageSaveDTO stageDTO){
		RequestStage createdStage = requestStageService.save(stageDTO.transformToStage());
		return ResponseEntity.status(HttpStatus.CREATED).body(createdStage);
	}
	
	//getById
	@GetMapping("/{id}")
	public ResponseEntity<RequestStage> getById(@PathVariable("id") Long id){
		RequestStage stage = requestStageService.getById(id);
		return ResponseEntity.ok(stage);
	}

}
