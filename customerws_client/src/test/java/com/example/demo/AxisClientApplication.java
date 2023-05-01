package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 

import java.net.URL;

import javax.xml.namespace.QName;

@SpringBootApplication
public class AxisClientApplication   {

	private static Logger LOG = LoggerFactory.getLogger(AxisClientApplication.class);

	public static void main(String[] args) {
		LOG.info("STARTING THE APPLICATION");
		SpringApplication.run(AxisClientApplication.class, args);
		LOG.info("APPLICATION FINISHED");
	}
 
}
