package doctor_api.com.example.api_doctor.model;

import java.time.LocalDateTime;

import doctor_api.com.example.api_doctor.exception.constraints.ValidPhoneNumber;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "doctor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "name is required")
    private String name;

    
    @ValidPhoneNumber
    private Long phone;

    private String address;
    private String experience;
    private String qualification;

    @NotEmpty(message = "speciality is required")
    private String speciality;


    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id" )
    private User user;

    @Column(name = "create_at", updatable = false)
    private LocalDateTime create_at = LocalDateTime.now();
    
    private LocalDateTime update_at = LocalDateTime.now();

}
