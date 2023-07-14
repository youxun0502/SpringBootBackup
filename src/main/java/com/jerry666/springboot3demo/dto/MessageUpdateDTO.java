package com.jerry666.springboot3demo.dto;

import lombok.Data;

@Data
public class MessageUpdateDTO {

	private Integer id;
	
	private String newText;
	
	public MessageUpdateDTO() {
	}

}
