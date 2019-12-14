package com.rabo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class StatementValidationController {

	@RequestMapping(method=RequestMethod.POST, value="/validateStatement")
	public void processStatement(@RequestParam MultipartFile userInputfile) {
		
	}
}
