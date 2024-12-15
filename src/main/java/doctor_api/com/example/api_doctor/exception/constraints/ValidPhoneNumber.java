package doctor_api.com.example.api_doctor.exception.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import doctor_api.com.example.api_doctor.exception.validators.PhoneNumberValidator;
import jakarta.validation.Constraint;

@Constraint(validatedBy = PhoneNumberValidator.class)
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPhoneNumber {
    String message() default "Invalid phone number";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
