package com.onm.expections;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	// handling Resourse not found
		@ExceptionHandler(ResourceNotFoundException.class)
		public ResponseEntity<?> resourceNotFoundHandling(ResourceNotFoundException exception, WebRequest request){
			ErrorDetails errorDetails = 
					new ErrorDetails(new Date(), exception.getMessage());
			return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
		}

			//handling already exist user
		@ExceptionHandler(ResourceAlreadyExistException.class)
		public ResponseEntity<?> resourceAlreadyExistHandling(ResourceAlreadyExistException exception, WebRequest request){
			ErrorDetails errorDetails = 
					new ErrorDetails(new Date(), exception.getMessage());
			return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
		}

		@ExceptionHandler(NotAdminException.class)
		public ResponseEntity<?> notAdminHandling(NotAdminException exception, WebRequest request){
			ErrorDetails errorDetails = 
					new ErrorDetails(new Date(), exception.getMessage());
			return new ResponseEntity<>(errorDetails, HttpStatus.LOCKED);
		}


		// handling global exception
		
		@ExceptionHandler(Exception.class)
		public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request){
			ErrorDetails errorDetails = 
					new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
			return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	    
}
