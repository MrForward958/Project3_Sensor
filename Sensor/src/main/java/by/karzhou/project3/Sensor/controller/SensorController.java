package by.karzhou.project3.Sensor.controller;
import by.karzhou.project3.Sensor.dto.SensorDTO;
import by.karzhou.project3.Sensor.service.SensorService;
import by.karzhou.project3.Sensor.until.SensorDTOValidator;
import by.karzhou.project3.Sensor.until.exception.ErrorResponse;
import by.karzhou.project3.Sensor.until.exception.ErrorsBuilder;
import by.karzhou.project3.Sensor.until.exception.InputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

import static java.lang.System.currentTimeMillis;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;
    private final SensorDTOValidator sensorDTOValidator;

    @Autowired
    public SensorController(SensorService sensorService, SensorDTOValidator sensorDTOValidator) {
        this.sensorService = sensorService;
        this.sensorDTOValidator = sensorDTOValidator;
    }


    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registrationSensor(@RequestBody @Valid SensorDTO sensorDTO,
                                                         BindingResult bindingResult){
        sensorDTOValidator.validate(sensorDTO,bindingResult);
        if(bindingResult.hasErrors()){
            ErrorsBuilder.buildingErrorMessage(bindingResult);
        }
        sensorService.save(sensorService.convertToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(InputException e){
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(),new Date(currentTimeMillis()));
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

}
