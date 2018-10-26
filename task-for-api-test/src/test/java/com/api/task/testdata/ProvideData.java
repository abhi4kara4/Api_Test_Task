package com.api.task.testdata;

import org.testng.annotations.DataProvider;

import com.api.task.constants.Constants;
import com.api.task.utilities.ExcelReader;

/**
 * This is a data provider class which will contain methods to provide data.
 * @author Abhijit Karan
 *
 */
public class ProvideData {
	
	


	/**
	 * This method returns the data for the test cases
	 * @return
	 * @throws Exception
	 */
	public Object[][] getTestCaseDetails() throws Exception{

		Object[][] testObjArray = ExcelReader.getTableArray(Constants.TEST_CASE_AND_TEST_DATA_LOCATION,"Test Cases",7);

		return testObjArray;

	}
	
	@DataProvider
	public Object[][] apiUrls() throws Exception{

		Object[][] testObjArray = ExcelReader.getTableArray(Constants.TEST_CASE_AND_TEST_DATA_LOCATION,"API_URL",1);

		return testObjArray;

	}
	
	
	
	@DataProvider
	public Object[][] planetNames() throws Exception{

		Object[][] testObjArray = ExcelReader.getTableArray(Constants.TEST_CASE_AND_TEST_DATA_LOCATION,"Planet_Names",1);

		return testObjArray;

	}
	
	
	@DataProvider
	public Object[][] planetNameAndRotationPeriod() throws Exception{

		Object[][] testObjArray = ExcelReader.getTableArray(Constants.TEST_CASE_AND_TEST_DATA_LOCATION,"PN_RP_MAP",2);

		return testObjArray;

	} 


	@DataProvider
	public Object[][] planetNameAndDiameter() throws Exception{

		Object[][] testObjArray = ExcelReader.getTableArray(Constants.TEST_CASE_AND_TEST_DATA_LOCATION,"PN_D_MAP",2);

		return testObjArray;

	} 
	
	@DataProvider
	public Object[][] planetNameAndClimate() throws Exception{

		Object[][] testObjArray = ExcelReader.getTableArray(Constants.TEST_CASE_AND_TEST_DATA_LOCATION,"PN_C_MAP",2);

		return testObjArray;

	} 
	
	
	@DataProvider
	public Object[][] planetNameAndGravity() throws Exception{

		Object[][] testObjArray = ExcelReader.getTableArray(Constants.TEST_CASE_AND_TEST_DATA_LOCATION,"PN_G_MAP",2);

		return testObjArray;

	}
	
	
	@DataProvider
	public Object[][] planetNameAndTerrain() throws Exception{

		Object[][] testObjArray = ExcelReader.getTableArray(Constants.TEST_CASE_AND_TEST_DATA_LOCATION,"PN_T_MAP",2);

		return testObjArray;

	}
	
	
	@DataProvider
	public Object[][] planetNameAndSurfaceWater() throws Exception{

		Object[][] testObjArray = ExcelReader.getTableArray(Constants.TEST_CASE_AND_TEST_DATA_LOCATION,"PN_SW_MAP",2);

		return testObjArray;

	}
	
	
	@DataProvider
	public Object[][] planetNameAndPopulation() throws Exception{

		Object[][] testObjArray = ExcelReader.getTableArray(Constants.TEST_CASE_AND_TEST_DATA_LOCATION,"PN_P_MAP",2);

		return testObjArray;

	}
	
	
	@DataProvider
	public Object[][] planetNameAndUrl() throws Exception{

		Object[][] testObjArray = ExcelReader.getTableArray(Constants.TEST_CASE_AND_TEST_DATA_LOCATION,"PN_URL_MAP",2);

		return testObjArray;

	}
}
