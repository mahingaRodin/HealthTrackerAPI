package com.healthTrackerAPI.healthtrackerAPI.dto;

import com.healthTrackerAPI.healthtrackerAPI.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {
    private String jwt;
    private Long userId;
    private UserRole userRole;
}
