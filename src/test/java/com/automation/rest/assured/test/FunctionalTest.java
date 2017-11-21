package com.automation.rest.assured.test;

import org.testng.annotations.BeforeClass;


import io.restassured.RestAssured;

public class FunctionalTest {

	@BeforeClass
	public static void setup() {
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

		String baseHost = System.getProperty("server.host");
		if (baseHost == null) {
			baseHost = "https://jsonplaceholder.typicode.com";
		}
		RestAssured.baseURI = baseHost;
	}

}
