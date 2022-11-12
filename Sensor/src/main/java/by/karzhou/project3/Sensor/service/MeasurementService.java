package by.karzhou.project3.Sensor.service;


import by.karzhou.project3.Sensor.dto.MeasurementDTO;
import by.karzhou.project3.Sensor.model.Measurement;
import by.karzhou.project3.Sensor.repository.MeasurementRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, ModelMapper modelMapper) {
        this.measurementRepository = measurementRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public void save(Measurement measurement){
        measurementRepository.save(measurement);
    }

    public List<Measurement> findAll(){
        return measurementRepository.findAll();
    }

    public MeasurementDTO convertToMeasurementDTO(Measurement measurement){
        return  modelMapper.map(measurement,MeasurementDTO.class);
    }

    public Integer rainyDaysCount(){
        return measurementRepository.findByRaining(true).size();
    }
}
