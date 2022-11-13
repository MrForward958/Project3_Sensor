There is a meteorological sensor. The sensor can determine the air temperature and the presence of precipitation (rain).
You need to create a REST API service that will receive data from the sensor.
Every time the sensor makes a measurement, it will send HTTP
request with data in JSON format to our service.
We implement a "sensor" because we don’t have a real one, we will send requests in JSON format using RestTemplate to our REST API service.
We implement a REST API service that will process and work with data from the sensor:
Register a new sensor in the database.
Add new data from the sensor to the database.
Returns all measurements from the database.
Returns the number of rainy days from the database
Also, based on the data obtained, we will build a graph using the xchart library.