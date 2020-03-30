package com.montagna.apirestful.aws.domain;

import java.util.Date;

import com.montagna.apirestful.aws.domain.enums.RequestState;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class RequestStage {

	private Long id;
	private Date realizationDate;
	private String description;
	private RequestState state;
	private Request request;
	private User user;
}
