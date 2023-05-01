package com.example.demo;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractTest {
	Logger LOG = LoggerFactory.getLogger( getClass());
	String wsdl = "wsapear-rw-istio-system.apps.ocp.iisi.test";
//	String wsdl = "localhost:8080";
//	String wsdl = "axis1-ws-mbms-morder-feature.apps.ocp.iisi.test";
	//請記得要改/etc/hosts  60.250.171.28   wsapear-rw-istio-system.apps.ocp.iisi.test
	
	
	
	static	class Param{
		String wsdl, cmd  ,param ,oprcode  ,fromsite ,ip;
		 
		public Param(String wsdl,String cmd, String param, String oprcode, String fromsite, String ip) {
			super();
			this.wsdl = wsdl ;
			this.cmd = cmd;
			this.param = param;
			this.oprcode = oprcode;
			this.fromsite = fromsite;
			this.ip = ip;
		}
		
	}
	
	
	public   <T> void  commonTest(final Function<Param,String>  action)   { 
		String cmd = "cusqry";
		String param = "&lt;telnum&gt;${telnum}&lt;/telnum&gt;&lt;detail&gt;Y&lt;/detail&gt;";
		String oprcode = "A123456798";
		String fromsite = "EAI";
		String ip = "10.144.94.141";		 
		String response = action.apply(new Param(wsdl, cmd, param, oprcode, fromsite, ip)); 		
		System.out.println("------------------");
		System.out.println(response);
		System.out.println("------------------");
	}
}
