package Activities_REST;
import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import org.testng.annotations.Test;

public class RestAPIProject {
	// Declare request specification
		RequestSpecification requestSpec;
		// Declare response specification
		ResponseSpecification responseSpec;
		// Global properties
		String sshKey;
		int sshKeyId;
		@BeforeClass
		public void setUp() {
			// Create request specification
			requestSpec = new RequestSpecBuilder()
					// Set content type
					.setContentType(ContentType.JSON)
					.addHeader("Authorization", "token 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")
					// Set base URL
					.setBaseUri("https://api.github.com")
					// Build request specification
					.build();
			sshKey = "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABgQC0TuW86kcT8uMmg7XbV0ffpW/Zi1sW3hDpWxBBoz6POz2FRZHiiOoFSxdLi0SPYsAVPEjsciWJJUa8imI7Ffqb8MmZVOx/fDhNcX81USARJJY20ErN28L3jOlMNS2LNeJbjgT90p6d/gZWlo+K2ioRZqSM9TVnBckywnU3Mz1Toup85CKx1CVbkdCNkizMr52XokU5APpI2PYJCq0rzEip4U0clhYmH8ABwBk281qOuuInrb/1BqUAw3/oOTQI4OdXooSDbNVw8QOjaRaVDMNClnu6dgm46WaFPgUxG71Si7QgHNKDY1pc8pPq6aOUCaINuW798Vnh5In9OHTzO/YA7QTABErVGlxOXGa+6iyuY/b4CJqczlSAlKSJ4V7aT38Y7IIK2ngd9+S8jzH7VJY5q+xOzq+VKDutgUUrYYlPqoVoJ9XBfctIh7CuBI81/f54Op+7B0jSwqqKYgIDhLWAJpHE/xnxED4RLChUcBuOOhgtW8isLlYVuC/SqgxtAJk=" ;
		}
		@Test(priority = 1)
		// Test case using a DataProvider
		public void addKeys() {
			String reqBody = "{\"title\": \"TestKey\", \"key\": \"" + sshKey + "\" }";
			Response response = given().spec(requestSpec) // Use requestSpec
					.body(reqBody) // Send request body
					.when().post("/user/keys"); // Send POST request
			String resBody = response.getBody().asPrettyString();
			System.out.println(resBody);
			sshKeyId = response.then().extract().path("id");
			// Assertions
			response.then().statusCode(201);
		}
		@Test(priority = 2)
		// Test case using a DataProvider
		public void getKeys() {
			Response response = given().spec(requestSpec) // Use requestSpec
					.when().get("/user/keys"); // Send GET Request
			String resBody = response.getBody().asPrettyString();
			System.out.println(resBody);
			// Assertions
			response.then().statusCode(200);
		}
		@Test(priority = 3)
		// Test case using a DataProvider
		public void deleteKeys() {
			Response response = given().spec(requestSpec) // Use requestSpec
					.pathParam("keyId", sshKeyId).when().delete("/user/keys/{keyId}"); // Send GET Request
			String resBody = response.getBody().asPrettyString();
			System.out.println(resBody);
			// Assertions
			response.then().statusCode(204);
		}
	}
