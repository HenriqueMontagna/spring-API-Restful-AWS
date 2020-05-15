package com.montagna.apirestful.aws.DTO;

import javax.validation.constraints.NotNull;

import com.montagna.apirestful.aws.domain.Request;
import com.montagna.apirestful.aws.domain.RequestStage;
import com.montagna.apirestful.aws.domain.User;
import com.montagna.apirestful.aws.domain.enums.RequestState;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class RequestStageSaveDTO {

	private String description;
	
	@NotNull(message = "RequestState Required")
	private RequestState state;
	
	@NotNull(message = "Request Required")
	private Request request;
	
	@NotNull(message = "Owner Required")
	private User owner;

	public RequestStage transformToStage() {
		RequestStage stage = new RequestStage(
				null, 
				null, 
				this.description, 
				this.state, 
				this.request, 
				this.owner);
		
		return stage;
	}
	
}
