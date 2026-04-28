package com.zosh.payload.request;

import jakarta.validation.Valid;
import lombok.*;

import java.time.ZoneId;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AirportRequest {

    private String iataCode;
    private String name;
    private ZoneId timeZone;

    @Valid
    private String address;
    private Long cityId;

    @Valid
    private String geoCode;

}