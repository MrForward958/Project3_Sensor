package by.karzhou.project3.Sensor.service;


import by.karzhou.project3.Sensor.dto.SensorDTO;
import by.karzhou.project3.Sensor.model.Sensor;
import by.karzhou.project3.Sensor.repository.SensorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SensorService {

    private final SensorRepository sensorRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorService(SensorRepository sensorRepository, ModelMapper modelMapper) {
        this.sensorRepository = sensorRepository;
        this.modelMapper = modelMapper;
    }

    public Sensor findByName(String name){
        return sensorRepository.findByName(name);
    }

    @Transactional
    public void save(Sensor sensor){
        sensorRepository.save(sensor);
    }

    public Sensor convertToSensor(SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO,Sensor.class);
    }

    public SensorDTO convertToSensorDTO(Sensor sensor){
        return modelMapper.map(sensor,SensorDTO.class);
    }

}
