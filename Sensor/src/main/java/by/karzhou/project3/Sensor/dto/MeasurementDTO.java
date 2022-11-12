package by.karzhou.project3.Sensor.dto;



import javax.validation.constraints.*;


public class MeasurementDTO {

    @NotNull(message = "Value temperature should not be empty!")
    @Max(value = 100, message = "Value should be not greater than 100")
    @Min(value = -100, message = "Value should be greater than -100")
    private Double value;

    @NotNull(message = "The raining field should not be empty!")
    private Boolean raining;

    private SensorDTO sensor;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
