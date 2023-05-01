package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.io.InputStream; 
import java.rmi.RemoteException;
import java.util.Properties;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach; 
import org.junit.jupiter.api.Test;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient; 
import org.apache.axis2.kernel.http.HTTPConstants; 
  

import orz.wsdl.tutorials.bookservice.BookServiceStub;
import orz.wsdl.tutorials.bookservice.BookServiceStub.Book_type0;
import orz.wsdl.tutorials.bookservice.BookServiceStub.GetAllBooksResponse; 
 
 
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
	 * Test the AXIS-2 service client by calling the SOAPUI mock service.
	 * @throws RemoteException 
	 */
	@Test
	public void testAction_XML() throws RemoteException {
		BookServiceStub stub = new BookServiceStub(mockEndpoint);
        ServiceClient sc = stub._getServiceClient();
        
        Options options = sc.getOptions();
        options.setProperty(HTTPConstants.SO_TIMEOUT, 15000);
        options.setProperty(HTTPConstants.CONNECTION_TIMEOUT, 15000);
        sc.setOptions(options);	        
        stub._setServiceClient(sc);
        
        BookServiceStub.GetAllBooks   action =  new BookServiceStub.GetAllBooks(); 
		
		GetAllBooksResponse response = stub.getAllBooks(action);
        
        Book_type0[] result = response.getBook();
        for (Book_type0 book: result) {
        	System.out.println(book.getAuthor()); 
        	System.out.println(book.getID());
        	System.out.println(book.getTitle());
        }
		System.out.println("-----------------------------------------------");
		
	}
	 
}
