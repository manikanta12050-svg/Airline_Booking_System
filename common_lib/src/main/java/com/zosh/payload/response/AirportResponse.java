package com.zosh.payload.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AirportResponse {

    private Long id;
    private String iataCode;
    private String name;
    private String detailedName;
    private String timeZone;
    private String address;
    private CityResponse city;
    private String geoCode;
}