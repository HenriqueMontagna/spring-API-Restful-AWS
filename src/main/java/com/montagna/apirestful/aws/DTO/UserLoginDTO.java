package com.montagna.apirestful.aws.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserLoginDTO{
	
	@Email(message = "Invalid Email Address")
	private String email;
	
	@NotBlank(message = "Password Required")
	private String password;
}
