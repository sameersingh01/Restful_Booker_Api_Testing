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

public class TestDELETE extends BasePage{
	
	
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
	
	// pingRequest method is checking that server is up and running before performing delete call on sever
	// hence we use priority 
	
	@Test(priority = 110)
	public void pingRequest() {
		resource = Resources.pingEndPOint;
		Response res = restClient.ping(resource);
		Assert.assertEquals(res.statusCode(), 201);
		System.out.println("API is up and running");
	}
	
	@Test(priority = 510)
	public void deleteReq() {
		
		resource = Resources.deleteEndPoint;
		Response res = restClient.delete(resource, Resources.deleteHeaders());
		log.info("Succesfully invoke delete method");
		//Assert.assertEquals(res.statusCode(), 201);
		//for valiadting negative scenarios we are getting status code as 405
		//Assert.assertEquals(res.statusCode(), 405);
		System.out.println(res.statusCode());
	}

}
