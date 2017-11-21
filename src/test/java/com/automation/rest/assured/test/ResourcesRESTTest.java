package com.automation.rest.assured.test;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ResourcesRESTTest extends FunctionalTest {

	@Test
	public void makeSureThatJsonpPlaceholderSiteIsUp() {
		given().when().get().then().assertThat().statusCode(200);
	}

	@Test(dependsOnMethods = "makeSureThatJsonpPlaceholderSiteIsUp")
	public void makeSureThatCommentsAreAvailable() {
		given().when().get("/comments").then().assertThat().statusCode(200);
	}

	@Test(dependsOnMethods = "makeSureThatCommentsAreAvailable")
	public void makeSureNumberOfComments() {
		given()
		.when().get("/comments")
		.then().assertThat().body("size()", is(500));
	}

	@Test(dependsOnMethods = "makeSureThatCommentsAreAvailable")
	public void makeSureThatCommentByIdIsRetrieved() {
		given().pathParam("id", "10")
		.when().get("/comments/{id}")
		.then().assertThat().body("id", equalTo(10));
	}
	
	@Test(dependsOnMethods = "makeSureThatCommentsAreAvailable")
	public void makeSureThatCommentRetrievedByIdContainsAtSign() {
		given().pathParam("id", "100")
		.when().get("/comments/{id}")
		.then().assertThat().body("email", containsString("@"));
	}
}
