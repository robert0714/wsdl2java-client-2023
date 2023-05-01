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

import com.cleverbuilder.bookservice.Book;
import com.cleverbuilder.bookservice.BookService;
import com.cleverbuilder.bookservice.BookService_Service;
import com.cleverbuilder.bookservice.GetAllBooks;
import com.cleverbuilder.bookservice.GetAllBooksResponse;
 
 
public class ServiceTest  {
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

		GetAllBooks action = new GetAllBooks();

		GetAllBooksResponse response = testPort.getAllBooks(action);
		response.getBook();

		List<Book> result = response.getBook();
		for (Book book : result) {
			System.out.println(book.getAuthor());
			System.out.println(book.getID());
			System.out.println(book.getTitle());
		}
		System.out.println("-----------------------------------------------");
	}
}
