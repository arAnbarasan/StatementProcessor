package com.rabo.app.servcice.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.rabo.app.exception.DataValidationException;
import com.rabo.app.service.XMLService;
import com.rabo.dto.StatementResponse;

@Component
public class XMLServiceImpl implements XMLService {

	@Override
	public List<StatementResponse> getXMLResult(InputStream inputStream) throws DataValidationException, ParserConfigurationException, SAXException, IOException {
		parseData(inputStream);
		return null;
	}
	
	private List<StatementResponse> parseData(InputStream inputStream) throws ParserConfigurationException, SAXException, IOException {
		List<StatementResponse> statementResponseList = new ArrayList<>();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
		DocumentBuilder db = dbf.newDocumentBuilder();  
		Document doc = db.parse(inputStream);  
		doc.getDocumentElement().normalize();
		
		NodeList nodeList = doc.getElementsByTagName("records");
		
		for (int itr = 0; itr < nodeList.getLength(); itr++)   
		{  
		Node node = nodeList.item(itr);  
		System.out.println("\nNode Name :" + node.getNodeName());  
		if (node.getNodeType() == Node.ELEMENT_NODE)   
		{  
		Element eElement = (Element) node;  
		NodeList elementsByTagName = eElement.getElementsByTagName("record");
		for(int tag=0;tag<elementsByTagName.getLength();tag++) {
			
		}
		System.out.println(": "+ eElement.getElementsByTagName("record").item(0).getTextContent());  
		 
		}  
		}  
		
		return statementResponseList;
	}

}
