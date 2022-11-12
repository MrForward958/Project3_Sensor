package by.karzhou.project3.Project3_Rest_API.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Sensor")
//implements Serializable - т.к. связь идет не по индексу, а по полю name.
public class Sensor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sensor_id")
    private int id;

    @Column(name = "name", unique = true)
    @NotEmpty(message = "Sensor name must not be empty!")
    @Size(min = 3, max = 30, message = "Name must be between 3 and 30 characters")
    private String name;

    @OneToMany(mappedBy = "sensor")
    private List<Measurement> measurements;

    public Sensor(){
    }

    public Sensor(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }


    @Override
    public String toString() {
        return "Sensor{" +
                "name='" + name + '\'' +
                '}';
    }
}
