package com.zosh.service;

import com.zosh.payload.request.CityRequest;
import com.zosh.payload.response.CityResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface CityService {

    CityResponse createCity(CityRequest request) throws Exception;
    CityResponse getCityById(Long Id) throws Exception;
    CityResponse updateCity(Long Id,CityRequest request) throws Exception;
    void deleteCity(Long Id) throws Exception;
    Page<CityResponse> getAllCities(Pageable pageable);

    Page<CityResponse> searchCities(String keyword,Pageable pageable);
    Page<CityResponse> getCitiesByCountryCode(String countryCode,Pageable pageable);

    boolean cityExists(String upperCase);

}
