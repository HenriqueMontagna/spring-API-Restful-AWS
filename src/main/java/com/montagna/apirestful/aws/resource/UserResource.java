package com.montagna.apirestful.aws.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.montagna.apirestful.aws.DTO.UserLoginDTO;
import com.montagna.apirestful.aws.DTO.UserUpdateRoleDTO;
import com.montagna.apirestful.aws.domain.Request;
import com.montagna.apirestful.aws.domain.User;
import com.montagna.apirestful.aws.model.PageModel;
import com.montagna.apirestful.aws.model.PageRequestModel;
import com.montagna.apirestful.aws.service.RequestService;
import com.montagna.apirestful.aws.service.UserService;

@RestController
@RequestMapping(value = "users")
public class UserResource {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RequestService requestService;
	
	@PostMapping
	public ResponseEntity<User> save(@RequestBody User user){
		User createdUser = userService.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable("id") Long id, @RequestBody User user){
		user.setId(id);
		User updatedUser = userService.update(user);
		return ResponseEntity.ok(updatedUser);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> getById(@PathVariable(name = "id") Long id){
		User user = userService.getById(id);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping
	public ResponseEntity<PageModel<User>> listAllOnLazyMode(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size){
		
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<User> pm = userService.listAllOnLazyMode(pr);
		
		return ResponseEntity.ok(pm);
	}
	
	@GetMapping("/{userId}/requests")
	public ResponseEntity<PageModel<Request>> listAllRequestsById(
			@PathVariable("userId") Long id,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size){
		
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<Request> pm = requestService.listByOwnerIdOnLazyLoading(id, pr);
		
		return ResponseEntity.ok(pm);
	}
	
	
	@PostMapping(value = "/login")
	public ResponseEntity<User> login(@RequestBody UserLoginDTO user){
		User loggedUser = userService.login(user.getEmail(), user.getPassword());
		return ResponseEntity.ok(loggedUser);
	}
	
	@PatchMapping(value = "/role/{id}")
	public ResponseEntity<?> updateRole(
			@PathVariable("id") Long id,
			@RequestBody UserUpdateRoleDTO userDTO){
		User user = new User();
		user.setId(id);
		user.setRole(userDTO.getRole());
		
		userService.updateUserRole(user);
		User updatedUser = userService.getById(id);
		
		return ResponseEntity.ok(updatedUser);
	}
}
