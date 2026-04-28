package com.zosh.payload.dto;

import com.zosh.enums.UserRole;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long id;
    private String email;
    private String password;
    private String fullName;
    private String phone;
    private UserRole role;
    private LocalDateTime lastLogin;
}
