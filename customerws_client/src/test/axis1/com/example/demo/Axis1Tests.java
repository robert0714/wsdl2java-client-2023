package com.example.demo;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.junit.jupiter.api.Test; 
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Axis1Tests extends AbstractTest{ 
	/**
	 *  測試程式： AXIS 1 (重新執行wsdl2java)
	***/
	@Test
	void testAxis1Client() {
		commonTest((u)->{
			String result =null;
			try {
				String endpoint = "http://" + u.wsdl + "/WSApServer/Dispatcher?wsdl";
				com.cht.morder.ejb.bm.wsquerymgr.Dispatcher_ServiceLocator  locator = new com.cht.morder.ejb.bm.wsquerymgr.Dispatcher_ServiceLocator(endpoint,
						new javax.xml.namespace.QName("http://wsquerymgr.bm.ejb.morder.cht.com", "Dispatcher"));
				
				locator.setEndpointAddress("DispatcherPort", endpoint);
				com.cht.morder.ejb.bm.wsquerymgr.Dispatcher_PortType service = locator.getDispatcherPort();
				result = service.dispatch(u.cmd, u.param, u.oprcode, u.fromsite, u.ip);
				
			} catch (ServiceException | RemoteException e) {
				LOG.error(e.getMessage() ,e ) ;
			} 		
			return result ;
		});
	}	 
}
