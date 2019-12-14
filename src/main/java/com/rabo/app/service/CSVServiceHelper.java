package com.rabo.app.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.rabo.app.exception.DataValidationException;
import com.rabo.app.validate.DataValidator;
import com.rabo.dto.CustomerStatementDTO;
import com.rabo.dto.StatementResponse;

@Service
public class CSVServiceHelper {

	@Autowired
	private DataValidator csvDataValidator;

	private static final String SAMPLE_CSV_FILE_PATH = "./src/main/resources/records.csv";

	private static final Logger logger = LoggerFactory.getLogger(CSVServiceHelper.class);

	public List<CustomerStatementDTO> getCSV() {
		List<CustomerStatementDTO> csvList = null;
		logger.debug("getCSV method start");
		try {
			Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));

			ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
			strategy.setType(CustomerStatementDTO.class);
			String[] memberFieldsToBindTo = { "Reference", "AccountNumber", "Description", "StartBalance", "Mutation",
					"EndBalance" };
			strategy.setColumnMapping(memberFieldsToBindTo);

			CsvToBean<CustomerStatementDTO> csvToBean = new CsvToBeanBuilder<CustomerStatementDTO>(reader)
					.withMappingStrategy(strategy).withSkipLines(1).withIgnoreLeadingWhiteSpace(true).build();

			Iterator<CustomerStatementDTO> myUserIterator = csvToBean.iterator();

			while (myUserIterator.hasNext()) {
				CustomerStatementDTO customerStatementDTO = myUserIterator.next();
				if (csvList == null)
					csvList = new ArrayList<>();
				csvList.add(customerStatementDTO);
			}
		} catch (Exception e) {
			logger.error("Exception in getCSV() method" + e.getMessage());
		}
		logger.debug("getCSV method end");
		return csvList;
	}

	public List<StatementResponse> validateCSVData(List<CustomerStatementDTO> data) throws DataValidationException {

		List<StatementResponse> validatedList = null;
		return csvDataValidator.validateData(data,validatedList);

	}

}