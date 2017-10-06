package com.mvc.spittr.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Duplicated Sptille")
public class DuplicateSpittleException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8757993034661201744L;

}
