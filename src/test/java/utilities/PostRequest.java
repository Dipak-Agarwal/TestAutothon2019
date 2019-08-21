package utilities;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PostRequest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File file = new File("/ExternalFiles/post.json");
		
		RestAssured.given().log().ifValidationFails().contentType(ContentType.M).accept(ContentType.JSON)
		.body(file)
		.post("http://localhost:8080/laptop-bag/webapi/api/add")
		.then().assertThat().statusCode(200);
		
	}

}
