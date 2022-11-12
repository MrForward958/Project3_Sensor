package by.karzhou.project3.Sensor.until.exception;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ErrorsBuilder {
    public static void  buildingErrorMessage(BindingResult bindingResult){
        StringBuilder errorMessage = new StringBuilder();

        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error: errors ) {
            errorMessage.append(error.getField()).append(" - ")
                    .append(error.getDefaultMessage()).append(";");
        }
        throw new InputException(errorMessage.toString());
    }
}
