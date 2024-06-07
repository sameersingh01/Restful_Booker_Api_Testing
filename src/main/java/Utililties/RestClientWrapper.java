package Utililties;

import java.util.HashMap;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClientWrapper {

	public String resource;
    public String baseUrl;
    private RequestSpecification request;
    private Response restResponse;
    
 //   In RestAssured, a wrapper class is a class that provides a convenient way to make HTTP requests.
//    RestAssured wrapper classes typically provide methods for setting the request method, headers, body, 
//    and other parameters.
//    They also provide methods for extracting the response status code, headers, and body.
    
//    This wrapper class provides methods for making GET, POST, PUT, and DELETE requests.
//    To use the wrapper class, you would first create an instance of it.
//    Then, you would call the appropriate method for the type of request you want to make.
//    For example, to make a GET request, you would call the get() method.
    
//    The wrapper class would then make the request and return the response. 
//    You could then use the response to extract the status code, headers, and body
    
    
//    RestAssured wrapper classes can be very useful for simplifying the process of making HTTP requests.
//    They can also help to make your code more readable and maintainable.
    
    public RestClientWrapper(String baseUrl, RequestSpecification request) {
        this.request =request;
        this.request.baseUri(baseUrl);
    }
    
    public Response ping(String resource) {
    	restResponse = request.when().get(resource);
    	return restResponse;
    	
    }

    
    public Response get(String resource) throws Exception {
        restResponse = request.when().get(resource );
        return restResponse;
    }
    
    public Response post(String resource,String bodyString) throws Exception {
        restResponse = request.header(Resources.contentType,Resources.contentTypeValue).when().body(bodyString).post(resource );
        return restResponse;
    }
    
    public Response put(String resource, String bodyString, HashMap<String,String> headers) {
    	restResponse = request.headers(headers).when().body(bodyString).put(resource);
    	return restResponse;
    }
    
    public Response delete(String resource,HashMap<String,String> headers) {
    	restResponse = request.headers(headers).when().delete(resource);
    	return restResponse;
    }
}
