package com.montagna.apirestful.aws.DTO;

import com.montagna.apirestful.aws.domain.enums.Role;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserUpdateRoleDTO {
	
	private Role role;

}
