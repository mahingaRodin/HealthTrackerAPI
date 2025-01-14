package com.healthTrackerAPI.healthtrackerAPI.service.auth;

import com.healthTrackerAPI.healthtrackerAPI.dto.SignupRequest;
import com.healthTrackerAPI.healthtrackerAPI.dto.UserDTO;

public interface AuthService {
    UserDTO createUser(SignupRequest signupRequest);
}
