package com.zosh.controller;

import com.zosh.payload.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public ApiResponse HomeController() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Hello Welcome");
        return apiResponse;
    }
}
