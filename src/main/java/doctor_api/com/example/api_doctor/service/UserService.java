package doctor_api.com.example.api_doctor.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import doctor_api.com.example.api_doctor.helper.ApiResponse;
import doctor_api.com.example.api_doctor.model.User;
import doctor_api.com.example.api_doctor.repository.UserRepository;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<ApiResponse<List<User>>> fetchAllUsers() {
        try {
            List<User> users = userRepository.findAll();

            if (users.isEmpty()) {
                logger.warn("No users found in the database.");
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "No users found", null));
            }

            logger.info("Fetched {} users from the database.", users.size());
            return ResponseEntity
                    .ok(new ApiResponse<>(HttpStatus.OK.value(), "Success", users));

        } catch (Exception e) {
            logger.error("Error fetching users: {}", e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred", null));
        }
    }

    public ResponseEntity<ApiResponse<User>> createUser(User user){
        try {
            if (userRepository.findByEmail(user.getEmail()) != null) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body(new ApiResponse<>(HttpStatus.CONFLICT.value(), "Email already exists", null));
            }

            String hashPassword = passwordEncoder.encode(user.getPassword());
            
            user.setPassword(hashPassword);
            User newUser = userRepository.save(user);
            logger.info("User created: {}", newUser);
            
            // validate user email ready exits

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(HttpStatus.CREATED.value(), "User created", newUser));
        } catch (Exception e) {
            logger.error("Error creating user: {}", e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred", null));
        }
    }

    public ResponseEntity<ApiResponse<User>> authenticateUser(User user) {
        try {
            User existingUser = userRepository.findByEmail(user.getEmail());

            if (existingUser == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "User not found", null));
            }

            if (!passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body(new ApiResponse<>(HttpStatus.UNAUTHORIZED.value(), "Invalid credentials", null));
            }

            logger.info("User authenticated: {}", existingUser);
            return ResponseEntity
                    .ok(new ApiResponse<>(HttpStatus.OK.value(), "User authenticated", existingUser));
        } catch (Exception e) {
            logger.error("Error authenticating user: {}", e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred", null));
        }
    }
}
