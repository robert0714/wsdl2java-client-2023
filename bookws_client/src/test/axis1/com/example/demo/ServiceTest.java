package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.io.InputStream; 
import java.rmi.RemoteException;
import java.util.Properties;

import javax.xml.rpc.ServiceException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach; 
import org.junit.jupiter.api.Test;

import com.cleverbuilder.www.BookService.Book;
import com.cleverbuilder.www.BookService.BookService_PortType;
import com.cleverbuilder.www.BookService.BookService_ServiceLocator; 
  
 
 
 
public class ServiceTest {
	String mockEndpoint;
	 
	@BeforeEach 
	public void readMockPort() { 
        Properties p = new Properties();
		try (InputStream is = ServiceTest.class.getResourceAsStream("/test.properties")) {
			p.load(is);
		} catch (IOException e) {
			Assertions.fail(); 
		}
		String mockport = p.getProperty("mockport");
		assertNotNull(mockport);
		mockEndpoint = "http://localhost:" + mockport + "/testservice";
    }
	
	/**
	 * Test the AXIS-1 service client by calling the SOAPUI mock service.
	 * @throws RemoteException 
	 * @throws ServiceException 
	 */
	@Test
	public void testAction_XML() throws RemoteException, ServiceException {
		BookService_ServiceLocator server = new BookService_ServiceLocator();
		server.setBookServiceSOAPEndpointAddress(mockEndpoint);
		
		
		BookService_PortType service = server.getBookServiceSOAP(); 
		
		Book[] result = service.getAllBooks();
		 
		for (Book book : result) {
			System.out.println(book.getAuthor());
			System.out.println(book.getID());
			System.out.println(book.getTitle());
		}
		System.out.println("-----------------------------------------------");
		
	}
	 
}
