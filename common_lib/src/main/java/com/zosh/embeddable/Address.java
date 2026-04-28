package com.zosh.embeddable;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    private String street;
    private String postalCode;
}
