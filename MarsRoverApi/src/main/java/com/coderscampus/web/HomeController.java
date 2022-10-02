package com.coderscampus.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import com.coderscampus.response.MarsRoverApiResponse;
import com.coderscampus.service.MarsRoverApiService;

@Controller
public class HomeController {
	
	@Autowired
	private MarsRoverApiService roverService;
	
	@GetMapping("/")
	public String getHomeView(ModelMap model, @RequestParam(required=false) String marsApiRoverData,
		@RequestParam(required=false) Integer marsSol) {
		// if request param is empty, then set a default value
		if (StringUtils.isEmpty(marsApiRoverData)) {
			marsApiRoverData = "opportunity";
		}
		if (marsSol == null)
			marsSol = 1;
			
		MarsRoverApiResponse roverData = roverService.getRoverData(marsApiRoverData, marsSol);
		model.put("roverData", roverData);
		
		return "index";
		
	}

}
