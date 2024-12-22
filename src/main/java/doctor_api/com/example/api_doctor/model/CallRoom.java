package doctor_api.com.example.api_doctor.model;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "call_room")
@NoArgsConstructor
@AllArgsConstructor

public class CallRoom {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;


    @NotEmpty(message = "Channel id is required")
    @Size(min = 5, max = 50, message = "Channel id must be between 5 and 50 characters")
    private String channel_id;

    @ManyToOne()
    @JoinTable(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne()
    @JoinTable(name = "patient_id")
    private Partient patient;

    @Column(name = "create_at")
    private LocalDateTime create_at;

    @Column(name = "update_at")
    private LocalDateTime update_at;


}
