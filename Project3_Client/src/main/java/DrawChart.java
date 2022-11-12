
import dto.MeasurementDTO;
import dto.MeasurementResponse;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class DrawChart {
    public static void main(String[] args) {
        List<Double> temperatures = getTemperaturesFromServer();
        if(!temperatures.isEmpty()) {
            drawChar(temperatures);
        }else {
            System.out.println("Данные отсутствуют!");
        }
    }

    private static void drawChar(List<Double> temperatures) {
        //Получаем поток значений от нуля до количества температур, переводим в поток даблов, и в массив.
        double[] xData = IntStream.range(0, temperatures.size()).asDoubleStream().toArray();
        //Получаем поток, маппим этот поток в поток double, и упаковываем в массив.
        double[] yData = temperatures.stream().mapToDouble(x -> x).toArray();
        XYChart chart = QuickChart.getChart("Temperatures", "X", "Y", "temperature",
                xData, yData);

        new SwingWrapper(chart).displayChart();

    }

    private static List<Double> getTemperaturesFromServer() {
        final RestTemplate restTemplate = new RestTemplate();
        final String url = "http://localhost:8080/measurements";

        //Получаем лист упакованный в класс обертку, для удобства передачи и получения.
        MeasurementResponse measurementsResponse = restTemplate.getForObject(url,MeasurementResponse.class);


        if(measurementsResponse == null || measurementsResponse.getMeasurementDTO() == null)
            return Collections.emptyList();

        //Упаковываем все значения value в лист.
        return measurementsResponse.getMeasurementDTO().stream().map(MeasurementDTO::getValue).collect(Collectors.toList());
    }
}