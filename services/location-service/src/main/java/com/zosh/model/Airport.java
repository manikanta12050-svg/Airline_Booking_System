package com.zosh.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false, length=3)
    private String iataCode;

    @Column(nullable = false)
    private String name;

    private String address;

    private String geoCode; //latitude and longitude

    private String timeZone;

    @ManyToOne
    private City city;

    @JsonIgnore
    @Transient
    public String getDetailedName() {
        if(city!=null && city.getCountryCode()!=null) {
            return name.toUpperCase()+ "/" + city.getCityCode();
        }
        return name.toUpperCase();
    }
}