package com.zosh.service.Impl;

import com.zosh.config.JwtProvider;
import com.zosh.enums.UserRole;
import com.zosh.mapper.UserMapper;
import com.zosh.model.User;
import com.zosh.payload.dto.UserDTO;
import com.zosh.payload.response.AuthResponse;
import com.zosh.repository.UserRepository;
import com.zosh.service.AuthService;
import com.zosh.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserDetailService customUsersDetailService;

    /*
        1.Check if email already exists
        2.Encode Password using Bcrypt
        3.save user in database
        4.Generate JWT token
        5.Return token and user information
     */

    @Override
    public AuthResponse signup(UserDTO req) throws Exception {
        User existingUser = userRepository.findByEmail(req.getEmail());
        if(existingUser!=null) {
            throw new Exception("Email already registered");
        }
        if(req.getRole() == UserRole.ROLE_SYSTEM_ADMIN) {
            throw new Exception("You cannot sign up system admin");
        }
        User newUser = User.builder()
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .phone(req.getPhone())
                .role(req.getRole())
                .fullName(req.getFullName())
                .lastLogin(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        User savedUser=userRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                savedUser.getEmail(),savedUser.getPassword()
        );
        String jwt = jwtProvider.generateToken(
                authentication, savedUser.getId()
        );
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setUser(UserMapper.toDTO(savedUser));
        authResponse.setTitle("Welcome" + savedUser.getFullName());
        authResponse.setMessage("Registered Successfully");
        return authResponse;
    }

    @Override
    public AuthResponse login(String email, String password) throws Exception {

        Authentication authentication = authenticate(email, password);

        User user = userRepository.findByEmail(email);
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        String jwt = jwtProvider.generateToken(authentication, user.getId());

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setUser(UserMapper.toDTO(user));
        authResponse.setTitle("Welcome Back" + user.getFullName());
        authResponse.setMessage("Login Successfully!");

        return authResponse;
    }
    private Authentication authenticate(String email, String password) throws Exception {


        UserDetails userDetails = customUsersDetailService.loadUserByUsername(email);

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new Exception("Invalid password!");
        }

        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
    }
}
