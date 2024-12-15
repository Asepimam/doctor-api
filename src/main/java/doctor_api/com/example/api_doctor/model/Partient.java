package doctor_api.com.example.api_doctor.model;

import doctor_api.com.example.api_doctor.exception.constraints.ValidPhoneNumber;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "partient")
@Data
@NoArgsConstructor 
@AllArgsConstructor
public class Partient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name is required")
    private String name;

    @NotEmpty(message = "email is required")
    private String email;

    @NotEmpty(message = "phone is required")
    @ValidPhoneNumber(message = "Invalid phone number")
    private String phone;

    @NotEmpty(message = "address is required")
    private String address;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
