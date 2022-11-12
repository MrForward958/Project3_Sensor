package by.karzhou.project3.Sensor.repository;

import by.karzhou.project3.Sensor.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {

    public List<Measurement> findAll();
    public List<Measurement> findByRaining(boolean a);

}
