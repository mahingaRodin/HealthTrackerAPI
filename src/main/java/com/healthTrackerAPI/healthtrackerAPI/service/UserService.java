package com.healthTrackerAPI.healthtrackerAPI.service;


import com.healthTrackerAPI.healthtrackerAPI.dto.LoginDTO;
import com.healthTrackerAPI.healthtrackerAPI.dto.UserDTO;
import com.healthTrackerAPI.healthtrackerAPI.exception.ResourceNotFoundException;
import com.healthTrackerAPI.healthtrackerAPI.model.User;
import com.healthTrackerAPI.healthtrackerAPI.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return userRepository.save(user);
    }

    public String loginUser(LoginDTO loginDTO) {
        Optional<User> user = userRepository.findByEmail(loginDTO.getEmail());
        if(user.isPresent() && user.get().getPassword().equals(loginDTO.getPassword())) {
            //return plaintext password
            return "Login successful";
        }
        throw  new ResourceNotFoundException("Invalid email or password");
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return null;
            }
        };
    }
}
