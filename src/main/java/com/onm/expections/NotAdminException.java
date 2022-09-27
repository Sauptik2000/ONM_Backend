package com.onm.expections;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.LOCKED)
public class NotAdminException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public NotAdminException(String message) {
		super(message);
	}
}
