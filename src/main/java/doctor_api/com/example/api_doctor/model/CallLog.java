package doctor_api.com.example.api_doctor.model;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "call_log")
@NoArgsConstructor
@AllArgsConstructor
public class CallLog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne()
    @JoinColumn(name = "call_room_id")
    private CallRoom callRoom;

    @ManyToOne()
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;


    @ManyToOne()
    @JoinColumn(name = "patient_id")
    private Patient patient;


    @Column(name = "call_duration",nullable = false)
    private Long call_duration;

    @Column(name = "joined_at",nullable = false)
    private LocalDateTime joined_at;


    @Column(name = "left_at",nullable = false)
    private LocalDateTime left_at;

    @Column(name = "create_at",nullable = false)
    private LocalDateTime create_at;
}
