package doctor_api.com.example.api_doctor.service;

import org.springframework.beans.factory.annotation.Autowired;

import doctor_api.com.example.api_doctor.model.User;
import doctor_api.com.example.api_doctor.repository.UserRepository;

public class UserService {
    @Autowired
    private UserRepository userRepositori;

    // public User saveUser(User user) {
    //     return userRepositori.save(user);
    // }

    public User fetchUserByEmail(String email) {
        return this.userRepositori.findByEmail(email);
    }
    
}
