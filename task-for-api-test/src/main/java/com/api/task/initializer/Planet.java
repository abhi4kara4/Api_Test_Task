package com.api.task.initializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.api.task.constants.Constants;
import com.api.task.utilities.CommonUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Planet {
	
	public String planetName = "name";
	public String rotationPeriod = "rotation_period";
	public String diameter = "diameter";
	public String climate = "climate";
	public String gravity = "gravity";
	public String terrain = "terrain";
	public String surfaceWater = "surface_water";
	public String population = "population";
	public String url = "url";
	public static String planetUrl = "";
	public List<String> planetNames = new ArrayList<String>();
	public int statusCode = 0;
	public long apiResponseTime = 0;
	public List<Response> responseList = new ArrayList<Response>();
	public Response apiResponse = null;
	public Map<String, String> planetNameToRotationPeriod = new HashMap<String, String>();
	public Map<String, String> planetNameToDiameter = new HashMap<String, String>();
	public Map<String, String> planetNameToClimate = new HashMap<String, String>();
	public Map<String, String> planetNameToGravity = new HashMap<String, String>();
	public Map<String, String> planetNameToTerrain = new HashMap<String, String>();
	public Map<String, String> planetNameToSurfaceWater = new HashMap<String, String>();
	public Map<String, String> planetNameToPopulation = new HashMap<String, String>();
	public Map<String, String> planetNameToUrl = new HashMap<String, String>();
	public static Planet instance = null;
	
	
	public String getPlanetName() {
		return planetName;
	}
	public void setPlanetName(String planetName) {
		this.planetName = planetName;
	}
	public String getRotationPeriod() {
		return rotationPeriod;
	}
	public void setRotationPeriod(String rotationPeriod) {
		this.rotationPeriod = rotationPeriod;
	}
	public String getDiameter() {
		return diameter;
	}
	public void setDiameter(String diameter) {
		this.diameter = diameter;
	}
	public String getClimate() {
		return climate;
	}
	public void setClimate(String climate) {
		this.climate = climate;
	}
	public String getGravity() {
		return gravity;
	}
	public void setGravity(String gravity) {
		this.gravity = gravity;
	}
	public String getTerrain() {
		return terrain;
	}
	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}
	public String getSurfaceWater() {
		return surfaceWater;
	}
	public void setSurfaceWater(String surfaceWater) {
		this.surfaceWater = surfaceWater;
	}
	public String getPopulation() {
		return population;
	}
	public void setPopulation(String population) {
		this.population = population;
	}
	public String getPlanetUrl() {
		return planetUrl;
	}
	public void setPlanetUrl(String planetUrl) {
		Planet.planetUrl = planetUrl;
	}
	public Map<String, String> getPlanetNameToDiameter() {
		return planetNameToDiameter;
	}
	public void setPlanetNameToDiameter(Map<String, String> planetNameToDiameter) {
		this.planetNameToDiameter = planetNameToDiameter;
	}
	public Map<String, String> getPlanetNameToClimate() {
		return planetNameToClimate;
	}
	public void setPlanetNameToClimate(Map<String, String> planetNameToClimate) {
		this.planetNameToClimate = planetNameToClimate;
	}
	public Map<String, String> getPlanetNameToGravity() {
		return planetNameToGravity;
	}
	public void setPlanetNameToGravity(Map<String, String> planetNameToGravity) {
		this.planetNameToGravity = planetNameToGravity;
	}
	public Map<String, String> getPlanetNameToTerrain() {
		return planetNameToTerrain;
	}
	public void setPlanetNameToTerrain(Map<String, String> planetNameToTerrain) {
		this.planetNameToTerrain = planetNameToTerrain;
	}
	public Map<String, String> getPlanetNameToSurfaceWater() {
		return planetNameToSurfaceWater;
	}
	public void setPlanetNameToSurfaceWater(Map<String, String> planetNameToSurfaceWater) {
		this.planetNameToSurfaceWater = planetNameToSurfaceWater;
	}
	public Map<String, String> getPlanetNameToPopulation() {
		return planetNameToPopulation;
	}
	public void setPlanetNameToPopulation(Map<String, String> planetNameToPopulation) {
		this.planetNameToPopulation = planetNameToPopulation;
	}
	public List<String> getPlanetNames() {
		return planetNames;
	}
	public void setPlanetNames(List<String> planetNames) {
		this.planetNames = planetNames;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Map<String, String> getPlanetNameToUrl() {
		return planetNameToUrl;
	}
	public void setPlanetNameToUrl(Map<String, String> planetNameToUrl) {
		this.planetNameToUrl = planetNameToUrl;
	}
	public List<Response> getResponseList() {
		return responseList;
	}
	public void setResponseList(List<Response> responseList) {
		this.responseList = responseList;
	}
	public Response getApiResponse() {
		return apiResponse;
	}
	public void setApiResponse(Response apiResponse) {
		this.apiResponse = apiResponse;
	}
	public Map<String, String> getPlanetNameToRotationPeriod() {
		return planetNameToRotationPeriod;
	}
	public void setPlanetNameToRotationPeriod(Map<String, String> planetNameToRotationPeriod) {
		this.planetNameToRotationPeriod = planetNameToRotationPeriod;
	}
	public long getApiResponseTime() {
		return apiResponseTime;
	}
	public void setApiResponseTime(long apiResponseTime) {
		this.apiResponseTime = apiResponseTime;
	}
	
	/**
	 * Define default constructor to set all the instance variables with the 
	 * necessary values 
	 * @param apiUrl
	 * @author Abhijit Karan
	 */
	public Planet(String apiUrl) {
		Response response = null;
		int statusCode = 0;
		long responseTime = 0;
		List<Response> responseList = new ArrayList<Response>();
		List<String> planetNames = new ArrayList<String>();
		
		setPlanetUrl(apiUrl);
		System.out.println("Setting PlanetUrl for API :"+apiUrl);
		
		response = RestAssured.get(apiUrl);
		if(response!=null) {
			System.out.println("Setting Response for API :"+apiUrl);
			setApiResponse(response);
			
			//responseCode
			statusCode = response.getStatusCode();
			System.out.println("Setting Status Code for API :"+apiUrl);
			setStatusCode(statusCode);
			
			//responseTime
			responseTime = response.getTime();
			System.out.println("Setting responseTime for API :"+apiUrl);
			setApiResponseTime(responseTime);
		}
		if(apiUrl.equals(Constants.PLANET_API_URL)) {
			
			responseList = CommonUtil.getAllResponseFromPlanetApi(apiUrl);
			if(responseList!=null) {
				System.out.println("Setting responseList for API :"+apiUrl);
				setResponseList(responseList);
				
				//planetNames
				planetNames = CommonUtil.getAllPlanetNames(responseList);
				System.out.println("Setting planetNamesList for API :"+apiUrl);
				setPlanetNames(planetNames);
				
				//planetNameToRotationPeriodMap
				planetNameToRotationPeriod = CommonUtil.getEntireMapOfPlanetNameToTargetElement(getResponseList(), rotationPeriod);
				System.out.println("Setting planetName To Rotation Period Map for API :"+apiUrl);
				setPlanetNameToRotationPeriod(planetNameToRotationPeriod);
				
				//planetNameToDiameterMap
				planetNameToDiameter = CommonUtil.getEntireMapOfPlanetNameToTargetElement(getResponseList(), diameter);
				System.out.println("Setting planet Name To Diameter Map for API :"+apiUrl);
				setPlanetNameToDiameter(planetNameToDiameter);
				
				//planetNameToClimateMap
				planetNameToClimate = CommonUtil.getEntireMapOfPlanetNameToTargetElement(getResponseList(), climate);
				System.out.println("Setting planet Name To Climate Map for API :"+apiUrl);
				setPlanetNameToClimate(planetNameToClimate);
				
				//planetNameToGravityMap
				planetNameToGravity = CommonUtil.getEntireMapOfPlanetNameToTargetElement(getResponseList(), gravity);
				System.out.println("Setting planet Name To Gravity Map for API :"+apiUrl);
				setPlanetNameToGravity(planetNameToGravity);
				
				//planetNameToTerrainMap
				planetNameToTerrain = CommonUtil.getEntireMapOfPlanetNameToTargetElement(getResponseList(), terrain);
				System.out.println("Setting planet Name To Terrain Map for API :"+apiUrl);
				setPlanetNameToTerrain(planetNameToTerrain);
				
				//planetNameToSurfaceWaterMap
				planetNameToSurfaceWater = CommonUtil.getEntireMapOfPlanetNameToTargetElement(getResponseList(), surfaceWater);
				System.out.println("Setting planet Name To Surface Water Map for API :"+apiUrl);
				setPlanetNameToSurfaceWater(planetNameToSurfaceWater);
				
				//planetNameToPopulation
				planetNameToPopulation = CommonUtil.getEntireMapOfPlanetNameToTargetElement(getResponseList(), population);
				System.out.println("Setting planet Name To population Map for API :"+apiUrl);
				setPlanetNameToPopulation(planetNameToPopulation);
				
				//planetNameToPlanetUrl
				planetNameToUrl = CommonUtil.getEntireMapOfPlanetNameToTargetElement(getResponseList(), url);
				System.out.println("Setting planet Name To planet Url Map for API :"+apiUrl);
				setPlanetNameToUrl(planetNameToUrl);
			}
			
			
		}
		
		
	}
	
	
	/**
	 * 
	 * @param apiUrl
	 * @return
	 */
	public static synchronized Planet getInstance(String apiUrl) {
		if (instance == null) {
			try {
				System.out.println("Creating New Instance for "+apiUrl);
				instance = new Planet(apiUrl);
			} catch (Exception e) {
				throw new ExceptionInInitializerError(e);
			}
		}
		return instance;
	}
	

}
