package com.api.task.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.api.task.constants.Constants;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * This class will define all the commonly used methods across the project
 * @author Abhijit Karan
 *
 */
public class CommonUtil {



	private static final Logger LOGGER = Logger.getLogger(CommonUtil.class);



	public static Response getApiResponse(String apiUrl) {
		Response response = null;

		response = RestAssured.get(apiUrl);
		LOGGER.info("API URL is "+apiUrl);
		if(response!=null) {
			LOGGER.info("Status Code : "+response.getStatusCode());
		}else {
			LOGGER.error("Response is Null");
		}

		return response;
	}
	
	/**
	 * This method return the integer status code for the API URL supplied.
	 * @param apiUrl
	 * @return
	 */
	public static int getApiResponseStatusCode(String apiUrl) {
		int statusCode = 0;
		Response response = null;

		response = RestAssured.get(apiUrl);
		LOGGER.info("API URL is "+apiUrl);
		if(response!=null) {
			LOGGER.info("Status Code : "+response.getStatusCode());
		}else {
			LOGGER.error("Response is Null");
		}
		statusCode = response.getStatusCode();
		
		return statusCode;
	}
	
	
	/**
	 * This method gets the response time from the rest assured Response and verifies if it is fine or not.
	 * @param apiUrl
	 * @return 
	 */
	public static boolean verifyApiResponseTime(long responseTime) {
		
		boolean isResponseTimeFine = false;
		// Logic to verify the responseTime is less than 2 sec.
		if(responseTime<Constants.TWO_SEC_IN_MILLIS) {
			isResponseTimeFine = true;
		}else {
			System.out.println("Response time calculated as : "+responseTime);
		}
		
		return isResponseTimeFine;
	}
	
	
	


	/**
	 * This method will retrive all the planet Names from a single Response
	 * @param response
	 * @return ArrayList<String>
	 * @author Abhijit Karan
	 */
	public static ArrayList<String> getPlanetNamesFromResponseBody(Response response) {

		ArrayList<String> listOfPlanetNames = new ArrayList<String>();

		// get the json Array for results
		JsonArray jsonarray = getResultJsonArrayFromResponse(response);

		// Iterate through the array of results to prepare the list
		for(int i=0; i<jsonarray.size(); i++){
			JsonObject obj = jsonarray.get(i).getAsJsonObject();

			String name = obj.get("name").getAsString();
			LOGGER.debug("Planet Name : "+name);
			listOfPlanetNames.add(name);
		}

		return listOfPlanetNames;
	}




	/**
	 * This returns the entire list of planet Names from the API Response List
	 * @param responseList
	 * @return
	 */
	public static List<String> getAllPlanetNames(List<Response> list) {

		//List<Response> list = CommonUtil.getAllResponseFromPlanetApi();
		List<String> planetNamesList = new ArrayList<String>();

		for(int i=0;i<list.size();i++) {
			List<String> sublist = new ArrayList<String>();
			sublist = CommonUtil.getPlanetNamesFromResponseBody(list.get(i));
			for(int j=0;j<sublist.size();j++) {
				LOGGER.debug(sublist.get(j));
				planetNamesList.add(sublist.get(j));
			}
		}
		return planetNamesList;
	}

	/**
	 * Get the Result Json Array to build the list of elements inside it.
	 * @param response
	 * @return JsonArray
	 * @author Abhijit Karan
	 */
	public static JsonArray getResultJsonArrayFromResponse(Response response) {
		String jsonString = response.getBody().asString();
		JsonObject jObject = new JsonParser().parse(jsonString).getAsJsonObject();

		String resultString = jObject.get("results").toString();
		JsonArray jsonarray = new JsonParser().parse(resultString).getAsJsonArray();
		return jsonarray;
	}


	/**
	 * This method gives the map of Name to target Element.
	 * @param targetElement
	 * @return Map<String,String>
	 * @author Abhijit Karan
	 */
	public static Map<String,String> getEntireMapOfPlanetNameToTargetElement(List<Response> list, String targetElement){
		// Initialise the map.
		Map<String, String> planetNameToTargetElementMap = new TreeMap<String, String>();
		String targetElements = "";
		//List<Response> list = CommonUtil.getAllResponseFromPlanetApi();

		for(int i=0;i<list.size();i++) {

			// Get the Result Array List
			JsonArray jsonarray = getResultJsonArrayFromResponse(list.get(i));

			// Iterate through the array of results to prepare the map
			for(int j=0; j<jsonarray.size(); j++){
				JsonObject obj = jsonarray.get(j).getAsJsonObject();

				String name = obj.get("name").getAsString();
				if(targetElement.equals("residents")) {

					JsonArray jsonarray1 = obj.get(targetElement).getAsJsonArray();

					for(int k=0; k<jsonarray1.size();k++) {
						targetElements = targetElements + "," + jsonarray1.get(k);
					}

				}else {
					targetElements = obj.get(targetElement).getAsString();
				}
				planetNameToTargetElementMap.put(name, targetElements.trim());
			}
		}
		return planetNameToTargetElementMap;
	}

	/**
	 * This method returns the list of all the restAssured responses.
	 * @return List<Response>
	 * @author Abhijit Karan
	 */
	public static List<Response> getAllResponseFromPlanetApi(String initialApiUrl){

		List<Response> responseList = new ArrayList<Response>();
		JsonObject jObject = null;
		Response response = null;
		String jsonString = "";
		String apiUrl = "";
		int count = 0;
		do {
			if(count == 0) {
				apiUrl = initialApiUrl;
				response = getApiResponse(apiUrl);
				jsonString = response.getBody().asString();
				jObject = new JsonParser().parse(jsonString).getAsJsonObject();
			}else {
				response = getApiResponse(apiUrl);
				jsonString = response.getBody().asString();
				jObject = new JsonParser().parse(jsonString).getAsJsonObject();
			}
			if(response!=null ) {
				LOGGER.info("Adding Response Object in to the List for URL "+apiUrl);
				responseList.add(response);
			}
			try {
				apiUrl = jObject.get("next").getAsString();
			}catch (Exception e) {
				LOGGER.error(e.getMessage());
				return responseList;
			}
			count++;
		}
		while(!apiUrl.equals("null"));

		return responseList;

	}


}