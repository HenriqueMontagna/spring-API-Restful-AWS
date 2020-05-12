package com.montagna.apirestful.aws.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.montagna.apirestful.aws.domain.Request;
import com.montagna.apirestful.aws.domain.RequestStage;
import com.montagna.apirestful.aws.domain.User;
import com.montagna.apirestful.aws.model.PageModel;
import com.montagna.apirestful.aws.model.PageRequestModel;
import com.montagna.apirestful.aws.service.RequestService;
import com.montagna.apirestful.aws.service.RequestStageService;

@RestController
@RequestMapping(value = "requests")
public class RequestResource {
	
	@Autowired
	private RequestService requestService;
	
	@Autowired
	private RequestStageService requestStageService;
	
	@PostMapping
	public ResponseEntity<Request> save(@RequestBody Request request){
		Request createdRequest = requestService.save(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
	}
	
	@PutMapping("/{requestId}")
	public ResponseEntity<Request> update(@PathVariable("requestId") Long id ,@RequestBody Request request){
		request.setId(id);
		Request updatedRequest = requestService.update(request);
		return ResponseEntity.ok(updatedRequest);
	}

	@GetMapping("/{requestId}")
	public ResponseEntity<Request> getById(@PathVariable("requestId") Long id){
		Request request = requestService.getById(id);
		return ResponseEntity.ok(request);
		}
	
	@GetMapping
	public ResponseEntity<PageModel<Request>> listAllOnLazyMode(
			@RequestParam(name = "page") int page,
			@RequestParam(name = "size") int size){
		
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<Request> pm = requestService.listAllOnLazyMode(pr);
		
		return ResponseEntity.ok(pm);
	}
	
	//listAllByRequestId
	@GetMapping("/{requestId}/request-stages")
	public ResponseEntity<PageModel<RequestStage>> listAllByRequestId(@PathVariable("requestId") Long id,
			@RequestParam("page") int page,
			@RequestParam("size") int size){
		PageRequestModel pr = new PageRequestModel(page, size);
		
		PageModel<RequestStage> pm = requestStageService.listAllByRequestIdOnLazyMode(id, pr);
		return ResponseEntity.ok(pm);
	}
}
