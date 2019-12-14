package com.rabo.app.service;

import java.util.List;

import com.rabo.app.exception.DataValidationException;
import com.rabo.dto.StatementResponse;

public interface CSVService {

	List<StatementResponse> getCSVResult() throws DataValidationException;
}
