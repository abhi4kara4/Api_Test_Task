package com.api.task.tests;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.api.task.annotations.TestDetails;
import com.api.task.constants.Constants;
import com.api.task.enums.TestType;
import com.api.task.initializer.Planet;
import com.api.task.testdata.ProvideData;
import com.api.task.utilities.CommonUtil;

import io.restassured.response.Response;

public class PlanetApiTest {

	private static final Logger LOGGER = Logger.getLogger(PlanetApiTest.class);


	// clean install -Dtest=PlanetApiTest#testToVerifyResponseForInvalidApiUrl test


	@BeforeTest
	public void preconditionForGetAllApi() {
		Planet.getInstance(Constants.PLANET_API_URL);
	}



	@Test
	@TestDetails(testUID = "TC-API_TEST-01", testType = { TestType.FUNCTIONAL}, tags = { "API_TEST" }, testDecription = "Verify the Status Code for getAllPlanet API :"+Constants.PLANET_API_URL)
	public void testToVerifyStatusCodeForGetAllPlanetApi() {
		//Initializing the softAssert inorder to continue execution even after assert fail
		SoftAssert softAssert = new SoftAssert();
		try {
			String apiUrl = Constants.PLANET_API_URL;
			String stepNumber = "S1";
			String step1des = "Step "+stepNumber+" to validate Status Code on API URLL : "+apiUrl;
			LOGGER.info(step1des);
			boolean isStatusCodeCorrect = false;

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
	@TestDetails(testUID = "TC-API_TEST-02", testType = { TestType.FUNCTIONAL}, tags = { "API_TEST" }, testDecription = "Verify the API Response for getAllPlanet API :"+Constants.PLANET_API_URL)
	public void testToVerifyApiResponseTimeForGetAllPlanetApi() {
		//Initializing the softAssert inorder to continue execution even after assert fail
		SoftAssert softAssert = new SoftAssert();
		try {
			String apiUrl = Constants.PLANET_API_URL;
			String stepNumber = "S1";
			String step1des = "Step "+stepNumber+" to validate that Response Time is less than 2 Sec. on API URLL : "+apiUrl;
			LOGGER.info(step1des);
			long apiResponseTime = Planet.getInstance(apiUrl).getApiResponseTime();

			boolean isResponseTimeFine = CommonUtil.verifyApiResponseTime(apiResponseTime);

			softAssert.assertTrue(isResponseTimeFine, "Response Time is not below 2 Sec");
		}catch (Exception e) {
			LOGGER.error("Execution Failed due to : "+e.getMessage());
		}finally {
			softAssert.assertAll();
		}
	}





	@Test(dataProvider="planetNames", dataProviderClass = ProvideData.class)
	@TestDetails(testUID = "TC-API_TEST-03", testType = { TestType.FUNCTIONAL}, tags = { "API_TEST" }, testDecription = "Verify all the planets are present for getAllPlanet API :"+Constants.PLANET_API_URL)
	public void testToVerifyPlanetNameInApiResponse(String planetName) {
		//Initializing the softAssert inorder to continue execution even after assert fail
		SoftAssert softAssert = new SoftAssert();
		try {
			boolean isPlanetPresent = false;
			String stepNumber = "S1";
			String step1des = "Step "+stepNumber+" to validate that planet Name : "+planetName+" is present in the response of getAllPlanetApi";
			LOGGER.info(step1des);

			Planet planet = Planet.getInstance(Constants.PLANET_API_URL);

			//get the list of all planet names from the responses
			List<String> allPlanetNames = planet.getPlanetNames();
			if(allPlanetNames.contains(planetName)) {
				isPlanetPresent = true;
			}

			softAssert.assertTrue(isPlanetPresent, planetName+ "is not present in the response of getAllPlanetApi");

		}catch (Exception e) {
			LOGGER.error("Execution Failed due to : "+e.getMessage());
		}finally {
			softAssert.assertAll();
		}

	}



	@Test(dataProvider="planetNameAndRotationPeriod", dataProviderClass = ProvideData.class)
	@TestDetails(testUID = "TC-API_TEST-04", testType = { TestType.FUNCTIONAL}, tags = { "API_TEST" }, testDecription = "Verify that the Rotation Period value is correct for the Specific Planet")
	public void testToVerifyPlanetNameToRotationPeriod(String planetName, String rotationPeriod) {
		//Initializing the softAssert inorder to continue execution even after assert fail
		SoftAssert softAssert = new SoftAssert();
		try {
			boolean isRotationPeriodMappedToCorrectPlanetName = false;
			String stepNumber = "S1";
			String step1des = "Step "+stepNumber+" to validate rotationPeriod is : "+rotationPeriod+" for PlanetName : "+planetName;
			LOGGER.info(step1des);

			String apiUrl = Constants.PLANET_API_URL;
			Map<String, String> map = Planet.getInstance(apiUrl).getPlanetNameToRotationPeriod();

			// Get the rotationPeriod from the Map with the Key as planetName from data provider.
			String rotationPeriodValue = map.get(planetName);

			if(rotationPeriodValue.equals(rotationPeriod)) {
				isRotationPeriodMappedToCorrectPlanetName = true;
			}

			softAssert.assertTrue(isRotationPeriodMappedToCorrectPlanetName, "rotationPeriod from the Map with the Key as :"+planetName+" is :"+rotationPeriodValue+", instead of "+rotationPeriod);
		}catch (Exception e) {
			LOGGER.error("Execution Failed due to : "+e.getMessage());
		}finally {
			softAssert.assertAll();
		}
	}


	@Test(dataProvider="planetNameAndDiameter", dataProviderClass = ProvideData.class)
	@TestDetails(testUID = "TC-API_TEST-05", testType = { TestType.FUNCTIONAL}, tags = { "API_TEST" }, testDecription = "Verify that the Diameter value is correct for the Specific Planet")
	public void testToVerifyPlanetNameToDiameter(String planetName, String diameter) {
		//Initializing the softAssert inorder to continue execution even after assert fail
		SoftAssert softAssert = new SoftAssert();
		try {
			boolean isDiameterMappedToCorrectPlanetName = false;
			String stepNumber = "S1";
			String step1des = "Step "+stepNumber+" to validate diameter is : "+diameter+" for PlanetName : "+planetName;
			LOGGER.info(step1des);

			String apiUrl = Constants.PLANET_API_URL;
			Map<String, String> map = Planet.getInstance(apiUrl).getPlanetNameToDiameter();

			// Get the Diameter from the Map with the Key as planetName from data provider.
			String diameterValue = map.get(planetName);

			if(diameterValue.equals(diameter)) {
				isDiameterMappedToCorrectPlanetName = true;
			}

			softAssert.assertTrue(isDiameterMappedToCorrectPlanetName, "Diameter from the Map with the Key as :"+planetName+" is :"+diameterValue+", instead of "+diameter);
		}catch (Exception e) {
			LOGGER.error("Execution Failed due to : "+e.getMessage());
		}finally {
			softAssert.assertAll();
		}
	}




	@Test(dataProvider="planetNameAndClimate", dataProviderClass = ProvideData.class)
	@TestDetails(testUID = "TC-API_TEST-06", testType = { TestType.FUNCTIONAL}, tags = { "API_TEST" }, testDecription = "Verify that the Climate value is correct for the Specific Planet")
	public void testToVerifyPlanetNameToClimate(String planetName, String climate) {
		//Initializing the softAssert inorder to continue execution even after assert fail
		SoftAssert softAssert = new SoftAssert();
		try {
			boolean isClimateMappedToCorrectPlanetName = false;
			String stepNumber = "S1";
			String step1des = "Step "+stepNumber+" to validate climate is : "+climate+" for PlanetName : "+planetName;
			LOGGER.info(step1des);

			String apiUrl = Constants.PLANET_API_URL;
			Map<String, String> map = Planet.getInstance(apiUrl).getPlanetNameToClimate();

			// Get the Climate from the Map with the Key as planetName from data provider.
			String climateValue = map.get(planetName);

			if(climateValue.equals(climate)) {
				isClimateMappedToCorrectPlanetName = true;
			}

			softAssert.assertTrue(isClimateMappedToCorrectPlanetName, "Climate from the Map with the Key as :"+planetName+" is :"+climateValue+", instead of "+climate);
		}catch (Exception e) {
			LOGGER.error("Execution Failed due to : "+e.getMessage());
		}finally {
			softAssert.assertAll();
		}
	}




	@Test(dataProvider="planetNameAndGravity", dataProviderClass = ProvideData.class)
	@TestDetails(testUID = "TC-API_TEST-07", testType = { TestType.FUNCTIONAL}, tags = { "API_TEST" }, testDecription = "Verify that the Gravity value is correct for the Specific Planet")
	public void testToVerifyPlanetNameToGravity(String planetName, String gravity) {
		//Initializing the softAssert inorder to continue execution even after assert fail
		SoftAssert softAssert = new SoftAssert();
		try{
			boolean isGravityMappedToCorrectPlanetName = false;
			String stepNumber = "S1";
			String step1des = "Step "+stepNumber+" to validate terrain is : "+gravity+" for PlanetName : "+planetName;
			LOGGER.info(step1des);

			String apiUrl = Constants.PLANET_API_URL;
			Map<String, String> map = Planet.getInstance(apiUrl).getPlanetNameToGravity();

			// Get the gravity from the Map with the Key as planetName from data provider.
			String gravityValue = map.get(planetName);

			if(gravityValue.equals(gravity)) {
				isGravityMappedToCorrectPlanetName = true;
			}

			softAssert.assertTrue(isGravityMappedToCorrectPlanetName, "Gravity from the Map with the Key as :"+planetName+" is :"+gravityValue+", instead of "+gravity);
		}catch (Exception e) {
			LOGGER.error("Execution Failed due to : "+e.getMessage());
		}finally {
			softAssert.assertAll();
		}
	}





	@Test(dataProvider="planetNameAndTerrain", dataProviderClass = ProvideData.class)
	@TestDetails(testUID = "TC-API_TEST-08", testType = { TestType.FUNCTIONAL}, tags = { "API_TEST" }, testDecription = "Verify that the Terrain value is correct for the Specific Planet")
	public void testToVerifyPlanetNameToTerrain(String planetName, String terrain) {
		//Initializing the softAssert inorder to continue execution even after assert fail
		SoftAssert softAssert = new SoftAssert();
		try {
			boolean isTerrainMappedToCorrectPlanetName = false;
			String stepNumber = "S1";
			String step1des = "Step "+stepNumber+" to validate terrain is : "+terrain+" for PlanetName : "+planetName;
			LOGGER.info(step1des);

			String apiUrl = Constants.PLANET_API_URL;
			Map<String, String> map = Planet.getInstance(apiUrl).getPlanetNameToTerrain();

			// Get the Terrain from the Map with the Key as planetName from data provider.
			String terrainValue = map.get(planetName);

			if(terrainValue.equals(terrain)) {
				isTerrainMappedToCorrectPlanetName = true;
			}

			softAssert.assertTrue(isTerrainMappedToCorrectPlanetName, "Terrain from the Map with the Key as :"+planetName+" is :"+terrainValue+", instead of "+terrain);
		}catch (Exception e) {
			LOGGER.error("Execution Failed due to : "+e.getMessage());
		}finally {
			softAssert.assertAll();
		}
	}




	@Test(dataProvider="planetNameAndSurfaceWater", dataProviderClass = ProvideData.class)
	@TestDetails(testUID = "TC-API_TEST-09", testType = { TestType.FUNCTIONAL}, tags = { "API_TEST" }, testDecription = "Verify that the Surface Water value is correct for the Specific Planet")
	public void testToVerifyPlanetNameToSurfaceWater(String planetName, String surfaceWater) {
		//Initializing the softAssert inorder to continue execution even after assert fail
		SoftAssert softAssert = new SoftAssert();
		try {
			boolean isSurfaceWaterMappedToCorrectPlanetName = false;
			String stepNumber = "S1";
			String step1des = "Step "+stepNumber+" to validate surfaceWater is : "+surfaceWater+" for PlanetName : "+planetName;
			LOGGER.info(step1des);

			String apiUrl = Constants.PLANET_API_URL;
			Map<String, String> map = Planet.getInstance(apiUrl).getPlanetNameToSurfaceWater();

			// Get the SurfaceWater from the Map with the Key as planetName from data provider.
			String surfaceWaterValue = map.get(planetName);

			if(surfaceWaterValue.equals(surfaceWater)) {
				isSurfaceWaterMappedToCorrectPlanetName = true;
			}

			softAssert.assertTrue(isSurfaceWaterMappedToCorrectPlanetName, "SurfaceWater from the Map with the Key as :"+planetName+" is :"+surfaceWaterValue+", instead of "+surfaceWater);

		}catch (Exception e) {
			LOGGER.error("Execution Failed due to : "+e.getMessage());
		}finally {
			softAssert.assertAll();
		}
	}




	@Test(dataProvider="planetNameAndPopulation", dataProviderClass = ProvideData.class)
	@TestDetails(testUID = "TC-API_TEST-10", testType = { TestType.FUNCTIONAL}, tags = { "API_TEST" }, testDecription = "Verify that the Population value is correct for the Specific Planet")
	public void testToVerifyPlanetNameToPopulation(String planetName, String population) {
		//Initializing the softAssert inorder to continue execution even after assert fail
		SoftAssert softAssert = new SoftAssert();
		try {
			boolean isPopulationMappedToCorrectPlanetName = false;
			String stepNumber = "S1";
			String step1des = "Step "+stepNumber+" to validate population is : "+population+" for PlanetName : "+planetName;
			LOGGER.info(step1des);
			String apiUrl = Constants.PLANET_API_URL;
			Map<String, String> map = Planet.getInstance(apiUrl).getPlanetNameToPopulation();

			// Get the population from the Map with the Key as planetName from data provider.
			String populationValue = map.get(planetName);

			if(populationValue.equals(population)) {
				isPopulationMappedToCorrectPlanetName = true;
			}

			softAssert.assertTrue(isPopulationMappedToCorrectPlanetName, "Population from the Map with the Key as :"+planetName+" is :"+populationValue+", instead of "+population);

		}catch (Exception e) {
			LOGGER.error("Execution Failed due to : "+e.getMessage());
		}finally {
			softAssert.assertAll();
		}
	}




	@Test(dataProvider="planetNameAndUrl", dataProviderClass = ProvideData.class)
	@TestDetails(testUID = "TC-API_TEST-11", testType = { TestType.FUNCTIONAL}, tags = { "API_TEST" }, testDecription = "Verify that the URL is correct for the Specific Planet")
	public void testToVerifyPlanetNameToUrl(String planetName, String url) {
		//Initializing the softAssert inorder to continue execution even after assert fail
		SoftAssert softAssert = new SoftAssert();
		try {
			boolean isUrlMappedToCorrectPlanetName = false;
			String stepNumber = "S1";
			String step1des = "Step "+stepNumber+" to validate url is : "+url+" for PlanetName : "+planetName;
			LOGGER.info(step1des);
			String apiUrl = Constants.PLANET_API_URL;
			Map<String, String> map = Planet.getInstance(apiUrl).getPlanetNameToUrl();

			// Get the url from the Map with the Key as planetName from data provider.
			String urlValue = map.get(planetName);

			if(urlValue.equals(url)) {
				isUrlMappedToCorrectPlanetName = true;
			}

			softAssert.assertTrue(isUrlMappedToCorrectPlanetName, "Url from the Map with the Key as :"+planetName+" is :"+urlValue+", instead of "+url);
		}catch (Exception e) {
			LOGGER.error("Execution Failed due to : "+e.getMessage());
		}finally {
			softAssert.assertAll();
		}
	}



	@Test
	@TestDetails(testUID = "TC-API_TEST-12", testType = { TestType.FUNCTIONAL}, tags = { "API_TEST" }, testDecription = "Verify the Response status code for Invalid Planet URL")
	public void testToVerifyResponseForInvalidApiUrl() {
		//Initializing the softAssert inorder to continue execution even after assert fail
		SoftAssert softAssert = new SoftAssert();
		try {
			String stepNumber = "S1";
			Response response = null;
			String apiUrl = Constants.INVALID_PLANET_API_URL;
			boolean isStatusCodeCorrect = false;
			String step1 = "Step "+stepNumber+" to Verify the Response status code for Invalid Planet URL : "+apiUrl;
			LOGGER.info(step1);
			
			//Setting Instance = null if different apiUrl
			if(!Planet.planetUrl.equals(apiUrl)) {
				Planet.instance = null;
			}else {
				System.out.println("Instance already present for the apiUrl "+apiUrl);
			}
			
			response = Planet.getInstance(apiUrl).getApiResponse();
			int statusCode = response.getStatusCode();
			
			if(statusCode==Constants.NOT_FOUND_STATUS_CODE) {
				isStatusCodeCorrect = true;
			}

			softAssert.assertTrue(isStatusCodeCorrect, "Response status code is not "+Constants.NOT_FOUND_STATUS_CODE+", instead "+statusCode+" for API with Invalid Planet Url : "+apiUrl);
			

		}catch (Exception e) {
			LOGGER.error("Execution Failed due to : "+e.getMessage());
		}finally {
			softAssert.assertAll();
		}

	}

}
