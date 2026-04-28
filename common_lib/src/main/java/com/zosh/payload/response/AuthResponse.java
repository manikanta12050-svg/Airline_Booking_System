package com.zosh.payload.response;

import com.zosh.payload.dto.UserDTO;
import lombok.*;
import org.springframework.stereotype.Service;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String JWT;
    private String message;
    private String title;
    private UserDTO user;

    public String setJwt(String jwt) {
        return null;
    }
}
