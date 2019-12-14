package com.rabo.app.servcice.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.rabo.app.exception.DataValidationException;
import com.rabo.app.service.XMLService;
import com.rabo.dto.StatementResponse;

public class XMLServiceImpl implements XMLService {

	@Override
	public List<StatementResponse> getXMLResult(InputStream inputStream) throws DataValidationException {
		
		return null;
	}
	
	private List<StatementResponse> parseData() {
		List<StatementResponse> statementResponseList = new ArrayList<>();
		
		
		
		return statementResponseList;
	}

}
