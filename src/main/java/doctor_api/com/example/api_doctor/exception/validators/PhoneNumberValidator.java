package doctor_api.com.example.api_doctor.exception.validators;



import doctor_api.com.example.api_doctor.exception.constraints.ValidPhoneNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {
    private static final String PHONE_REGEX = "\\+?\\d{10,15}";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.matches(PHONE_REGEX);
    }
}

