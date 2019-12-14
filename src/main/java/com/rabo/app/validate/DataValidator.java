package com.rabo.app.validate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.rabo.app.exception.DataValidationException;
import com.rabo.app.service.CSVServiceHelper;
import com.rabo.dto.CustomerStatementDTO;
import com.rabo.dto.StatementResponse;

@Component
public class DataValidator {

	private static final Logger logger = LoggerFactory.getLogger(CSVServiceHelper.class);

	public List<StatementResponse> validateData(List<CustomerStatementDTO> data,
			List<StatementResponse> validatedList) throws DataValidationException{
		logger.debug("validateData method start");
		Set<String> uniqueData = new HashSet<>();

		try {
			for (CustomerStatementDTO st : data) {

				// 1. Validate duplicate entries
				if (uniqueData.add(st.getReference()) == false) {
					if (validatedList == null)
						validatedList = new ArrayList<>();
					StatementResponse transactionReference = new StatementResponse();
					transactionReference.setDescription(st.getDescription());
					transactionReference.setTransactionReference(st.getReference());

					validatedList.add(transactionReference);
					logger.debug("found a duplicate data : " + data);
				}
				// 2. Balance validation
				BigDecimal startBalance = new BigDecimal(st.getStartBalance());
				BigDecimal mutation = new BigDecimal(st.getMutation());
				BigDecimal endBalance = new BigDecimal(st.getEndBalance());
				BigDecimal check;
				check = startBalance.add(mutation);

				if (!check.equals(endBalance)) {
					if (validatedList == null)
						validatedList = new ArrayList<>();
					StatementResponse endBalanceResponse = new StatementResponse();
					endBalanceResponse.setDescription(st.getDescription());
					endBalanceResponse.setTransactionReference(st.getReference());
					validatedList.add(endBalanceResponse);
					logger.debug("balance validation failed data : " + data);
				}
			}
		}
		catch (Exception e) {
			logger.error("Exception in validateData() method" + e.getMessage());
		}
		logger.debug("validateData method end");
		return validatedList;

	}
}
