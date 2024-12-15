package doctor_api.com.example.api_doctor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import doctor_api.com.example.api_doctor.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    List<User>  findAll();
    
}

