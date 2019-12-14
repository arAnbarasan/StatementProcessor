package com.rabo.app.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.rabo.app.exception.DataValidationException;
import com.rabo.dto.StatementResponse;

public interface XMLService {
	List<StatementResponse> getXMLResult(InputStream inputStream) throws DataValidationException, ParserConfigurationException, SAXException, IOException;
}
