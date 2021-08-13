package stepDefinition;

import org.testng.annotations.Test;

import utilities.MasterClass;

import static io.restassured.RestAssured.*;

public class TestPublicAPI extends MasterClass{
	
	@Test
	public void verifyStatusCode()
	{
		basePath = "/users/1";
		verifyStatusCode200(given().get().statusCode());
	}
	
	@Test
	public void verifyContent()
	{
		basePath = "/users/1";
//		Utility.verifyJsonDataContains(given().get().body().asString(), "name", "Leanne");
		verifyJsonData(given().get().body().asString(), "username", "Bret");
	}

}
