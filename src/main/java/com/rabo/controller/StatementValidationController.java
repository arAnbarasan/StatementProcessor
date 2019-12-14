package com.rabo.controller;

import java.io.FileNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rabo.app.exception.DataValidationException;
import com.rabo.exception.ApiError;

@RestController
public class StatementValidationController {

	@RequestMapping(method=RequestMethod.POST, value="/validateStatement")
	public void processStatement(@RequestParam MultipartFile userInputfile) {
	
		//servicecall
		
	}
	
	@ExceptionHandler(FileNotFoundException.class)
	public ResponseEntity<ApiError> handleFileNotFoundExcetion(FileNotFoundException ex){
	ApiError error = new ApiError(HttpStatus.NOT_FOUND,"File Not Found", ex.getMessage());
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(DataValidationException.class)
	public ResponseEntity<ApiError> handleDataValidationException(DataValidationException ex){
	ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,"Internal Error", ex.getMessage());
		return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
}
