package com.coderscampus.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.coderscampus.response.MarsPhoto;
import com.coderscampus.response.MarsRoverApiResponse;
import com.coderscampusDto.HomeDto;

@Service
public class MarsRoverApiService {
	
	private static final String API_KEY = "bvcxaYLOfadlQBwSqbiyRW4DdDYcuseB8ddpCQg9";
	
	private Map<String, List<String>> validCameras = new HashMap<>();
	
	public MarsRoverApiService() {
		validCameras.put(Opportunity", Arrays.asList("FHAZ","RHAZ","NAVCAM","PANCAM","MINITES"));
		validCameras.put("Curiosity", Arrays.asList("FHAZ","RHAZ","MAST","CHEMCAM","MAHLI","MARDI","MAVCAM"));
		validCameras.put("Spirit", Arrays.asList("FHAZ","RHAZ","NAVCAM","PANCAM","MINITES"));
	}
	
	public MarsRoverApiResponse getRoverData(HomeDto homeDto) {
		RestTemplate rt = new RestTemplate();
		
		List<String> apiUrlEndpoints = getApiUrlEndpoints(homeDto);
		List<MarsPhoto> photos = new ArrayList<>();
		MarsRoverApiResponse response = new MarsRoverApiResponse();
		
		apiUrlEndpoints.stream()
		               .forEach(url -> {		               	
		            	   MarsRoverApiResponse apiResponse = rt.getForObject(url, MarsRoverApiResponse.class);
		            	   photos.addAll(apiResponse.getPhotos());
		               	});
		response.setPhotos(photos);
		
		return response;
	}
	
	public List<String> getApiUrlEndpoints (HomeDto homeDto) {
		List<String> urls = new ArrayList<>();
		
		Method[] methods = homeDto.getClass().getMethods();
		
		for (Method method : methods) {
			if (method.getName().indexOf("getCamera") > -1 && Boolean.TRUE.equals(method.invoke(homeDto))) {
				String cameraName = method.getName().split("getCamera")[1].toUpperCase();
				if (validCameras.get(homeDto.getMarsApiRoverData()).contains(cameraName)) {
					urls.add()
				}
			}
		}
	
		if (Boolean.TRUE.equals(homeDto.getCameraChemcam())  && "curiosity".equalsIgnoreCase(homeDto.getMarsApiRoverData())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+homeDto.getMarsApiRoverData()+"/photos?sol="+homeDto.getMarsSol()+"&api_key=" + API_KEY + "&camera=CHEMCAM");
		}
		if (Boolean.TRUE.equals(homeDto.getCameraFhaz())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+homeDto.getMarsApiRoverData()+"/photos?sol="+homeDto.getMarsSol()+"&api_key=" + API_KEY + "&camera=FHAZ");
		} 
		if (Boolean.TRUE.equals(homeDto.getCameraMahli()) && "curiosity".equalsIgnoreCase(homeDto.getMarsApiRoverData())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+homeDto.getMarsApiRoverData()+"/photos?sol="+homeDto.getMarsSol()+"&api_key=" + API_KEY + "&camera=MAHLI");
		}
		if (Boolean.TRUE.equals(homeDto.getCameraMardi()) && "curiosity".equalsIgnoreCase(homeDto.getMarsApiRoverData())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+homeDto.getMarsApiRoverData()+"/photos?sol="+homeDto.getMarsSol()+"&api_key=" + API_KEY + "&camera=MARDI");
		}
		if (Boolean.TRUE.equals(homeDto.getCameraMast()) && "curiosity".equalsIgnoreCase(homeDto.getMarsApiRoverData())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+homeDto.getMarsApiRoverData()+"/photos?sol="+homeDto.getMarsSol()+"&api_key=" + API_KEY + "&camera=MAST");
		}
		if (Boolean.TRUE.equals(homeDto.getCameraMinites()) && !"curiosity".equalsIgnoreCase(homeDto.getMarsApiRoverData())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+homeDto.getMarsApiRoverData()+"/photos?sol="+homeDto.getMarsSol()+"&api_key=" + API_KEY + "&camera=MINITES");
		}
		if (Boolean.TRUE.equals(homeDto.getCameraNavcam())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+homeDto.getMarsApiRoverData()+"/photos?sol="+homeDto.getMarsSol()+"&api_key=" + API_KEY + "&camera=NAVCAM");
		}
		if (Boolean.TRUE.equals(homeDto.getCameraPancam()) && !"curiosity".equalsIgnoreCase(homeDto.getMarsApiRoverData())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+homeDto.getMarsApiRoverData()+"/photos?sol="+homeDto.getMarsSol()+"&api_key=" + API_KEY + "&camera=PANCAM");
		}
		if (Boolean.TRUE.equals(homeDto.getCameraRhaz())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/"+homeDto.getMarsApiRoverData()+"/photos?sol="+homeDto.getMarsSol()+"&api_key=" + API_KEY + "&camera=RHAZ");
		}
		return urls;
	}

}
