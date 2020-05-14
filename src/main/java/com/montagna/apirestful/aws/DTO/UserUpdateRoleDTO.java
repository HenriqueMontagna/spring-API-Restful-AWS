package com.montagna.apirestful.aws.DTO;

import javax.validation.constraints.NotNull;

import com.montagna.apirestful.aws.domain.enums.Role;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserUpdateRoleDTO {
	
	@NotNull(message = "Role Required")
	private Role role;

}
