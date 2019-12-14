package com.rabo.app.service;

import java.io.InputStream;
import java.util.List;

import com.rabo.app.exception.DataValidationException;
import com.rabo.dto.StatementResponse;

public interface CSVService {

	List<StatementResponse> getCSVResult(InputStream inputStream) throws DataValidationException;
}
