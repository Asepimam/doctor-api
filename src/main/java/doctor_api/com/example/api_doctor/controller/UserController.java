package doctor_api.com.example.api_doctor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import doctor_api.com.example.api_doctor.helper.ApiResponse;
import doctor_api.com.example.api_doctor.model.User;
import doctor_api.com.example.api_doctor.service.UserService;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        return  this.userService.fetchAllUsers();
    }

    @PostMapping(path = "create")
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody User user) {
        return this.userService.createUser(user);

    }

    @PostMapping(path = "login")
    public ResponseEntity<ApiResponse<User>> loginUser(@RequestBody User user) {
        return this.userService.authenticateUser(user);
    }
}
