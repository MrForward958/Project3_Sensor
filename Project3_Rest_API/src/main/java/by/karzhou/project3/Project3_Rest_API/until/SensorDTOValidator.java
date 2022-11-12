package by.karzhou.project3.Project3_Rest_API.until;

import by.karzhou.project3.Project3_Rest_API.dto.SensorDTO;
import by.karzhou.project3.Project3_Rest_API.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


//TODO описать валидатор для сенсора когда будет бд и сервис с репозиторием

@Component
public class SensorDTOValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public SensorDTOValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensorDTO = (SensorDTO) target;
        if(sensorService.findByName(sensorDTO.getName()) != null){
            errors.rejectValue("name","","Name must be unique!");
        }
    }

}
