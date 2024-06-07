package CRUD_Operations;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Utililties.ReadingPropertiesFile;
import Utililties.Resources;
import Utililties.RestClientWrapper;
import baseClass.BasePage;
import io.restassured.response.Response;

public class TestPOST extends BasePage {
	
	
	public String resource;
	private static RestClientWrapper restClient;
	public static String baseUrl;
	
	@BeforeClass
	public void setupTest() {
		baseUrl = ReadingPropertiesFile.prop.getProperty("baseUrl");
	}
	
	@BeforeMethod
	public void setUPRequest() {
		restClient = new RestClientWrapper(baseUrl, httpRequest);

	}
	
	// pingRequest method is checking that server is up and running before performing post call on sever
	// hence we use priority 
	
	@Test(priority = 201)
	public void pingRequest() {
		resource = Resources.pingEndPOint;
		Response res = restClient.ping(resource);
		Assert.assertEquals(res.statusCode(), 201);
		System.out.println("API is up and running");
	}
	
	@Test(priority = 501)
	public void postReq() throws Exception {
		
		resource = Resources.postEndPoint;
		

		
		Response res = restClient.post(resource, Resources.bodyStringPost());
		String firstName = res.jsonPath().get("booking.firstname");
		
		//validating the status code of post response
		Assert.assertEquals(res.statusCode(), 200);
		Assert.assertEquals(firstName, "Sameer");
		log.info("Succesfully invoke post method");
		
		res.prettyPeek();
		
		System.out.println(res.statusCode());
		
	}

}
