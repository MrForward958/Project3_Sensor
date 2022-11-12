import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Регистрируем сенсор в бд:
        System.out.println("Enter name of sensor");
        String sensorName = scanner.nextLine();
            while (sensorName.isEmpty()) {
                System.out.println("Enter name of sensor");
                sensorName = scanner.nextLine();
            }
        registration(sensorName);

        System.out.println("Do you want make 1000 requests?");
        scanner.nextLine();
        Random random = new Random();
        System.out.println("wait...");
        for (int i = 0; i < 1000; i++) {
            Double value = random.nextDouble() * 200 - 100;
            sendMeasurement(value,random.nextBoolean(),sensorName);
        }
        System.out.println("Successfully!");
    }

    private static void sendMeasurement(Double value, boolean raining, String sensorName) {
        final String url = "http://localhost:8080/measurements/add";
        Map<String,Object> jsonData = new HashMap<>();
        jsonData.put("value",value);
        jsonData.put("raining",raining);
        //map.of - вкладываем еще одну пару ключ значение под ключ sensor.
        jsonData.put("sensor",Map.of("name",sensorName));
        makePostJsonRequest(url,jsonData);
    }

    private static void registration(String sensorName) {
        final String url = "http://localhost:8080/sensors/registration";
        Map<String, Object>  jsonData = new HashMap<>();
        jsonData.put("name",sensorName);
        makePostJsonRequest(url,jsonData);
    }

    private static void makePostJsonRequest(String url, Map<String, Object> jsonData) {
        final RestTemplate restTemplate = new RestTemplate();

        //Помечаем в заголовках что будем отсылать данные в формате json, что бы сервер понимал.
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String,Object>> request = new HttpEntity<>(jsonData,headers);
        try {
            restTemplate.postForObject(url,request, HttpStatus.class);
        }catch (HttpClientErrorException e){
            System.out.println("Некорректные данные");
            System.out.println(e.getMessage());
        }

    }
}
