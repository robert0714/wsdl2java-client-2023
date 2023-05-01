package com.example.demo;

import java.net.MalformedURLException;
import java.net.URL;  

import javax.xml.namespace.QName; 

import org.junit.jupiter.api.Test; 
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CxfTest extends AbstractTest{	   
	/**
	 *  測試程式： CXF (重新執行wsdl2java)
	***/
	@Test
	void testCXFClient() {
		commonTest((u)->{
			String result =null;
			try {
				
				String endpoint = "http://" + u.wsdl + "/WSApServer/Dispatcher?wsdl";	
				URL wsdlURL = new URL(endpoint);
				QName SERVICE_NAME = new javax.xml.namespace.QName("http://wsquerymgr.bm.ejb.morder.cht.com", "Dispatcher");
				javax.xml.ws.Service service = javax.xml.ws.Service.create(wsdlURL, SERVICE_NAME);
				com.cht.morder.ejb.bm.wsquerymgr.Dispatcher client = service.getPort(com.cht.morder.ejb.bm.wsquerymgr.Dispatcher.class);
				result = client.dispatch(u.cmd, u.param, u.oprcode, u.fromsite, u.ip);
				
			} catch (MalformedURLException e) {
				LOG.error(e.getMessage() ,e ) ;
			}
			return result ;
		});
	} 
	 
}
