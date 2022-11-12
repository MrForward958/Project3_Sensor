package by.karzhou.project3.Sensor.repository;

import by.karzhou.project3.Sensor.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor,Integer> {

    public Sensor findByName(String name);
}
