package com.zosh.controller;

import com.zosh.payload.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public ApiResponse homeController() {
        ApiResponse apiResponse = new ApiResponse("hey everyone I am User service of Airline System",true);
        return apiResponse; //Deleted response body
    }
}
