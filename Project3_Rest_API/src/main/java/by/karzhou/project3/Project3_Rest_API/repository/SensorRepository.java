package by.karzhou.project3.Project3_Rest_API.repository;

import by.karzhou.project3.Project3_Rest_API.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor,Integer> {

    public Sensor findByName(String name);
}
