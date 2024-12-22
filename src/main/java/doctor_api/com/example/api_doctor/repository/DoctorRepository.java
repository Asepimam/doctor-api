package doctor_api.com.example.api_doctor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import doctor_api.com.example.api_doctor.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor findByEmail(String email);
    List<Doctor>  findAll();

}    

