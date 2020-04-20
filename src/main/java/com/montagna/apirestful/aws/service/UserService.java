package com.montagna.apirestful.aws.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.montagna.apirestful.aws.domain.User;
import com.montagna.apirestful.aws.repository.UserRepository;
import com.montagna.apirestful.aws.service.util.HashUtil;

@Service
public class UserService {
	
	@Autowired UserRepository repo;
	
	public User save(User user) {
		String hash = HashUtil.getSecureHash(user.getPassword());
		user.setPassword(hash);
		
		User createdUser = repo.save(user);
		return createdUser;
	}
	
	public User update(User user) {
		String hash = HashUtil.getSecureHash(user.getPassword());
		user.setPassword(hash);
		
		User updatedUser = repo.save(user);
		return updatedUser;
	}
	
	public User getById(Long id) {
		Optional<User> user = repo.findById(id);
		return user.get();
	}

	public List<User> listAll(){
		List<User> list = repo.findAll();
		return list;
	}
	
	public User login(String email, String password) {
		password = HashUtil.getSecureHash(password);
		Optional<User> loggedUser = repo.login(email, password);
		return loggedUser.get();
	}
}
