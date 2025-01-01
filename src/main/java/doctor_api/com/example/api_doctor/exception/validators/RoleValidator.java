package doctor_api.com.example.api_doctor.exception.validators;

import doctor_api.com.example.api_doctor.exception.constraints.ValidRole;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RoleValidator implements ConstraintValidator<ValidRole, String> {
    private static final String[] ALLO_STRINGS = {"patient", "doctor"};

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        if (value ==  null){
            return false;
        }
        for (String role : ALLO_STRINGS){
            if (role.equalsIgnoreCase(value)){
                return true;
            }
        }
        return false;
    }
}
