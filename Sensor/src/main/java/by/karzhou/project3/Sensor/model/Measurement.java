package by.karzhou.project3.Sensor.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "Measurement")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "measurement_id")
    private int id;

    @NotNull(message = "Value temperature should not be empty!")
    @Max(value = 100, message = "Value should be not greater than 100")
    @Min(value = -100, message = "Value should be greater than -100")
    @Column(name = "value", nullable = false)
    private Double value;

    @Column(name = "raining")
    @NotNull(message = "Value raining should not be empty!")
    private Boolean raining;


    @Column(name = "measurement_time")
    private LocalDateTime measurementTime;

    @ManyToOne
    //Name - как будет называться колонка в таблице измерений
    //referencedColumnName - как называется колонка в таблице one(Sensor).
    @JoinColumn(name = "sensor_name", referencedColumnName = "name")
    private Sensor sensor;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }


    public Measurement() {
    }

    public Measurement(double value, boolean raining, LocalDateTime measurementTime, Sensor sensor) {
        this.value = value;
        this.raining = raining;
        this.measurementTime = measurementTime;
        this.sensor = sensor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public LocalDateTime getMeasurementTime() {
        return measurementTime;
    }

    public void setMeasurementTime(LocalDateTime measurementTime) {
        this.measurementTime = measurementTime;
    }


    @Override
    public String toString() {
        return "Measurement{" +
                "value=" + value +
                ", raining=" + raining +
                '}';
    }
}
