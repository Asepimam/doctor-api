package doctor_api.com.example.api_doctor.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dt_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Username is required")
    @Size(min = 4 ,max = 50, message = "Name must be between 4 and 50 characters")
    private String username;


    @NotEmpty(message = "email is required")
    @Size(min = 4 ,max = 50, message = "Name must be between 4 and 50 characters")
    @Email(message = "Email is invalid")
    private String email;

    @NotEmpty(message = "password is required")
    @Size(min = 4 ,max = 80, message = "Name must be between 4 and 50 characters")
    private String password;

    @NotEmpty(message = "role is required")
    @Size(min = 4 ,max = 50, message = "Name must be between 4 and 50 characters")
    private String role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Doctor doctor;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Partient partient;
    
    
}
