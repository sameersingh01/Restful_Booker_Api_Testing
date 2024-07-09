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

public class TestPUT extends BasePage {
	
	
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
	
	// pingRequest method is checking that server is up and running before performing put call on sever
	// hence we use priority 
	
	@Test(priority = 201)
	public void pingRequest() {
		resource = Resources.pingEndPOint;
		Response res = restClient.ping(resource);
		Assert.assertEquals(res.statusCode(), 201);
		System.out.println("API is up and running");
	}
	
	
	@Test(priority = 501)
	public void putReq() {
		
		resource = Resources.putEndPoint;
		
		Response res = restClient.put(resource, Resources.bodyStringPost(), Resources.putHeaders());
		
		//validating status code of put response
		Assert.assertEquals(res.statusCode(), 405);
		log.info("Succesfully invoke put method");
		
		System.out.println(res.statusCode());
	}

}
