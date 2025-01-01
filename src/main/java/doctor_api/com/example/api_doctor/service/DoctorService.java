package doctor_api.com.example.api_doctor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import doctor_api.com.example.api_doctor.helper.ApiResponse;
import doctor_api.com.example.api_doctor.model.Doctor;
import doctor_api.com.example.api_doctor.repository.DoctorRepository;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public ResponseEntity<ApiResponse<Doctor>> createDoctor(Doctor doctor) {
        try {
            if (doctorRepository.findByEmail(doctor.getUser().getEmail()) != null) {
                return ResponseEntity
                        .status(409)
                        .body(new ApiResponse<>(409, "Email already exists", null));
            }

            Doctor newDoctor = doctorRepository.save(doctor);
            return ResponseEntity
                    .status(201)
                    .body(new ApiResponse<>(201, "Doctor created successfully", newDoctor));

        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body(new ApiResponse<>(500, "An error occurred", null));
        }
    }

    public ResponseEntity<ApiResponse<Doctor>> fetchDoctorByEmail(String email) {
        try {
            Doctor doctor = doctorRepository.findByEmail(email);

            if (doctor == null) {
                return ResponseEntity
                        .status(404)
                        .body(new ApiResponse<>(404, "Doctor not found", null));
            }

            return ResponseEntity
                    .status(200)
                    .body(new ApiResponse<>(200, "Success", doctor));

        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body(new ApiResponse<>(500, "An error occurred", null));
        }
    }
    // update doctor
    public ResponseEntity<ApiResponse<Doctor>> updateDoctor(Doctor doctor) {
        try {
            Doctor existingDoctor = doctorRepository.findByEmail(doctor.getUser().getEmail());

            if (existingDoctor == null) {
                return ResponseEntity
                        .status(404)
                        .body(new ApiResponse<>(404, "Doctor not found", null));
            }

            existingDoctor.setName(doctor.getName());
            existingDoctor.setPhone(doctor.getPhone());
            existingDoctor.setSpeciality(doctor.getSpeciality());

            Doctor updatedDoctor = doctorRepository.save(existingDoctor);
            return ResponseEntity
                    .status(200)
                    .body(new ApiResponse<>(200, "Doctor updated successfully", updatedDoctor));

        } catch (Exception e) {
            return ResponseEntity
                    .status(500)
                    .body(new ApiResponse<>(500, "An error occurred", null));
        }
    }
}
