package com.rabo.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.rabo.app.service.CSVService;
import com.rabo.dto.StatementResponse;

import lombok.extern.slf4j.Slf4j;
import com.rabo.exception.ApiError;

@Slf4j
@RestController
public class StatementValidationController {

	@Autowired
	private CSVService csvService;
	
	@RequestMapping(method=RequestMethod.POST, value="/validateStatement")
	public ResponseEntity<Object> processStatement(@RequestParam MultipartFile userInputFile) throws DataValidationException, IOException {
		log.info("Welcome to Statement processor validate");
		if(userInputFile != null) {
			String fileName = userInputFile.getOriginalFilename();
			if(fileName != null) {
				
				String fileExtension = fileName.substring(fileName.lastIndexOf(".")+1);
				if(fileExtension != null) {
					if(fileExtension.equalsIgnoreCase("xml")) {
						log.info("Received XML File and invoke XML service");
						return null;
					} else if(fileExtension.equalsIgnoreCase("csv")) {
						log.info("Received CSV File and invoke CSV service");
						List<StatementResponse> csvResult = csvService.getCSVResult(userInputFile.getInputStream());
						return new ResponseEntity(csvResult, HttpStatus.OK);

					} else {
						//throw new FileNotFoundException("Invalid user input file");
						new ResponseEntity(null, HttpStatus.OK);
					}
				}
			} else {
				log.error("User input file not exists.");
				throw new FileNotFoundException("User input file not exists.");
			}
		} else {
			log.error("User input file not exists.");
			throw new FileNotFoundException("User input file not exists.");
		}
		return null;
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
