package com.api.task.tests;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.api.task.annotations.TestDetails;
import com.api.task.constants.Constants;
import com.api.task.enums.TestType;
import com.api.task.initializer.Planet;
import com.api.task.testdata.ProvideData;
import com.api.task.utilities.CommonUtil;

import io.restassured.response.Response;

public class PlanetApiByIdTest {

	private static final Logger LOGGER = Logger.getLogger(PlanetApiByIdTest.class);
	

	// clean install -Dtest=PlanetApiByIdTest,PlanetApiTest test

	@Test(dataProvider="apiUrls", dataProviderClass = ProvideData.class)
	@TestDetails(testUID = "TC-API_TEST-13", testType = { TestType.FUNCTIONAL}, tags = { "API_TEST" }, testDecription = "Test to valiadate the Planet API url by ID")
	public void testToVerifyApiResponseTimeForUrlById(String apiUrl) {
		//Initializing the softAssert inorder to continue execution even after assert fail
		SoftAssert softAssert = new SoftAssert();
		try {
			String stepNumber = "S1";
			//Setting Instance = null if different apiUrl
			if(!Planet.planetUrl.equals(apiUrl)) {
				Planet.instance = null;
			}else {
				System.out.println("Instance already present for the apiUrl "+apiUrl);
			}

			

			String step1des = "Step "+stepNumber+" to validate Response Time is less than 2 Sec. on API URLL : "+apiUrl;
			System.out.println(step1des);
			long apiResponseTime = Planet.getInstance(apiUrl).getApiResponseTime();
			boolean isResponseTimeFine = CommonUtil.verifyApiResponseTime(apiResponseTime);

			softAssert.assertTrue(isResponseTimeFine, "Response Time is not below 2 Sec, instead :"+apiResponseTime+" for API : "+apiUrl);



			stepNumber = "S2";
			String step2des = "Step "+stepNumber+" to validate Status Code on API URL : "+apiUrl;
			boolean isStatusCodeCorrect = false;
			System.out.println(step2des);

			int actualStatusCode = Planet.getInstance(apiUrl).getStatusCode();
			
			if(actualStatusCode==Constants.SUCCESS_STATUS_CODE) {
				isStatusCodeCorrect = true;
			}

			softAssert.assertTrue(isStatusCodeCorrect, "Response status code is not "+Constants.SUCCESS_STATUS_CODE+", instead "+actualStatusCode+" for API : "+apiUrl);
			
		}catch (Exception e) {
			LOGGER.error("Execution Failed due to : "+e.getMessage());
		}finally {
			softAssert.assertAll();
		}
	}




	@Test
	@TestDetails(testUID = "TC-API_TEST-14", testType = { TestType.FUNCTIONAL}, tags = { "API_TEST" }, testDecription = "Test to valiadate the API when planetId is Invalid")
	public void testToVerifyResponseForInvalidPlanetId() {
		//Initializing the softAssert inorder to continue execution even after assert fail
		SoftAssert softAssert = new SoftAssert();
		try {
			String stepNumber = "S1";
			Response response = null;
			String apiUrl = Constants.API_URL_WITH_INVALID_ID;
			boolean isResponseBodyCorrect = false;
			String responseBody = "";
			String step1 = "Step "+stepNumber+" to Validate if Response Body contains \"detail\": \"Not found\" when Planet Id is invalid for API";
			System.out.println(step1);

			//Setting Instance = null if different apiUrl
			if(!Planet.planetUrl.equals(apiUrl)) {
				Planet.instance = null;
			}else {
				System.out.println("Instance already present for the apiUrl "+apiUrl);
			}

			response = Planet.getInstance(apiUrl).getApiResponse();
			responseBody = response.getBody().asString();

			if(responseBody.equals(Constants.RESPONSE_BODY_NOT_FOUND)) {
				isResponseBodyCorrect = true;
			}

			softAssert.assertTrue(isResponseBodyCorrect, "Response Body does not contain "+Constants.RESPONSE_BODY_NOT_FOUND+", instead :"+responseBody+" for Invalid Planet Id");


			stepNumber = "S2";
			String step2 = "Step "+stepNumber+" to Validate if Response Status Code is "+Constants.NOT_FOUND_STATUS_CODE+" when Planet Id is invalid for API";
			int statusCode = response.getStatusCode();
			boolean isStatusCodeCorrect = false;
			System.out.println(step2);

			if(statusCode==Constants.NOT_FOUND_STATUS_CODE) {
				isStatusCodeCorrect = true;
			}

			softAssert.assertTrue(isStatusCodeCorrect, "Response status code is not "+Constants.NOT_FOUND_STATUS_CODE+", instead "+statusCode+" for API with Invalid Planet Id");
			

		}catch (Exception e) {
			LOGGER.error("Execution Failed due to : "+e.getMessage());
		}finally {
			softAssert.assertAll();
		}

	}
	
}
