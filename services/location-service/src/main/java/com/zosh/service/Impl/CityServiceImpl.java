package com.zosh.service.Impl;

import com.zosh.mapper.CityMapper;
import com.zosh.model.City;
import com.zosh.payload.request.CityRequest;
import com.zosh.payload.response.CityResponse;
import com.zosh.repository.CityRepository;
import com.zosh.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    public CityResponse createCity(CityRequest request) throws Exception {
        if(cityRepository.existsByCityCode(request.getCityCode())) {
            throw new Exception(("City already exists"));
        }
        City city = CityMapper.toEntity(request);
        City result = cityRepository.save(city);
        return CityMapper.toResponse(result);
    }

    @Override
    public CityResponse getCityById(Long id) throws Exception {
        City city = cityRepository.findById(id).orElseThrow(
                () -> new Exception("City not exit with given id")
        );

        return CityMapper.toResponse(city);
    }

    @Override
    public CityResponse updateCity(Long id, CityRequest request) throws Exception {
        City city = cityRepository.findById(id).orElseThrow(
                () -> new  Exception("city not exist with id")
        );
        if(cityRepository.existsByCityCode(request.getCityCode())) {
            throw new Exception(("City with given code already exist"));
        }
        City updatedCity = cityRepository.save(CityMapper.updateEntity(city,request));
        return CityMapper.toResponse((updatedCity));
    }

    @Override
    public void deleteCity(Long id) throws Exception {
        City city = cityRepository.findById(id).orElseThrow(
                () -> new Exception("city not exit with given id")
        );
        cityRepository.delete(city);
    }

    @Override
    public Page<CityResponse> getAllCities(Pageable pageable) {
        return cityRepository.findAll(pageable).map(CityMapper ::toResponse);

    }

    @Override
    public Page<CityResponse> searchCities(String keyword, Pageable pageable) {

        return cityRepository.searchByKeyword(keyword,pageable).map(CityMapper :: toResponse);
    }

    @Override
    public Page<CityResponse> getCitiesByCountryCode(String countryCode, Pageable pageable) {

        return cityRepository.findByCountryCodeIgnoreCase(countryCode,pageable).map(CityMapper::toResponse);
    }
    public boolean cityExists(String cityCode) {

        return cityRepository.existsByCityCode(cityCode);
    }

}
