package by.karzhou.project3.Sensor.controller;


import by.karzhou.project3.Sensor.dto.MeasurementDTO;
import by.karzhou.project3.Sensor.dto.MeasurementResponse;
import by.karzhou.project3.Sensor.model.Measurement;
import by.karzhou.project3.Sensor.model.Sensor;
import by.karzhou.project3.Sensor.service.MeasurementService;
import by.karzhou.project3.Sensor.service.SensorService;
import by.karzhou.project3.Sensor.until.MeasureDTOValidator;
import by.karzhou.project3.Sensor.until.exception.ErrorResponse;
import by.karzhou.project3.Sensor.until.exception.ErrorsBuilder;
import by.karzhou.project3.Sensor.until.exception.InputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.stream.Collectors;


import static java.lang.System.currentTimeMillis;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;
    private final SensorService sensorService;
    private final MeasureDTOValidator measureDTOValidator;

    @Autowired
    public MeasurementController(MeasurementService measurementService, SensorService sensorService, MeasureDTOValidator measureDTOValidator) {
        this.measurementService = measurementService;
        this.sensorService = sensorService;
        this.measureDTOValidator = measureDTOValidator;
    }

    @GetMapping()
    public MeasurementResponse getListMeasurements(){
        return new MeasurementResponse(measurementService.findAll().stream().map(measurementService::convertToMeasurementDTO).collect(Collectors.toList()));
    }

    @GetMapping("/rainyDaysCount")
    public Integer getRainyDaysCount(){
        return measurementService.rainyDaysCount();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                     BindingResult bindingResult){
        measureDTOValidator.validate(measurementDTO,bindingResult);
        if(bindingResult.hasErrors()){
            ErrorsBuilder.buildingErrorMessage(bindingResult);
        }
        Sensor sensor = sensorService.findByName(measurementDTO.getSensor().getName());
        Measurement measurement = new Measurement(measurementDTO.getValue(),measurementDTO.getRaining(), LocalDateTime.now(),sensor);
        measurementService.save(measurement);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(InputException e){
         ErrorResponse errorResponse = new ErrorResponse(e.getMessage(),new Date(currentTimeMillis()));
         return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }


}
