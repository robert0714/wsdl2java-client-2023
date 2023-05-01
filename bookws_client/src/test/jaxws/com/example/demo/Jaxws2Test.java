package com.example.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
 
import javax.xml.ws.BindingProvider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import orz.wsdl.tutorials.bookservice.Book;
import orz.wsdl.tutorials.bookservice.BookService;
import orz.wsdl.tutorials.bookservice.BookService_Service;
 
public class Jaxws2Test  {
	String mockEndpoint;
	 
	@BeforeEach 
	public void readMockPort() { 
        Properties p = new Properties();
		try (InputStream is = AbstractTest.class.getResourceAsStream("/test.properties")) {
			p.load(is);
		} catch (IOException e) {
			Assertions.fail(); 
		}
		String mockport = p.getProperty("mockport");
		assertNotNull(mockport);
		mockEndpoint = "http://localhost:" + mockport + "/testservice";
    }
	
	/**
	 * Test the JAX-WS service client by calling the SOAPUI mock service.
	 */
	@Test
	public void testTriggeredResponse() {
		BookService_Service testService = new BookService_Service();
		BookService testPort = testService.getBookServiceSOAP();
		
		BindingProvider binding = (BindingProvider) testPort;
		binding.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, mockEndpoint);
		binding.getRequestContext().put("com.sun.xml.internal.ws.request.timeout", 15000);// Timeout in millis 
		GetCustomersElement();
		
		 List<Book> list = testPort.getAllBooks();
		 
		System.out.println("-----------------------------------------------");
		System.out.println(list); 
	}
}
