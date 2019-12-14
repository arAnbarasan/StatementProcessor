package com.rabo.app.servcice.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabo.app.service.CSVService;
import com.rabo.app.service.CSVServiceHelper;
import com.rabo.app.exception.DataValidationException;
import com.rabo.dto.CustomerStatementDTO;
import com.rabo.dto.StatementResponse;

@Component
public class CSVServiceImpl implements CSVService {

	@Autowired
	private CSVServiceHelper csvServiceHelper;

	private static final Logger logger = LoggerFactory.getLogger(CSVServiceImpl.class);
	
	public List<StatementResponse> getCSVResult(InputStream inputStream) throws DataValidationException {
		logger.debug("getCSVResult method start");
		List<StatementResponse> resultList = null;
		List<CustomerStatementDTO> csvList = csvServiceHelper.getCSV(inputStream);
		
		if (csvList != null) {
			resultList = csvServiceHelper.validateCSVData(csvList);
		}
		logger.debug("getCSVResult method end");
		return resultList;
	}

}
