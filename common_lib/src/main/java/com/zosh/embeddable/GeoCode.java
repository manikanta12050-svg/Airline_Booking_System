package com.zosh.embeddable;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeoCode {
    private Double latitude;
    private Double longitude;
}
