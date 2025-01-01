package doctor_api.com.example.api_doctor.exception.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import doctor_api.com.example.api_doctor.exception.validators.RoleValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = RoleValidator.class)
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRole {
    String message() default "Invalid role. Allowed roles are: patient, doctor";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
