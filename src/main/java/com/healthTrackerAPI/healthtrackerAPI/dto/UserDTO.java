package com.healthTrackerAPI.healthtrackerAPI.dto;


import com.healthTrackerAPI.healthtrackerAPI.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String password;
    private UserRole userRole;
}
