package com.automation.rest.assured.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.ektorp.http.HttpStatus;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ResourcesRESTTest extends FunctionalBaseTest {

	@Test
	public void makeSureThatJsonpPlaceholderSiteIsUp() {
		given().when().get().then().assertThat().statusCode(HttpStatus.OK);
	}

	@Test(dependsOnMethods = "makeSureThatJsonpPlaceholderSiteIsUp")
	public void makeSureThatCommentsAreAvailable() {
		given().when().get("/comments").then().assertThat().statusCode(HttpStatus.OK);
	}

	@Test(dependsOnMethods = "makeSureThatCommentsAreAvailable")
	public void makeSureHeaderIsCorrect() {
		Response response = given().when().get("/comments");
		String contentType = response.header("Content-Type");
		assertEquals(contentType, "application/json; charset=utf-8");
		
		String serverType =  response.header("Server");
		assertEquals(serverType, "cloudflare-nginx");
	}	
	
	@Test(dependsOnMethods = "makeSureThatCommentsAreAvailable")
	public void makeSureNumberOfComments() {
		given()
		.when().get("/comments")
		.then().assertThat().body("size()", is(500));
	}
	
	@Test(dependsOnMethods = "makeSureThatCommentsAreAvailable")
	public void makeSureThatEmailIsNotEmpty(){
		JsonPath jsonPath = given()
		.when().get("/comments")
		.jsonPath();
		
		String email = jsonPath.getString("$.email");
		
		assertTrue(!email.isEmpty());// assert email is not empty 	
	}

	@Test(dependsOnMethods = "makeSureThatCommentsAreAvailable")
	public void makeSureThatCommentByIdIsRetrieved() {
		given().pathParam("id", 10)
		.when().get("/comments/{id}")
		.then().assertThat().body("id", equalTo(10));
	}
	
	@Test(dependsOnMethods = "makeSureThatCommentsAreAvailable")
	public void makeSureThatCommentRetrievedByIdContainsAtSign() {
		given().pathParam("id", 100)
		.when().get("/comments/{id}")
		.then().assertThat().body("email", containsString("@"));
	}
}
