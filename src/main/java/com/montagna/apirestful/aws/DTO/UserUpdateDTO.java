package com.montagna.apirestful.aws.DTO;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.montagna.apirestful.aws.domain.Request;
import com.montagna.apirestful.aws.domain.RequestStage;
import com.montagna.apirestful.aws.domain.User;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserUpdateDTO {
	
	@NotBlank(message = "Name Required")
	private String name;
	
	@Email(message = "Invalid Email Address")
	private String email;
	
	@Size(min = 7, max = 99, message = "Password must be between 7 and 99")
	private String password;

	private List<Request> requests = new ArrayList<Request>();
	private List<RequestStage> stages = new ArrayList<RequestStage>();

	public User transformToUser() {
		User user = new User(
				null, 
				this.name, 
				this.email, 
				this.password, 
				null, 
				this.requests, 
				this.stages);
		return user;
	}
}
