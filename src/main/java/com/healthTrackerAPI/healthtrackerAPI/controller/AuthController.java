package com.healthTrackerAPI.healthtrackerAPI.controller;

import com.healthTrackerAPI.healthtrackerAPI.dto.AuthenticationRequest;
import com.healthTrackerAPI.healthtrackerAPI.dto.AuthenticationResponse;
import com.healthTrackerAPI.healthtrackerAPI.dto.SignupRequest;
import com.healthTrackerAPI.healthtrackerAPI.dto.UserDTO;
import com.healthTrackerAPI.healthtrackerAPI.jwt.UserServiceImpl;
import com.healthTrackerAPI.healthtrackerAPI.model.User;
import com.healthTrackerAPI.healthtrackerAPI.repository.UserRepository;
import com.healthTrackerAPI.healthtrackerAPI.service.UserService;
import com.healthTrackerAPI.healthtrackerAPI.service.auth.AuthService;
import com.healthTrackerAPI.healthtrackerAPI.util.JwtUtil;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final UserService userServices;

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest) {
            try {
                UserDTO createdUser = authService.createUser(signupRequest);
                return new ResponseEntity<>(createdUser, HttpStatus.OK);
            } catch (EntityExistsException entityExistsException) {
                return new ResponseEntity<>("User already exists!", HttpStatus.NOT_ACCEPTABLE);
            } catch (Exception e) {
                return new ResponseEntity<>("User not created. Try again later!", HttpStatus.BAD_REQUEST);
            }
    }

    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password");
        }
        final UserDetails userDetails = userServices.userDetailsService().loadUserByUsername(authenticationRequest.getEmail());
        Optional<User> optionalUser = userRepository.findByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();

        if(optionalUser.isPresent()) {
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUserRole(optionalUser.get().getUserRole());
            authenticationResponse.setUserId(optionalUser.get().getId());
        }
        return authenticationResponse;
    }
}