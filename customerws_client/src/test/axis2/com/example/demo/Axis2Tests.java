package com.example.demo;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException; 
import java.util.function.Function;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Axis2Tests  extends AbstractTest{ 
	 
	/**
	 *  測試程式： AXIS 2 (重新執行wsdl2java)
	***/
	@Test
	void testAxis2Client() {
		commonTest((u)->{
			String result =null;
			try {
				String wsdlurl = "http://" + u.wsdl + "/WSApServer/Dispatcher?wsdl";

				com.cht.morder.ejb.bm.wsquerymgr.DispatcherStub stub = new com.cht.morder.ejb.bm.wsquerymgr.DispatcherStub(wsdlurl);

				com.cht.morder.ejb.bm.wsquerymgr.DispatcherStub.Dispatch dispatch = new com.cht.morder.ejb.bm.wsquerymgr.DispatcherStub.Dispatch();
				dispatch.setString( u.cmd);
				dispatch.setString0( u.param);
				dispatch.setString1( u.oprcode);
				dispatch.setString2( u.fromsite);
				dispatch.setString3( u.ip);

				com.cht.morder.ejb.bm.wsquerymgr.DispatcherStub.DispatchResponse response= stub.dispatch(dispatch);
				result = response.getResult();
				
			} catch (  RemoteException e) {
				LOG.error(e.getMessage() ,e ) ;
			} 		
			return result ;
		});
	} 
	
	
}
