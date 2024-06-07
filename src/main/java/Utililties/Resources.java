package Utililties;

import java.util.HashMap;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Resources {

	// This class is used as a global resources where we can store all the reusable
	// endpoints as well as payloads.

	// These are the endpoints of different method define here statically
	public static String tokenEndPoint = "/auth";
	public static String getEndPoint = "/booking/1";
	public static String postEndPoint = "/booking";
	public static String putEndPoint = "/booking/1";
	public static String deleteEndPoint = "/booking/1";
	public static String pingEndPOint = "/ping";
	public static String contentType = "Content-Type";
	public static String contentTypeValue = "application/json";
	public static String auth = "Basic YWRtaW46cGFzc3dvcmQxMjM=";
	public static String auth_url = "https://restful-booker.herokuapp.com/auth";

	// These are the headers which we will pass during post and put request
	public static HashMap<String, String> putHeaders() {

		Response res = RestAssured.given().header("Content-Type", "application/json")
				.body("{\r\n" + "    \"username\" : \"admin\",\r\n" + "    \"password\" : \"password123\"\r\n" + "}")
				.post(auth_url);

		String access_token = res.jsonPath().get("token");
		access_token = "token=" + access_token;
		System.out.println("Token is :" + access_token);

		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json");
		headers.put("Cookie", access_token);
		headers.put("Authorization", auth);

		return headers;
	}

	// These are headers which we will pass during delete request
	public static HashMap<String, String> deleteHeaders() {

		Response res = RestAssured.given().header("Content-Type", "application/json")
				.body("{\r\n" + "    \"username\" : \"admin\",\r\n" + "    \"password\" : \"password123\"\r\n" + "}")
				.post(auth_url);

		String access_token = res.jsonPath().get("token");
		access_token = "token=" + access_token;
		System.out.println("Token is :" + access_token);

		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("Cookie", access_token);
		headers.put("Authorization", auth);

		return headers;

	}

	// This is a body which we will pass during post and put request
	public static String bodyStringPost() {
		String bodyString = "{\r\n" + "    \"firstname\" : \"Sameer\",\r\n" + "    \"lastname\" : \"Singh\",\r\n"
				+ "    \"totalprice\" : 111,\r\n" + "    \"depositpaid\" : true,\r\n" + "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2018-01-01\",\r\n" + "        \"checkout\" : \"2019-01-01\"\r\n"
				+ "    },\r\n" + "    \"additionalneeds\" : \"Breakfast\"\r\n" + "}";

		return bodyString;
	}

}
