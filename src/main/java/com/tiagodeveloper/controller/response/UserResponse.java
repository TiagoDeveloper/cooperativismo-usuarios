package com.tiagodeveloper.controller.response;

import java.io.Serializable;

import com.tiagodeveloper.enums.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private UserStatus status;

}
