package com.montagna.apirestful.aws.DTO;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.montagna.apirestful.aws.domain.Request;
import com.montagna.apirestful.aws.domain.RequestStage;
import com.montagna.apirestful.aws.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class RequestSaveDTO {
	
	@NotBlank(message = "Subject must not be blank")
	private String subject;
	
	private String description;
	
	@NotNull(message = "Owner must not be null")
	private User owner;
	
	private List<RequestStage> stages = new ArrayList<RequestStage>();

	
	public Request transformToRequest() {
		Request request = new Request(
				null, 
				this.subject, 
				this.description, 
				null, 
				null, 
				this.owner, 
				this.stages);
		return request;
	}
}
