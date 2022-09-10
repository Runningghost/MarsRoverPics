package com.coderscampus.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.coderscampus.response.MarsRoverApiResponse;

@Service
public class MarsRoverApiService {
	
	private static final String API_KEY = "bvcxaYLOfadlQBwSqbiyRW4DdDYcuseB8ddpCQg9";
	
	public MarsRoverApiResponse getRoverData(String roverType) {
		RestTemplate rt = new RestTemplate();
		
		ResponseEntity<MarsRoverApiResponse> response = rt.getForEntity("https://api.nasa.gov/mars-photos/api/v1/rovers/"+roverType+"/photos?sol=2&api_key="+ API_KEY, MarsRoverApiResponse.class);
		return response.getBody();
	}

}
