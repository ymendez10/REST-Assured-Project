package com.automation.rest.assured.test;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.metamodel.query.SelectItem;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.rest.assured.util.Data;
import com.google.common.base.Joiner;

public class ResourcesPOSTTest extends FunctionalTest {
	
	private static File csvFile = new File("src/test/resources/Test.csv");

	@Test
	public void makeSureThatJsonpPlaceholderSiteIsUp() {
		given().when().get().then().assertThat().statusCode(200);
	}

	@Test(dependsOnMethods = "makeSureThatJsonpPlaceholderSiteIsUp", enabled=false)
	public void makeSureThatCommentIsPosted() {
		
		Map<String,String> comment = new HashMap<>();
		comment.put("userId", "10");
		comment.put("id", "1003");
		comment.put("title", "My First Post");
        comment.put("body", "Sample test");
		
        given()
        .contentType("application/json")
        .body(comment)
        .when().post("/posts")
        .then().assertThat().statusCode(201);
	}
	
	@Test(dependsOnMethods = "makeSureThatJsonpPlaceholderSiteIsUp", dataProvider = "csvDataProvider", enabled=true)
	public void makeSureThatCommentsArePostedUsingDataProvider(SelectItem[] cols, Object[] data) {
		
		String row = Joiner.on("|").join( data );

		  given()
	        .contentType("application/json")
	        .body(Data.getCommentMapFromString(row, "|"))
	        .when().post("/posts")
	        .body().prettyPrint();
	        //.then().assertThat().statusCode(201);
	}
	
	@DataProvider( name = "csvDataProvider" ) 
	public Object[][] gatherCsvData(){
		return Data.getCsvData( csvFile );
	}
}
