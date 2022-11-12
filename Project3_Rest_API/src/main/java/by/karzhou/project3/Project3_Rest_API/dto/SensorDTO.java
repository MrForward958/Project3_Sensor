package by.karzhou.project3.Project3_Rest_API.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SensorDTO {

    @NotEmpty(message = "Sensor name must not be empty!")
    @Size(min = 3, max = 30, message = "Name must be between 3 and 30 characters")
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
