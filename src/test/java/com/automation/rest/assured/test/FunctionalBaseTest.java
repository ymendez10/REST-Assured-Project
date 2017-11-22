package com.automation.rest.assured.test;

import org.testng.annotations.BeforeClass;

import com.automation.rest.assured.util.PropertiesUtil;

import io.restassured.RestAssured;

public class FunctionalBaseTest {
	
	private PropertiesUtil properties;

	@BeforeClass
	public void setup() {
//		String port = System.getProperty("server.port");
//		if (port == null) {
//			RestAssured.port = Integer.valueOf(8080);
//		} else {
//			RestAssured.port = Integer.valueOf(port);
//		}
//
//		String basePath = System.getProperty("server.base");
//		if (basePath == null) {
//			basePath = "/comments";
//		}
//		RestAssured.basePath = basePath;
		
		properties = new PropertiesUtil(); 

		String baseHost = System.getProperty("resources.rest.project.server.host");
		if (baseHost == null) {
			baseHost = properties.getProperty("resources.rest.project.server.host");
		}
		RestAssured.baseURI = baseHost;
	}

}
