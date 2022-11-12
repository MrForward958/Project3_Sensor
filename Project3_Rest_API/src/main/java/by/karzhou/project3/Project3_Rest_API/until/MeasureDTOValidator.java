package by.karzhou.project3.Project3_Rest_API.until;

import by.karzhou.project3.Project3_Rest_API.dto.MeasurementDTO;
import by.karzhou.project3.Project3_Rest_API.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasureDTOValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public MeasureDTOValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return MeasurementDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDTO measurementDTO = (MeasurementDTO) target;
        if(measurementDTO.getSensor().getName().isEmpty())
            errors.rejectValue("sensor", "", "Sensor name must not be empty!");
        String nameSensor = measurementDTO.getSensor().getName();
        if(sensorService.findByName(nameSensor) == null)
            errors.rejectValue("sensor", "", "Sensor does not exist!");
    }

}
