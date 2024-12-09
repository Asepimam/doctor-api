package doctor_api.com.example.api_doctor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import doctor_api.com.example.api_doctor.model.User;
import doctor_api.com.example.api_doctor.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public User getUserByEmail(@RequestParam String email) {
        return this.userService.fetchUserByEmail(email);
    }
}
