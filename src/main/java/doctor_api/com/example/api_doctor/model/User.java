package doctor_api.com.example.api_doctor.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "first name is required")
    @Size(min = 4 ,max = 50, message = "Name must be between 4 and 50 characters")
    private String firstName;


    @NotEmpty(message = "last name is required")
    @Size(min = 4 ,max = 50, message = "Name must be between 4 and 50 characters")
    private String lastName;


    @NotEmpty(message = "email is required")
    @Size(min = 4 ,max = 50, message = "Name must be between 4 and 50 characters")
    private String email;

    @NotEmpty(message = "password is required")
    @Size(min = 4 ,max = 8, message = "Name must be between 4 and 50 characters")
    private String password;

    @NotEmpty(message = "role is required")
    @Size(min = 4 ,max = 50, message = "Name must be between 4 and 50 characters")
    private String role;
}
