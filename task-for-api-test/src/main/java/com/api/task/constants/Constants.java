package com.api.task.constants;

public class Constants {
	
	/*
	 * Status Code for Success
	 */
	public static final int SUCCESS_STATUS_CODE = 200;
	/*
	 * Status Code for Not Found
	 */
	public static final int NOT_FOUND_STATUS_CODE = 404;
	/*
	 * API Url for getALl Planets
	 */
	public static final String PLANET_API_URL = "https://swapi.co/api/planets";
	/*
	 * Invalid API Url for getALl Planets
	 */
	public static final String INVALID_PLANET_API_URL = "https://swapi.co/api/planet";
	/*
	 * This defines the location for the excel sheet that contains all test case and test data info.
	 */
	public static final String TEST_CASE_AND_TEST_DATA_LOCATION = "src/test/resources/Test_Case_And_Test_Data/TestCases_And_TestData.xlsx";
	/*
	 * One Second in Millisecond
	 */
	public static final long TWO_SEC_IN_MILLIS = 2000;
	/*
	 * API Url with Invalid ID as 000
	 */
	public static final String API_URL_WITH_INVALID_ID = "https://swapi.co/api/planets/000/";
	/*
	 * Response Body for Not Found
	 */
	public static final String RESPONSE_BODY_NOT_FOUND = "{\"detail\":\"Not found\"}";
	

}
