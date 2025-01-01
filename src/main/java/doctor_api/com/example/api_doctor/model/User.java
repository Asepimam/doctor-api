package doctor_api.com.example.api_doctor.model;

import java.time.LocalDateTime;

import org.checkerframework.common.aliasing.qual.Unique;

import com.fasterxml.jackson.annotation.JsonProperty;

import doctor_api.com.example.api_doctor.exception.constraints.ValidRole;
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
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Username is required")
    @Size(min = 4 ,max = 50, message = "Name must be between 4 and 50 characters")
    // username must be unique
    @Unique()
    private String username;


    @NotEmpty(message = "email is required")
    @Email(message = "Email is invalid")
    private String email;

    @NotEmpty(message = "password is required")
    @Size(min = 8 ,max = 13, message = "password must be between 8 and 13 characters")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotEmpty(message = "role is required")
    @ValidRole(message = "Invalid role. Allowed roles are: patient, doctor")
    private String role;
    
    private LocalDateTime create_at = LocalDateTime.now();
    private LocalDateTime update_at = LocalDateTime.now();
    
}
