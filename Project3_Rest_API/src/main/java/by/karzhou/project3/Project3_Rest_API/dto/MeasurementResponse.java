package by.karzhou.project3.Project3_Rest_API.dto;

import java.util.List;

public class MeasurementResponse {
    List<MeasurementDTO> measurementDTO;

    public MeasurementResponse(List<MeasurementDTO> measurementDTO) {
        this.measurementDTO = measurementDTO;
    }

    public List<MeasurementDTO> getMeasurementDTO() {
        return measurementDTO;
    }

    public void setMeasurementDTO(List<MeasurementDTO> measurementDTO) {
        this.measurementDTO = measurementDTO;
    }
}
